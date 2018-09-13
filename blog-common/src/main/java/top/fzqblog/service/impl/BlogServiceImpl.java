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
import top.fzqblog.mapper.BlogMapper;
import top.fzqblog.po.enums.ArticleType;
import top.fzqblog.po.enums.FileTopicType;
import top.fzqblog.po.enums.MarkEnum;
import top.fzqblog.po.enums.MessageType;
import top.fzqblog.po.enums.OrderByEnum;
import top.fzqblog.po.enums.PageSize;
import top.fzqblog.po.enums.TextLengthEnum;
import top.fzqblog.po.model.Attachment;
import top.fzqblog.po.model.Blog;
import top.fzqblog.po.model.MessageParams;
import top.fzqblog.po.query.BlogQuery;
import top.fzqblog.po.query.UpdateQuery4ArticleCount;
import top.fzqblog.service.AttachmentService;
import top.fzqblog.service.BlogService;
import top.fzqblog.service.MessageService;
import top.fzqblog.service.UserService;
import top.fzqblog.utils.ImageUtils;
import top.fzqblog.utils.Page;
import top.fzqblog.utils.PageResult;
import top.fzqblog.utils.StringUtils;

@Service
public class BlogServiceImpl implements BlogService {
	
	@Autowired
	private BlogMapper<Blog, BlogQuery> blogMapper;
	
	@Autowired
	private AttachmentService attachmentService;
	
	@Autowired
	private FormateAtService formateAtService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MessageService messageService;

	public PageResult<Blog> findBlogByPage(BlogQuery blogQuery) {
		int count = this.blogMapper.selectCount(blogQuery);
		int pageSize = PageSize.PAGE_SIZE20.getSize();
		int pageNum = blogQuery.getPageNum() == 1 ? 1 : blogQuery.getPageNum();
		Page page = new Page(pageNum, count, pageSize);
		blogQuery.setPage(page);
		blogQuery.setOrderBy(OrderByEnum.CREATE_TIME_DESC);
		List<Blog> list = this.blogMapper.selectList(blogQuery);
		PageResult<Blog> pageResult = new PageResult<Blog>(page, list);
		return pageResult;
	}

	public Blog getBlog(Integer blogId) {
		if(blogId == null){
			return null;
		}
		BlogQuery blogQuery = new BlogQuery();
		blogQuery.setBlogId(blogId);
		blogQuery.setShowContent(Boolean.TRUE);
		List<Blog> list = this.blogMapper.selectList(blogQuery);
		if(list.isEmpty()){
			return null;
		}
		return list.get(0);
	}

	public Blog showBlog(Integer blogId) throws BussinessException {
		Blog blog = this.getBlog(blogId);
		if(blog == null){
			throw new BussinessException("文章不存在,或已删除");
		}
		blog.setAttachment(this.attachmentService.getAttachmentByTopicIdAndFileType(blog.getBlogId(), FileTopicType.BLOG));
		UpdateQuery4ArticleCount updateQuery4ArticleCount = new UpdateQuery4ArticleCount();
		updateQuery4ArticleCount.setAddReadCount(Boolean.TRUE);
		updateQuery4ArticleCount.setArticleId(blogId);
		this.blogMapper.updateInfoCount(updateQuery4ArticleCount);
		return blog;
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=BussinessException.class)
	public void addBlog(Blog blog, Attachment attachment)
			throws BussinessException {
		if(blog.getTitle() == null || blog.getTitle().length() > TextLengthEnum.TEXT_100_LENGTH.getLength()
				||StringUtils.isEmpty(blog.getContent()) || blog.getContent().length() > TextLengthEnum.LONGTEXT.getLength()
				){
			throw new BussinessException("参数错误");
		}
		String title = HtmlUtils.htmlUnescape(blog.getTitle());
		String content = blog.getContent();
		content = StringUtils.replaceLast(content.replaceFirst("<p>", ""), "</p>", "");
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
		blog.setTitle(title);
		blog.setSummary(summary);
		blog.setContent(formatContent);
		String blogImage = ImageUtils.getImages(content);
		blog.setBlogImage(blogImage);
//		String blogImageSmall = ImageUtils.createThumbnail(topicImage, true);
//		blog.setTopicImageThum(blogImageSmall);
		Date curDate = new Date();
		blog.setCreateTime(curDate);
		this.blogMapper.insert(blog);
		this.userService.changeMark(blog.getUserId(),
				MarkEnum.MARK_TOPIC.getMark());
		
		if(!StringUtils.isEmpty(attachment.getFileName()) &&
				!StringUtils.isEmpty(attachment.getFileUrl())){
			attachment.setTopicId(blog.getBlogId());
			attachment.setFileTopicType(FileTopicType.BLOG);
			this.attachmentService.addAttachment(attachment);
		}
		
		MessageParams messageParams = new MessageParams();
		messageParams.setArticleId(blog.getBlogId());
		messageParams.setArticleType(ArticleType.BLOG);
		messageParams.setArticleUserId(blog.getUserId());
		messageParams.setMessageType(MessageType.AT_ARTICLE_MESSAGE);
		messageParams.setSendUserName(blog.getUserName());
		messageParams.setSendUserId(blog.getUserId());
		messageParams.setReceiveUserIds(userIds);
		messageService.createMessage(messageParams);
		
	}

	public void modifyBlog(Blog blog, Attachment attachment) throws BussinessException {
		if(getBlog(blog.getBlogId()) == null){
			throw new BussinessException("博客不存在或已删除");
		}
		this.blogMapper.update(blog);
		if(!StringUtils.isEmpty(attachment.getFileName()) &&
				!StringUtils.isEmpty(attachment.getFileUrl())){
			attachment.setTopicId(blog.getBlogId());
			attachment.setFileTopicType(FileTopicType.BLOG);
			this.attachmentService.addAttachment(attachment);
		}
	}

	public void deleteBlog(Integer blogId) throws BussinessException {
		if(getBlog(blogId) == null)	{
			throw new BussinessException("博客不存在或已删除");
		}
		this.blogMapper.delete(blogId);
	}
	
	@Override
	public List<Blog> findBlogList() {
		BlogQuery blogQuery = new BlogQuery();
		blogQuery.setOrderBy(OrderByEnum.CREATE_TIME_DESC);
		List<Blog> list = this.blogMapper.selectList(blogQuery);
		return list;
	}

	@Override
	public void deleteBatch(Integer[] blogIds) throws BussinessException {
		if(blogIds == null){
			throw new BussinessException("参数错误");
		}
		for(int id : blogIds){
			deleteBlog(id);
		}
	}
}
