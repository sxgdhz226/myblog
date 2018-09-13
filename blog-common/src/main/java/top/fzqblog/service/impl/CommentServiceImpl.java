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
import top.fzqblog.mapper.AskMapper;
import top.fzqblog.mapper.BlogMapper;
import top.fzqblog.mapper.CommentMapper;
import top.fzqblog.mapper.KnowledgeMapper;
import top.fzqblog.mapper.TopicMapper;
import top.fzqblog.po.enums.ArticleType;
import top.fzqblog.po.enums.MarkEnum;
import top.fzqblog.po.enums.MessageType;
import top.fzqblog.po.enums.PageSize;
import top.fzqblog.po.enums.TextLengthEnum;
import top.fzqblog.po.model.Ask;
import top.fzqblog.po.model.Blog;
import top.fzqblog.po.model.Comment;
import top.fzqblog.po.model.Knowledge;
import top.fzqblog.po.model.MessageParams;
import top.fzqblog.po.model.Topic;
import top.fzqblog.po.query.AskQuery;
import top.fzqblog.po.query.BlogQuery;
import top.fzqblog.po.query.CommentQuery;
import top.fzqblog.po.query.KnowledgeQuery;
import top.fzqblog.po.query.TopicQuery;
import top.fzqblog.po.query.UpdateQuery4ArticleCount;
import top.fzqblog.service.CommentService;
import top.fzqblog.service.MessageService;
import top.fzqblog.service.UserService;
import top.fzqblog.utils.Page;
import top.fzqblog.utils.PageResult;
import top.fzqblog.utils.StringUtils;
@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentMapper<Comment, CommentQuery> commentMapper;
	
	@Autowired
	private TopicMapper<Topic, TopicQuery> topicMapper;
	
	@Autowired
	private KnowledgeMapper<Knowledge, KnowledgeQuery> KnowledgeMapper;
	
	@Autowired
	private AskMapper<Ask, AskQuery> askMapper;
	
	@Autowired
	private BlogMapper<Blog, BlogQuery> blogMapper;
	
	@Autowired
	private FormateAtService formateAtService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MessageService messageService;
	
	public PageResult<Comment> findCommentByPage(CommentQuery commentQuery) {
		commentQuery.setPid(0);
		int pageNum = commentQuery.getPageNum() == 1 ? 1 : commentQuery.getPageNum();
		int pageSize = PageSize.PAGE_SIZE10.getSize();
		int count = commentMapper.selectCount(commentQuery);
		Page page = new Page(pageNum, count, pageSize);
		commentQuery.setPage(page);
		List<Comment> list = this.commentMapper.selectList(commentQuery);
		return new PageResult<Comment>(page, list);
	}

	public Comment getCommentById(Integer commentId) {
		CommentQuery commentQuery = new CommentQuery();
		commentQuery.setCommentId(commentId);
		List<Comment> list = this.commentMapper.selectList(commentQuery);
		if(!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=BussinessException.class)
	public void addComment(Comment comment) throws BussinessException {
		String content = comment.getContent();
		content = StringUtils.replaceLast(content.replaceFirst("<p>", ""), "</p>", "");
		if(StringUtils.isEmpty(content) || content.length() > TextLengthEnum.LONGTEXT.getLength()
				|| comment.getArticleId() == null || comment.getArticleType() == null){
			throw new BussinessException("参数错误");
		}
		Integer pid = comment.getPid();
		pid = pid == null ? 0 : pid;
		if(pid!=0){
			content = StringUtils.addLink(content);//给网页加链接
			content = HtmlUtils.htmlEscape(content);
		}
		if(pid != 0 && content.length() > TextLengthEnum.TEXT_500_LENGTH.getLength()){
			throw new BussinessException("参数错误");
		}
		
		Set<Integer> userIds = new HashSet<Integer>();
		String formatContent = this.formateAtService.generateRefererLinks(content, userIds);
		//TODO给用户发消息
		comment.setContent(formatContent);
		comment.setCreateTime(new Date());
		this.commentMapper.insert(comment);
		UpdateQuery4ArticleCount updateQuery4ArticleCount = new UpdateQuery4ArticleCount();
		updateQuery4ArticleCount.setAddCommentCount(Boolean.TRUE);
		updateQuery4ArticleCount.setArticleId(comment.getArticleId());
		Integer articleUserId = null;
		if(comment.getArticleType() == ArticleType.TOPIC){
			this.topicMapper.updateInfoCount(updateQuery4ArticleCount);
			TopicQuery topicQuery = new TopicQuery();
			topicQuery.setTopicId(comment.getArticleId());
			articleUserId = this.topicMapper.selectList(topicQuery).get(0).getUserId();
		}
		else if(comment.getArticleType() == ArticleType.KNOWLEDGE){
			this.KnowledgeMapper.updateInfoCount(updateQuery4ArticleCount);
			KnowledgeQuery knowledgeQuery = new KnowledgeQuery();
			knowledgeQuery.setTopicId(comment.getArticleId());
			articleUserId = this.KnowledgeMapper.selectList(knowledgeQuery).get(0).getUserId();
		}
		else if(comment.getArticleType() == ArticleType.Ask){
			this.askMapper.updateInfoCount(updateQuery4ArticleCount);
			AskQuery askQuery = new AskQuery();
			askQuery.setAskId(comment.getArticleId());
			articleUserId = this.askMapper.selectList(askQuery).get(0).getUserId();
		}
		else if(comment.getArticleType() == ArticleType.BLOG){
			this.blogMapper.updateInfoCount(updateQuery4ArticleCount);
			BlogQuery blogQuery = new BlogQuery();
			blogQuery.setBlogId(comment.getArticleId());
			articleUserId = this.blogMapper.selectList(blogQuery).get(0).getUserId();
		}
		else{
			throw new BussinessException("参数错误");
		}
		this.userService.changeMark(comment.getUserId(), MarkEnum.MARK_COMMENT.getMark());
		if(pid == 0){
			userIds.add(articleUserId);
		}
		else{
			Comment comment2 = this.getCommentById(pid);
			userIds.add(comment2.getUserId());
		}
		MessageParams messageParams = new MessageParams();
		messageParams.setArticleId(comment.getArticleId());
		messageParams.setArticleType(comment.getArticleType());
		messageParams.setArticleUserId(articleUserId);
		messageParams.setMessageType(MessageType.COMMENT_MESSAGE);
		messageParams.setSendUserName(comment.getUserName());
		messageParams.setSendUserId(comment.getUserId());
		messageParams.setReceiveUserIds(userIds);
		messageParams.setCommentId(comment.getId());
		messageParams.setPageNum(comment.getPageNum());
		messageService.createMessage(messageParams);
	}

}
