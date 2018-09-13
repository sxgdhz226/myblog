package top.fzqblog.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import top.fzqblog.exception.BussinessException;
import top.fzqblog.po.enums.BlogStatusEnum;
import top.fzqblog.po.enums.OrderByEnum;
import top.fzqblog.po.enums.ResponseCode;
import top.fzqblog.po.model.Attachment;
import top.fzqblog.po.model.Blog;
import top.fzqblog.po.model.BlogCategory;
import top.fzqblog.po.model.Collection;
import top.fzqblog.po.model.Message;
import top.fzqblog.po.model.SessionUser;
import top.fzqblog.po.model.User;
import top.fzqblog.po.query.BlogQuery;
import top.fzqblog.po.query.CollectionQuery;
import top.fzqblog.po.query.MessageQuery;
import top.fzqblog.po.vo.AjaxResponse;
import top.fzqblog.service.AttachmentService;
import top.fzqblog.service.BlogCategoryService;
import top.fzqblog.service.BlogService;
import top.fzqblog.service.CollectionService;
import top.fzqblog.service.MessageService;
import top.fzqblog.service.UserService;
import top.fzqblog.utils.Constants;
import top.fzqblog.utils.PageResult;
import top.fzqblog.utils.ServerUtils;
import top.fzqblog.utils.StringUtils;


@Controller
@RequestMapping("/admin")
public class UserAdminController extends BaseController {
	
private Logger logger = LoggerFactory.getLogger(UserHomeController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BlogCategoryService blogCategoryService;
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private AttachmentService attachmentService;
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private CollectionService collectionService;
	
	@RequestMapping
	public ModelAndView updateUser(HttpSession session){
		ModelAndView view = new ModelAndView("/page/admin/update_user");
		SessionUser sessionUser = (SessionUser) session.getAttribute(Constants.SESSION_USER_KEY);
		if(sessionUser==null){
			view.setViewName("redirect:" +  Constants.LOGINABSOLUTEPATH);
			return view;
		}
		try {
			User user = this.userService.findUserInfo4UserHome(sessionUser.getUserid());
			view.addObject("user", user);
		} catch (BussinessException e) {
			logger.error("获取用户信息失败：{}", e);
			view.setViewName("redirect:" + Constants.ERROR_404);
			return view;
		}
		return view;
	}
	
	@ResponseBody
	@RequestMapping("updateUserInfo")
	public AjaxResponse<Object> updateUserInfo(HttpSession session,User user){
		AjaxResponse<Object> ajaxResponse = new AjaxResponse<Object>();
		SessionUser sessionUser = (SessionUser) session.getAttribute(Constants.SESSION_USER_KEY);
		user.setUserid(sessionUser.getUserid());
		try {
			this.userService.updateUserInfo(user);
			ajaxResponse.setResponseCode(ResponseCode.SUCCESS);
		} catch (BussinessException e) {
			logger.error("修改出错", e);
			ajaxResponse.setErrorMsg(e.getLocalizedMessage());
			ajaxResponse.setResponseCode(ResponseCode.BUSSINESSERROR);
		}catch (Exception e) {
			logger.error("修改出错{}", e);
			ajaxResponse.setErrorMsg("修改出错,请重试");
			ajaxResponse.setResponseCode(ResponseCode.SERVERERROR);
		}
		return ajaxResponse;
	}
	
	@RequestMapping("preUpdateUserPage")
	public ModelAndView preUpdateUserPage(HttpSession session){
		ModelAndView view = new ModelAndView("/page/admin/update_userpage");
		SessionUser sessionUser = (SessionUser) session.getAttribute(Constants.SESSION_USER_KEY);
		if(sessionUser==null){
			view.setViewName("redirect:" +  "Constants.LOGINABSOLUTEPATH");
			return view;
		}
		try {
			User user = this.userService.findUserInfo4UserHome(sessionUser.getUserid());
			view.addObject("user", user);
		} catch (BussinessException e) {
			logger.error("获取用户信息失败：{}", e);
			view.setViewName("redirect:" + Constants.ERROR_404);
			return view;
		}
		return view;
	}
	
	
	@ResponseBody
	@RequestMapping("saveUserPage")
	public AjaxResponse<Object> saveUserPage(HttpSession session, Integer userPage){
		AjaxResponse<Object> ajaxResponse = new AjaxResponse<Object>();
		Integer userId = this.getUserid(session);
		User user = new User();
		user.setUserid(userId);
		try {
			userPage = userPage == null ? 0 : userPage;
			user.setUserPage(userPage);
			this.userService.updateUserWithoutValidate(user);
			ajaxResponse.setResponseCode(ResponseCode.SUCCESS);
		} catch (Exception e) {
			logger.error("修改出错{}", e);
			ajaxResponse.setErrorMsg("修改出错,请重试");
			ajaxResponse.setResponseCode(ResponseCode.SERVERERROR);
		}
		return ajaxResponse;
	}
	
	
	@RequestMapping("preUpdatePassword")
	public ModelAndView preUpdatePassword(HttpSession session){
		ModelAndView view = new ModelAndView("/page/admin/update_password");
		SessionUser sessionUser = (SessionUser) session.getAttribute(Constants.SESSION_USER_KEY);
		if(sessionUser==null){
			view.setViewName("redirect:" +  "Constants.LOGINABSOLUTEPATH");
			return view;
		}
		try {
			User user = this.userService.findUserInfo4UserHome(sessionUser.getUserid());
			view.addObject("user", user);
		} catch (BussinessException e) {
			logger.error("获取用户信息失败：{}", e);
			view.setViewName("redirect:" + Constants.ERROR_404);
			return view;
		}
		return view;
	}
	
	
	@ResponseBody
	@RequestMapping("modifyPassword")
	public AjaxResponse<Object> modifyPassword(HttpSession session,String oldPassword, String newPassword){
		AjaxResponse<Object> ajaxResponse = new AjaxResponse<Object>();
		SessionUser sessionUser = (SessionUser) session.getAttribute(Constants.SESSION_USER_KEY);
		try {
			this.userService.updatePassword(sessionUser.getUserid(), oldPassword, newPassword);
			ajaxResponse.setResponseCode(ResponseCode.SUCCESS);
		} catch (BussinessException e) {
			logger.error("修改出错", e);
			ajaxResponse.setErrorMsg(e.getLocalizedMessage());
			ajaxResponse.setResponseCode(ResponseCode.BUSSINESSERROR);
		}catch (Exception e) {
			logger.error("修改出错", e);
			ajaxResponse.setErrorMsg("修改出错,请重试{}");
			ajaxResponse.setResponseCode(ResponseCode.SERVERERROR);
		}
		return ajaxResponse;
	}
	
	@RequestMapping("preUpdateUserIcon")
	public ModelAndView preUpdateUserIcon(HttpSession session){
		ModelAndView view = new ModelAndView("/page/admin/update_usericon");
		SessionUser sessionUser = (SessionUser) session.getAttribute(Constants.SESSION_USER_KEY);
		if(sessionUser==null){
			view.setViewName("redirect:" +  "Constants.LOGINABSOLUTEPATH");
			return view;
		}
		try {
			User user = this.userService.findUserInfo4UserHome(sessionUser.getUserid());
			view.addObject("user", user);
		} catch (BussinessException e) {
			logger.error("获取用户信息失败：{}", e);
			view.setViewName("redirect:" + Constants.ERROR_404);
			return view;
		}
		return view;
	}
	
	@ResponseBody
	@RequestMapping("saveSysUserIcon")
	public AjaxResponse<Object> saveSysUserIcon(HttpSession session, String userIcon){
		AjaxResponse<Object> ajaxResponse = new AjaxResponse<Object>();
		Integer userId = this.getUserid(session);
		User user = new User();
		user.setUserid(userId);
		try {
			String dest = "user_icon/" + userId + ".jpg";
			this.userService.copyUserIcon(userIcon, dest);
			user.setUserIcon(dest);
			this.userService.updateUserWithoutValidate(user);
			ajaxResponse.setResponseCode(ResponseCode.SUCCESS);
		} catch (Exception e) {
			logger.error("修改出错{}", e);
			ajaxResponse.setErrorMsg("修改出错,请重试");
			ajaxResponse.setResponseCode(ResponseCode.SERVERERROR);
		}
		return ajaxResponse;
	}
	
	@ResponseBody
	@RequestMapping("saveUserIcon")
	public AjaxResponse<Object> saveUserIcon(HttpSession session, String img, Integer x1,
				Integer y1, Integer width, Integer height){
		AjaxResponse<Object> ajaxResponse = new AjaxResponse<Object>();
		if(StringUtils.isEmpty(img)){
			ajaxResponse.setErrorMsg("修改出错,请重试");
			ajaxResponse.setResponseCode(ResponseCode.SERVERERROR);
			return ajaxResponse;
		}
		Integer userId = this.getUserid(session);
		User user = new User();
		user.setUserid(userId);
		try {
			String dest = "user_icon/" + userId + ".jpg";
			String source = ServerUtils.getRealPath() + "/upload/" + img;
			File file = new File(source);
			BufferedImage image = ImageIO.read(file);
			BufferedImage subImage = image.getSubimage(x1, y1, width, height);
			String descPath = ServerUtils.getRealPath() + "/resources/images/" + dest;
			File destFile = new File(descPath);
			ImageIO.write(subImage, "JPEG", destFile);
			ajaxResponse.setResponseCode(ResponseCode.SUCCESS);
		} catch (Exception e) {
			logger.error("修改出错{}", e);
			ajaxResponse.setErrorMsg("修改出错,请重试");
			ajaxResponse.setResponseCode(ResponseCode.SERVERERROR);
		}
		return ajaxResponse;
	}
	
	@RequestMapping("preUpdateUserBg")
	public ModelAndView preUpdateUserBg(HttpSession session){
		ModelAndView view = new ModelAndView("/page/admin/update_userbg");
		SessionUser sessionUser = (SessionUser) session.getAttribute(Constants.SESSION_USER_KEY);
		if(sessionUser==null){
			view.setViewName("redirect:" + Constants.LOGINABSOLUTEPATH);
			return view;
		}
		try {
			User user = this.userService.findUserInfo4UserHome(sessionUser.getUserid());
			view.addObject("user", user);
		} catch (BussinessException e) {
			logger.error("获取用户信息失败：{}", e);
			view.setViewName("redirect:" + Constants.ERROR_404);
			return view;
		}
		return view;
	}
	
	@ResponseBody
	@RequestMapping("saveSysUserBg")
	public AjaxResponse<Object> saveSysUserBg(HttpSession session, String background){
		AjaxResponse<Object> ajaxResponse = new AjaxResponse<Object>();
		Integer userId = this.getUserid(session);
		User user = new User();
		user.setUserid(userId);
		try {
			user.setUserBg(background);
			this.userService.updateUserWithoutValidate(user);
			ajaxResponse.setResponseCode(ResponseCode.SUCCESS);
		} catch (Exception e) {
			logger.error("修改出错{}", e);
			ajaxResponse.setErrorMsg("修改出错,请重试");
			ajaxResponse.setResponseCode(ResponseCode.SERVERERROR);
		}
		return ajaxResponse;
	}
	
	@RequestMapping("preAddBlog")
	public ModelAndView preAddBlog(HttpSession session){
		ModelAndView view = new ModelAndView("/page/admin/add_blog");
		SessionUser sessionUser = (SessionUser) session.getAttribute(Constants.SESSION_USER_KEY);
		if(sessionUser==null){
			view.setViewName("redirect:" + Constants.LOGINABSOLUTEPATH);
			return view;
		}
		try {
			User user = this.userService.findUserInfo4UserHome(sessionUser.getUserid());
			view.addObject("user", user);
			view.addObject("categories", this.blogCategoryService.findBlogCategoryList(sessionUser.getUserid()));
		} catch (BussinessException e) {
			logger.error("获取用户信息失败：{}", e);
			view.setViewName("redirect:" + Constants.ERROR_404);
			return view;
		}
		return view;
	}
	
	@ResponseBody
	@RequestMapping("addBlog")
	public AjaxResponse<Integer> addBlog(HttpSession session, Blog blog, Attachment attachment){
		AjaxResponse<Integer> ajaxResponse = new AjaxResponse<Integer>();
		try {
			blog.setStatus(BlogStatusEnum.PUBLIC);
			this.setUserBaseInfo(Blog.class, blog, session);
			if(blog.getBlogId()!=null){
				this.blogService.modifyBlog(blog, attachment);
			}
			else{
				this.blogService.addBlog(blog, attachment);
			}
			ajaxResponse.setResponseCode(ResponseCode.SUCCESS);
			ajaxResponse.setData(blog.getBlogId());
		}catch(BussinessException e){
			logger.error("添加博客出错", e);
			ajaxResponse.setErrorMsg(e.getLocalizedMessage());
			ajaxResponse.setResponseCode(ResponseCode.BUSSINESSERROR);
		}
		catch (Exception e) {
			logger.error("添加博客出错{}", e);
			ajaxResponse.setErrorMsg("添加博客出错,请重试");
			ajaxResponse.setResponseCode(ResponseCode.SERVERERROR);
		}
		return ajaxResponse;
	}
	
	@ResponseBody
	@RequestMapping("addDraftBlog")
	public AjaxResponse<Integer> addDraftBlog(HttpSession session, Blog blog){
		AjaxResponse<Integer> ajaxResponse = new AjaxResponse<Integer>();
		try {
			blog.setStatus(BlogStatusEnum.DRAFT);
			this.setUserBaseInfo(Blog.class, blog, session);
			if(blog.getBlogId()!=null){
				this.blogService.modifyBlog(blog, new Attachment());
			}
			else{
				this.blogService.addBlog(blog, new Attachment());
			}
			ajaxResponse.setResponseCode(ResponseCode.SUCCESS);
			ajaxResponse.setData(blog.getBlogId());
		}catch(BussinessException e){
			logger.error("添加博客草稿出错{}", e);
			ajaxResponse.setErrorMsg(e.getLocalizedMessage());
			ajaxResponse.setResponseCode(ResponseCode.BUSSINESSERROR);
		}
		catch (Exception e) {
			logger.error("添加博客草稿出错{}", e);
			ajaxResponse.setErrorMsg("添加博客草稿出错,请重试");
			ajaxResponse.setResponseCode(ResponseCode.SERVERERROR);
		}
		return ajaxResponse;
	}
	
	@RequestMapping("blogList")
	public ModelAndView blogList(HttpSession session){
		ModelAndView view = new ModelAndView("/page/admin/blog_list");
		SessionUser sessionUser = (SessionUser) session.getAttribute(Constants.SESSION_USER_KEY);
		if(sessionUser==null){
			view.setViewName("redirect:" + Constants.LOGINABSOLUTEPATH);
			return view;
		}
		try {
			User user = this.userService.findUserInfo4UserHome(sessionUser.getUserid());
			view.addObject("user", user);
			view.addObject("categories", this.blogCategoryService.findBlogCategoryList(sessionUser.getUserid()));
		} catch (BussinessException e) {
			logger.error("获取博客列表失败：{}", e);
			view.setViewName("redirect:" + Constants.ERROR_404);
			return view;
		}
		return view;
	}
	
	@ResponseBody
	@RequestMapping("loadBlog")
	public AjaxResponse<Object> loadBlog(HttpSession session, BlogQuery blogQuery){
		AjaxResponse<Object> ajaxResponse = new AjaxResponse<Object>();
		try {
			blogQuery.setUserId(this.getUserid(session));
			blogQuery.setStatus(BlogStatusEnum.PUBLIC);
			PageResult<Blog> result = this.blogService.findBlogByPage(blogQuery);
			ajaxResponse.setData(result);
			ajaxResponse.setResponseCode(ResponseCode.SUCCESS);
		}catch (Exception e) {
			logger.error("获取博客列表出错{}", e);
			ajaxResponse.setErrorMsg("获取博客列表出错,请重试");
			ajaxResponse.setResponseCode(ResponseCode.SERVERERROR);
		}
		return ajaxResponse;
	}
	
	@ResponseBody
	@RequestMapping("del_blog.action")
	public AjaxResponse<Object> deleteBlog(HttpSession session, Integer blogId){
		AjaxResponse<Object> ajaxResponse = new AjaxResponse<Object>();
		try {
			this.blogService.deleteBlog(blogId);
			ajaxResponse.setResponseCode(ResponseCode.SUCCESS);
		}catch(BussinessException e){
			logger.error("删除博客出错", e);
			ajaxResponse.setErrorMsg(e.getLocalizedMessage());
			ajaxResponse.setResponseCode(ResponseCode.BUSSINESSERROR);
		}
		catch (Exception e) {
			logger.error("删除博客出错{}", e);
			ajaxResponse.setErrorMsg("删除出错,请重试");
			ajaxResponse.setResponseCode(ResponseCode.SERVERERROR);
		}
		return ajaxResponse;
	}
	
	@RequestMapping("edit_blog.action")
	public ModelAndView preModifyBlog(HttpSession session,Integer blogId){
		ModelAndView view = new ModelAndView("/page/admin/edit_blog");
		SessionUser sessionUser = (SessionUser) session.getAttribute(Constants.SESSION_USER_KEY);
		if(sessionUser==null){
			view.setViewName("redirect:" + Constants.LOGINABSOLUTEPATH);
			return view;
		}
		try {
			User user = this.userService.findUserInfo4UserHome(sessionUser.getUserid());
			view.addObject("user", user);
			view.addObject("categories", this.blogCategoryService.findBlogCategoryList(sessionUser.getUserid()));
			Blog blog = blogService.showBlog(blogId);
			view.addObject("blog", blog);
		} catch (BussinessException e) {
			logger.error("获取博客信息失败：{}", e);
			view.setViewName("redirect:" + Constants.ERROR_404);
			return view;
		}
		return view;
	}
	
	@ResponseBody
	@RequestMapping("deleteBlogAttachment")
	public AjaxResponse<Object> deleteBlogAttachment(HttpSession session,Integer attachmentId){
		AjaxResponse<Object> ajaxResponse = new AjaxResponse<Object>();
		try {
			this.attachmentService.deleteFile(attachmentId);
			ajaxResponse.setResponseCode(ResponseCode.SUCCESS);
		} catch (Exception e) {
			logger.error("删除文件失败{}", e);
			ajaxResponse.setErrorMsg("删除文件失败");
			ajaxResponse.setResponseCode(ResponseCode.SERVERERROR);
		}
		return ajaxResponse;
	}
	
	@RequestMapping("draftBlogList")
	public ModelAndView draftBlogList(HttpSession session){
		ModelAndView view = new ModelAndView("/page/admin/draftblog_list");
		SessionUser sessionUser = (SessionUser) session.getAttribute(Constants.SESSION_USER_KEY);
		if(sessionUser==null){
			view.setViewName("redirect:" + Constants.LOGINABSOLUTEPATH);
			return view;
		}
		try {
			User user = this.userService.findUserInfo4UserHome(sessionUser.getUserid());
			view.addObject("user", user);
			view.addObject("categories", this.blogCategoryService.findBlogCategoryList(sessionUser.getUserid()));
		} catch (BussinessException e) {
			logger.error("获取博客列表失败：{}", e);
			view.setViewName("redirect:" + Constants.ERROR_404);
			return view;
		}
		return view;
	}
	
	@ResponseBody
	@RequestMapping("loadDraftBlog")
	public AjaxResponse<Object> loadDraftBlog(HttpSession session, BlogQuery blogQuery){
		AjaxResponse<Object> ajaxResponse = new AjaxResponse<Object>();
		try {
			blogQuery.setUserId(this.getUserid(session));
			blogQuery.setStatus(BlogStatusEnum.DRAFT);
			PageResult<Blog> result = this.blogService.findBlogByPage(blogQuery);
			ajaxResponse.setData(result);
			ajaxResponse.setResponseCode(ResponseCode.SUCCESS);
		}catch (Exception e) {
			logger.error("获取博客列表出错{}", e);
			ajaxResponse.setErrorMsg("获取博客列表出错,请重试");
			ajaxResponse.setResponseCode(ResponseCode.SERVERERROR);
		}
		return ajaxResponse;
	}
	
	@RequestMapping("blog_category")
	public ModelAndView blogCategory(HttpSession session){
		ModelAndView view = new ModelAndView("/page/admin/blog_category");
		SessionUser sessionUser = (SessionUser) session.getAttribute(Constants.SESSION_USER_KEY);
		if(sessionUser==null){
			view.setViewName("redirect:" + Constants.LOGINABSOLUTEPATH);
			return view;
		}
		try {
			User user = this.userService.findUserInfo4UserHome(sessionUser.getUserid());
			view.addObject("user", user);
			view.addObject("categories", this.blogCategoryService.findBlogCategoryList(sessionUser.getUserid()));
		} catch (BussinessException e) {
			logger.error("获取博客分类失败：{}", e);
			view.setViewName("redirect:" + Constants.ERROR_404);
			return view;
		}
		return view;
	}
	
	@ResponseBody
	@RequestMapping("loadBlogCategories.action")
	public AjaxResponse<Object> loadBlogCategories(HttpSession session){
		AjaxResponse<Object> ajaxResponse = new AjaxResponse<Object>();
		try {
			List<BlogCategory> list = this.blogCategoryService.findBlogCategoryList(this.getUserid(session));
			ajaxResponse.setData(list);
			ajaxResponse.setResponseCode(ResponseCode.SUCCESS);
		}catch (Exception e) {
			logger.error("获取博客分类出错{}", e);
			ajaxResponse.setErrorMsg("获取博客分类出错,请重试");
			ajaxResponse.setResponseCode(ResponseCode.SERVERERROR);
		}
		return ajaxResponse;
	}
	
	
	@ResponseBody
	@RequestMapping("delBlogCategory.action")
	public AjaxResponse<Object> delBlogCategory(HttpSession session, Integer categoryId){
		AjaxResponse<Object> ajaxResponse = new AjaxResponse<Object>();
		try {
			this.blogCategoryService.deleteBlogCategory(categoryId);
			ajaxResponse.setResponseCode(ResponseCode.SUCCESS);
		}catch (Exception e) {
			logger.error("删除博客分类出错{}", e);
			ajaxResponse.setErrorMsg("删除博客分类出错,请重试");
			ajaxResponse.setResponseCode(ResponseCode.SERVERERROR);
		}
		return ajaxResponse;
	}
	
	@ResponseBody
	@RequestMapping("saveBlogCategory.action")
	public AjaxResponse<Object> saveBlogCategory(HttpSession session,BlogCategory blogCategory){
		AjaxResponse<Object> ajaxResponse = new AjaxResponse<Object>();
		try {
			blogCategory.setUserId(this.getUserid(session));
			this.blogCategoryService.saveOrUpdate(blogCategory);
			ajaxResponse.setResponseCode(ResponseCode.SUCCESS);
		}catch (Exception e) {
			logger.error("保存博客分类出错{}", e);
			ajaxResponse.setErrorMsg("保存博客分类出错,请重试");
			ajaxResponse.setResponseCode(ResponseCode.SERVERERROR);
		}
		return ajaxResponse;
	}
	
	@RequestMapping("messageList")
	public ModelAndView messageList(HttpSession session){
		ModelAndView view = new ModelAndView("/page/admin/message_list");
		SessionUser sessionUser = (SessionUser) session.getAttribute(Constants.SESSION_USER_KEY);
		if(sessionUser==null){
			view.setViewName("redirect:" + Constants.LOGINABSOLUTEPATH);
			return view;
		}
		try {
			User user = this.userService.findUserInfo4UserHome(sessionUser.getUserid());
			view.addObject("user", user);
		} catch (BussinessException e) {
			logger.error("获取消息列表失败：{}", e);
			view.setViewName("redirect:" + Constants.ERROR_404);
			return view;
		}
		return view;
	}
	
	
	@ResponseBody
	@RequestMapping("load_user_message_list.action")
	public AjaxResponse<Object> load_user_message_list(HttpSession session, MessageQuery messageQuery){
		AjaxResponse<Object> ajaxResponse = new AjaxResponse<Object>();
		try {
			messageQuery.setReceivedUserId(this.getUserid(session));
			messageQuery.setOrderBy(OrderByEnum.MESSAGE_STATUS_ASC_CREATE_TIME_DESC);
			PageResult<Message> result = this.messageService.findMessageByPage(messageQuery);
			ajaxResponse.setData(result);
			ajaxResponse.setResponseCode(ResponseCode.SUCCESS);
		}catch (Exception e) {
			logger.error("获取消息列表出错{}", e);
			ajaxResponse.setErrorMsg("获取消息列表出错,请重试");
			ajaxResponse.setResponseCode(ResponseCode.SERVERERROR);
		}
		return ajaxResponse;
	}
	
	
	@ResponseBody
	@RequestMapping("mark_message_read.action")
	public AjaxResponse<Object> mark_message_read(HttpSession session, Integer[] ids){
		AjaxResponse<Object> ajaxResponse = new AjaxResponse<Object>();
		try {
			this.messageService.update(ids, this.getUserid(session));
			ajaxResponse.setResponseCode(ResponseCode.SUCCESS);
		}catch (Exception e) {
			logger.error("消息标记已读出错{}", e);
			ajaxResponse.setErrorMsg("标记已读出错,请重试");
			ajaxResponse.setResponseCode(ResponseCode.SERVERERROR);
		}
		return ajaxResponse;
	}
	
	@ResponseBody
	@RequestMapping("load_user_message_count.action")
	public AjaxResponse<Object> load_user_message_count(HttpSession session, MessageQuery messageQuery){
		AjaxResponse<Object> ajaxResponse = new AjaxResponse<Object>();
		try {
			messageQuery.setReceivedUserId(this.getUserid(session));
			messageQuery.setOrderBy(OrderByEnum.MESSAGE_STATUS_ASC_CREATE_TIME_DESC);
			Integer count = this.messageService.findMessageCount(messageQuery);
			ajaxResponse.setData(count);
			ajaxResponse.setResponseCode(ResponseCode.SUCCESS);
		}catch (Exception e) {
			logger.error("获取消息列表出错{}", e);
			ajaxResponse.setErrorMsg("获取消息列表出错,请重试");
			ajaxResponse.setResponseCode(ResponseCode.SERVERERROR);
		}
		return ajaxResponse;
	}
	
	
	@ResponseBody
	@RequestMapping("del_message.action")
	public AjaxResponse<Object> del_message(HttpSession session, Integer[] ids){
		AjaxResponse<Object> ajaxResponse = new AjaxResponse<Object>();
		try {
			this.messageService.delMessage(this.getUserid(session), ids);
			ajaxResponse.setResponseCode(ResponseCode.SUCCESS);
		}catch (Exception e) {
			logger.error("消息删除出错{}", e);
			ajaxResponse.setErrorMsg("消息删除出错,请重试");
			ajaxResponse.setResponseCode(ResponseCode.SERVERERROR);
		}
		return ajaxResponse;
	}
	
	@RequestMapping("readMessage.action")
	public ModelAndView readMessage(HttpSession session, Integer id){
		ModelAndView view = new ModelAndView(Constants.ERROR_404);
		SessionUser sessionUser = (SessionUser) session.getAttribute(Constants.SESSION_USER_KEY);
		if(sessionUser==null){
			view.setViewName("redirect:" + Constants.LOGINABSOLUTEPATH);
			return view;
		}
		try {
			User user = this.userService.findUserInfo4UserHome(sessionUser.getUserid());
			view.addObject("user", user);
			view.setViewName("redirect:" + this.messageService.getMessageById(id, this.getUserid(session)).getUrl());
			this.messageService.update(new Integer[]{id}, this.getUserid(session));
		} catch (BussinessException e) {
			logger.error("获取消息列表失败：{}", e);
			view.setViewName("redirect:" + Constants.ERROR_404);
			return view;
		}
		return view;
	}
	
	@RequestMapping("collection_list.action")
	public ModelAndView collection_list(HttpSession session, CollectionQuery collectionQuery){
		ModelAndView view = new ModelAndView("/page/admin/collection_list");
		SessionUser sessionUser = (SessionUser) session.getAttribute(Constants.SESSION_USER_KEY);
		if(sessionUser==null){
			view.setViewName("redirect:" + Constants.LOGINABSOLUTEPATH);
			return view;
		}
		try {
			User user = this.userService.findUserInfo4UserHome(sessionUser.getUserid());
			view.addObject("user", user);
			view.addObject("articleType", collectionQuery.getArticleType().getType());
		} catch (BussinessException e) {
			logger.error("获取收藏列表失败：{}", e);
			view.setViewName("redirect:" + Constants.ERROR_404);
			return view;
		}
		return view;
	}
	
	@ResponseBody
	@RequestMapping("load_collection.action")
	public AjaxResponse<Object>load_collection(HttpSession session, CollectionQuery collectionQuery){
		AjaxResponse<Object> ajaxResponse = new AjaxResponse<Object>();
		try {
			PageResult<Collection> result = this.collectionService.findCollectionByPage(collectionQuery);
			ajaxResponse.setData(result);
			ajaxResponse.setResponseCode(ResponseCode.SUCCESS);
		}catch (Exception e) {
			logger.error("获取收藏列表出错{}", e);
			ajaxResponse.setErrorMsg("获取收藏列表出错,请重试");
			ajaxResponse.setResponseCode(ResponseCode.SERVERERROR);
		}
		return ajaxResponse;
	}
	
	@ResponseBody
	@RequestMapping("del_collection.action")
	public AjaxResponse<Object> del_collection(HttpSession session, Collection collection){
		AjaxResponse<Object> ajaxResponse = new AjaxResponse<Object>();
		try {
			this.collectionService.deleteCollection(collection);
			ajaxResponse.setResponseCode(ResponseCode.SUCCESS);
		}catch (Exception e) {
			logger.error("收藏删除出错{}", e);
			ajaxResponse.setErrorMsg("收藏删除出错,请重试");
			ajaxResponse.setResponseCode(ResponseCode.SERVERERROR);
		}
		return ajaxResponse;
	}
	
	
}
