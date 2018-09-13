package top.fzqblog.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import top.fzqblog.exception.BussinessException;
import top.fzqblog.po.enums.ResponseCode;
import top.fzqblog.po.model.Comment;
import top.fzqblog.po.model.SessionUser;
import top.fzqblog.po.query.CommentQuery;
import top.fzqblog.po.vo.AjaxResponse;
import top.fzqblog.service.CommentService;
import top.fzqblog.utils.Constants;
import top.fzqblog.utils.PageResult;

@Controller
public class CommentController extends BaseController {
	
	private Logger logger = LoggerFactory.getLogger(CommentController.class);
	
	@Autowired
	private CommentService commentService;
	
	@ResponseBody
	@RequestMapping("loadComment")
	public AjaxResponse<PageResult<Comment>> loadComment(HttpSession session, CommentQuery commentQuery){
		 AjaxResponse<PageResult<Comment>> ajaxResponse = new AjaxResponse<PageResult<Comment>>();
		 try {
			 PageResult<Comment> pageResult = this.commentService.findCommentByPage(commentQuery);
			 ajaxResponse.setData(pageResult);
			 ajaxResponse.setResponseCode(ResponseCode.SUCCESS);
		} catch (Exception e) {
			logger.error("{}加载评论出错", e);
			ajaxResponse.setErrorMsg("加载评论出错");
			ajaxResponse.setResponseCode(ResponseCode.SERVERERROR);
		}
		 return ajaxResponse;
	}
	
	@ResponseBody
	@RequestMapping("addComment")
	public AjaxResponse<Object> addComment(HttpSession session, Comment comment){
		AjaxResponse<Object> ajaxResponse = new AjaxResponse<Object>();
		SessionUser sessionUser = (SessionUser) session.getAttribute(Constants.SESSION_USER_KEY);
		if(sessionUser==null){
			ajaxResponse.setResponseCode(ResponseCode.BUSSINESSERROR);
			ajaxResponse.setErrorMsg("请先登录");
			return ajaxResponse;
		}
		this.setUserBaseInfo(Comment.class, comment, session);
		try {
			this.commentService.addComment(comment);
			ajaxResponse.setData(comment);
			 ajaxResponse.setResponseCode(ResponseCode.SUCCESS);
		} catch (BussinessException e) {
			ajaxResponse.setResponseCode(ResponseCode.BUSSINESSERROR);
			ajaxResponse.setErrorMsg(e.getLocalizedMessage());
			logger.error("{}评论出错", sessionUser.getUserName());
		}catch (Exception e) {
			ajaxResponse.setResponseCode(ResponseCode.SERVERERROR);
			ajaxResponse.setErrorMsg("服务器出错");
			logger.error("{}评论出错", sessionUser.getUserName());
		}
		return ajaxResponse;
	}
}
