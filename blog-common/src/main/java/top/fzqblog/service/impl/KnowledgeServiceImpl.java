package top.fzqblog.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.HtmlUtils;

import top.fzqblog.exception.BussinessException;
import top.fzqblog.mapper.KnowledgeMapper;
import top.fzqblog.po.enums.ArticleType;
import top.fzqblog.po.enums.FileTopicType;
import top.fzqblog.po.enums.MarkEnum;
import top.fzqblog.po.enums.MessageType;
import top.fzqblog.po.enums.OrderByEnum;
import top.fzqblog.po.enums.PageSize;
import top.fzqblog.po.enums.StatusEnum;
import top.fzqblog.po.enums.TextLengthEnum;
import top.fzqblog.po.model.Attachment;
import top.fzqblog.po.model.Knowledge;
import top.fzqblog.po.model.MessageParams;
import top.fzqblog.po.model.SysRes;
import top.fzqblog.po.model.Topic;
import top.fzqblog.po.query.KnowledgeQuery;
import top.fzqblog.po.query.UpdateQuery4ArticleCount;
import top.fzqblog.service.AttachmentService;
import top.fzqblog.service.KnowledgeService;
import top.fzqblog.service.MessageService;
import top.fzqblog.service.SysResService;
import top.fzqblog.service.SysRoleService;
import top.fzqblog.service.UserService;
import top.fzqblog.utils.ImageUtils;
import top.fzqblog.utils.Page;
import top.fzqblog.utils.PageResult;
import top.fzqblog.utils.StringUtils;

@Service
public class KnowledgeServiceImpl implements KnowledgeService {
	@Autowired
	private KnowledgeMapper<Knowledge, KnowledgeQuery> knowledgeMapper;

	@Autowired
	private AttachmentService attachmentService;
	
	@Autowired
	private FormateAtService formateAtService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private SysResService sysResService;
	
	@Autowired
	private SysRoleService sysRoleService;
	
	public PageResult<Knowledge> findKnowledgeByPage(
			KnowledgeQuery knowledgeQuery) {
		knowledgeQuery.setStatus(StatusEnum.AUDIT);
		int count = this.knowledgeMapper.selectCount(knowledgeQuery);
		int pageSize = PageSize.PAGE_SIZE20.getSize();
		int pageNum = knowledgeQuery.getPageNum() == 1 ? 1 : knowledgeQuery.getPageNum();
		Page page = new Page(pageNum, count, pageSize);
		knowledgeQuery.setPage(page);
		knowledgeQuery.setOrderBy(OrderByEnum.CREATE_TIME_DESC);
		List<Knowledge> list = this.knowledgeMapper.selectList(knowledgeQuery);
		PageResult<Knowledge> pageResult = new PageResult<Knowledge>(page, list);
		return pageResult;
	}

	public Knowledge getKnowledge(Integer knowledgeId) {
		if(knowledgeId == null){
			return null;
		}
		KnowledgeQuery knowledgeQuery = new KnowledgeQuery();
		knowledgeQuery.setTopicId(knowledgeId);
		knowledgeQuery.setShowContent(Boolean.TRUE);
		List<Knowledge> list = this.knowledgeMapper.selectList(knowledgeQuery);
		if(list.isEmpty()){
			return null;
		}
		return list.get(0);
	}

	public Knowledge showKnowledge(Integer knowledgeId, Integer userId) throws BussinessException{
		Knowledge knowledge = this.getKnowledge(knowledgeId);
		Set<Integer> roleSet = sysRoleService.findRoleIdsByUserId(userId);
		List<SysRes> list = sysResService.findMenuByRoleIds(roleSet);
		Set<String> permkey = new HashSet<>();
		if(list != null){
			for(SysRes sysRes : list){
				permkey.add(sysRes.getKey());
			}
		}
		if(knowledge == null ||(knowledge.getStatus() == StatusEnum.INIT && knowledge.getUserId().intValue() != userId) 
				|| permkey == null || !permkey.contains("content:knowledge:review")
				){
			throw new BussinessException("文章不存在,或已删除");
		}
		knowledge.setAttachment(this.attachmentService.getAttachmentByTopicIdAndFileType(knowledge.getTopicId(), FileTopicType.KNOWLEDGE));
		UpdateQuery4ArticleCount updateQuery4ArticleCount = new UpdateQuery4ArticleCount();
		updateQuery4ArticleCount.setAddReadCount(Boolean.TRUE);
		updateQuery4ArticleCount.setArticleId(knowledgeId);
		this.knowledgeMapper.updateInfoCount(updateQuery4ArticleCount);
		return knowledge;
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=BussinessException.class)
	public void addKnowledge(Knowledge knowledge, Attachment attachment) throws BussinessException {
		if(knowledge.getTitle() == null || knowledge.getTitle().length() > TextLengthEnum.TEXT_300_LENGTH.getLength()
				|| knowledge.getpCategoryId() == null || knowledge.getCategoryId() == null ||
				StringUtils.isEmpty(knowledge.getContent()) || knowledge.getContent().length() > TextLengthEnum.LONGTEXT.getLength()
				){
			throw new BussinessException("参数错误");
		}
		String title = HtmlUtils.htmlUnescape(knowledge.getTitle());
		String content = knowledge.getContent();
		String summary = StringUtils.cleanHtmlTag(HtmlUtils.htmlUnescape(content));
		if (summary.length() > TextLengthEnum.TEXT_200_LENGTH.getLength()) {
			summary = summary.substring(0,
					(int) TextLengthEnum.TEXT_200_LENGTH.getLength())
					+ "......";
		}
		Set<Integer> userIds = new HashSet<Integer>();
		//TODO给用户发消息
		String formatContent = formateAtService.generateRefererLinks(content,
				userIds);
		knowledge.setSummary(summary);
		knowledge.setTitle(title);
		knowledge.setContent(formatContent);
		String topicImage = ImageUtils.getImages(content);
		knowledge.setTopicImage(topicImage);
//		String knowledgeImageSmall = ImageUtils.createThumbnail(topicImage, true);
//		knowledge.setTopicImageThum(knowledgeImageSmall);
		Date curDate = new Date();
		knowledge.setCreateTime(curDate);
		knowledge.setLastCommentTime(curDate);
		knowledge.setStatus(StatusEnum.INIT);
		this.knowledgeMapper.insert(knowledge);
		this.userService.changeMark(knowledge.getUserId(),
				MarkEnum.MARK_TOPIC.getMark());
		
		if(!StringUtils.isEmpty(attachment.getFileName()) &&
				!StringUtils.isEmpty(attachment.getFileUrl())){
			attachment.setTopicId(knowledge.getTopicId());
			attachment.setFileTopicType(FileTopicType.KNOWLEDGE);
			this.attachmentService.addAttachment(attachment);
		}
		
		MessageParams messageParams = new MessageParams();
		messageParams.setArticleId(knowledge.getTopicId());
		messageParams.setArticleType(ArticleType.KNOWLEDGE);
		messageParams.setArticleUserId(knowledge.getUserId());
		messageParams.setMessageType(MessageType.AT_ARTICLE_MESSAGE);
		messageParams.setSendUserName(knowledge.getUserName());
		messageParams.setSendUserId(knowledge.getUserId());
		messageParams.setReceiveUserIds(userIds);
		messageService.createMessage(messageParams);
	}
	
	@Override
	public List<Knowledge> findKnowledgeList() {
		KnowledgeQuery knowledgeQuery = new KnowledgeQuery();
		knowledgeQuery.setStatus(StatusEnum.INIT);
		knowledgeQuery.setOrderBy(OrderByEnum.CREATE_TIME_DESC);
		return knowledgeMapper.selectList(knowledgeQuery);
	}

	@Override
	public void deleteBatch(Integer[] ids) throws BussinessException {
		if(ids == null){
			throw new BussinessException("参数错误");
		}		
		for(int id : ids){
			knowledgeMapper.delete(id);
		}
	}

	@Override
	public void updateStatusBatch(Integer[] ids) throws BussinessException {
		if(ids == null){
			throw new BussinessException("参数错误");
		}		
		
		knowledgeMapper.updateKnowledgeStatus(StatusEnum.AUDIT, ids);
	}

}
