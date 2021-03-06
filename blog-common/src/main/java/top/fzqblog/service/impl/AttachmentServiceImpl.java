package top.fzqblog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import top.fzqblog.exception.BussinessException;
import top.fzqblog.mapper.AttachmentDownloadMapper;
import top.fzqblog.mapper.AttachmentMapper;
import top.fzqblog.po.enums.FileTopicType;
import top.fzqblog.po.model.Attachment;
import top.fzqblog.po.model.AttachmentDownload;
import top.fzqblog.po.model.SessionUser;
import top.fzqblog.po.model.Topic;
import top.fzqblog.po.model.User;
import top.fzqblog.po.query.AttachmentDownloadQuery;
import top.fzqblog.po.query.AttachmentQuery;
import top.fzqblog.service.AttachmentService;
import top.fzqblog.service.TopicService;
import top.fzqblog.service.UserService;
import top.fzqblog.utils.StringUtils;
@Service
public class AttachmentServiceImpl implements AttachmentService {
	
	@Autowired
	private AttachmentMapper<Attachment, AttachmentQuery> attachmentMapper;
	
	@Autowired
	private AttachmentDownloadMapper<AttachmentDownload, AttachmentDownloadQuery> attachmentDownloadMapper;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TopicService topicService;
	
	public void addAttachment(Attachment attachment) throws BussinessException {
		final int MAXMARK = 10000;
		Integer mark = attachment.getDownloadMark();
		mark = mark == null ? 0 : mark;
		attachment.setDownloadMark(mark);
		if(StringUtils.isEmpty(attachment.getFileName()) || 
			StringUtils.isEmpty(attachment.getFileUrl()) ||
			mark > MAXMARK
				){
			throw new BussinessException("参数错误");
		}
		this.attachmentMapper.insert(attachment);
	}

	public Attachment getAttachmentByTopicIdAndFileType(Integer topicId,
			FileTopicType fileTopicType) {
		AttachmentQuery attachmentQuery = new AttachmentQuery();
		attachmentQuery.setTopicId(topicId);
		attachmentQuery.setFileTopicType(fileTopicType);
		List<Attachment> attachments = this.attachmentMapper.selectList(attachmentQuery);
		if(attachments.isEmpty()){
			return null;
		}
		return attachments.get(0);
	}

	public Attachment getAttachmentById(Integer attachmentId) {
		if(attachmentId == null){
			return null;
		}
		AttachmentQuery attachmentQuery = new AttachmentQuery();
		attachmentQuery.setAttachmentId(attachmentId);
		List<Attachment> attachmentList = this.attachmentMapper.selectList(attachmentQuery);
		if(attachmentList.isEmpty()){
			return null;
		}
		return attachmentList.get(0);
	}

	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=BussinessException.class)
	public Attachment downloadAttachment(SessionUser sessionUser,
			Integer attachmentId) throws BussinessException {
		Attachment attachment = this.getAttachmentById(attachmentId);
		if(attachment == null){
			throw new BussinessException("附件不存在");
		}
		Integer topicId = attachment.getTopicId();
		Topic topic = null;
		FileTopicType fileTopicType = attachment.getFileTopicType();
		if(fileTopicType == FileTopicType.TOPIC){
			topic = this.topicService.getTopic(topicId);
			if(topic == null){
				throw new BussinessException("附件对应的文章不存在");
			}
			this.checkDownloadPermission(topic.getUserId(), sessionUser.getUserid(), attachment.getDownloadMark(), attachmentId);
		}
		else if(fileTopicType == FileTopicType.BLOG){
			
		}
		AttachmentDownload attachmentDownload = new AttachmentDownload();
		attachmentDownload.setAttachmentId(attachmentId);
		attachmentDownload.setUserId(sessionUser.getUserid());
		attachmentDownload.setUserName(sessionUser.getUserName());
		attachmentDownload.setUserIcon(sessionUser.getUserIcon());
		this.attachmentDownloadMapper.insert(attachmentDownload);
		attachmentMapper.updateDownloadCount(attachmentId);
		return attachment;
	}

	public void checkDownloadPermission(Integer topicUserId, Integer userId,
			Integer downloadMark, Integer attachmentId)
			throws BussinessException {
		if(!topicUserId.equals(userId) && downloadMark > 0){
			AttachmentDownloadQuery attachmentDownloadQuery = new AttachmentDownloadQuery();
			attachmentDownloadQuery.setUserId(userId);
			attachmentDownloadQuery.setAttachmentId(attachmentId);
			int downCount = this.attachmentDownloadMapper.selectCount(attachmentDownloadQuery);
			if(downCount == 0){
					int count = this.userService.changeMark(userId, -downloadMark);
					if(count == 0){
						throw new BussinessException("积分不足");
					}
			}
		}
		
	}

	public void checkDownload(Integer attachmentId, Integer topicId,
			SessionUser sessionUser) throws BussinessException {
		if(attachmentId == null || topicId == null){
			throw new BussinessException("参数错误");
		}
		Attachment attachment = this.getAttachmentById(attachmentId);
		if(attachment == null){
			throw new BussinessException("附件不存在");
		}
		Integer topicUserId = null;
		FileTopicType fileTopicType = attachment.getFileTopicType();
		if(fileTopicType == FileTopicType.TOPIC){
			Topic topic = this.topicService.getTopic(topicId);
			if(topic == null){
				throw new BussinessException("附件对应的文章不存在");
				}
			topicUserId = topic.getUserId();
			}
			else if(fileTopicType == FileTopicType.BLOG){
				
			}
		AttachmentDownloadQuery attachmentDownloadQuery = new AttachmentDownloadQuery();
		attachmentDownloadQuery.setUserId(sessionUser.getUserid());
		attachmentDownloadQuery.setAttachmentId(attachmentId);
		int downCount = this.attachmentDownloadMapper.selectCount(attachmentDownloadQuery);
		if(topicUserId != sessionUser.getUserid() && attachment.getDownloadMark() > 0 && downCount == 0){
			User user = this.userService.findUserByUserid(sessionUser.getUserid());
			if(user.getMark() < attachment.getDownloadMark()){
				throw new BussinessException("你的积分只有&nbsp;&nbsp;" + user.getMark() + "&nbsp;&nbsp;分, 积分不足");
			}
		}
		
	}
	public void deleteFile(Integer attachmentId) {
		this.attachmentMapper.delete(attachmentId);
	}
	
}
