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
import top.fzqblog.po.model.Like;
import top.fzqblog.po.model.SessionUser;
import top.fzqblog.po.vo.AjaxResponse;
import top.fzqblog.service.LikeService;
import top.fzqblog.utils.Constants;

@Controller
public class LikeController extends BaseController {
	
	private Logger logger = LoggerFactory.getLogger(LikeController.class); 
	
	@Autowired
	private LikeService likeService;
	
	@ResponseBody
	@RequestMapping("doLike")
	public AjaxResponse<Object> doLike(HttpSession session, Like like){
		AjaxResponse<Object> ajaxResponse = new AjaxResponse<Object>();
		SessionUser sessionUser = (SessionUser) session.getAttribute(Constants.SESSION_USER_KEY);
		if(sessionUser==null){
			ajaxResponse.setResponseCode(ResponseCode.BUSSINESSERROR);
			ajaxResponse.setErrorMsg("请先登录");
			return ajaxResponse;
		}
		try {
			this.likeService.addLike(like);
			ajaxResponse.setResponseCode(ResponseCode.SUCCESS);
		} catch (BussinessException e) {
			ajaxResponse.setErrorMsg(e.getLocalizedMessage());
			ajaxResponse.setResponseCode(ResponseCode.BUSSINESSERROR);
			logger.error("{}赞出错{}", sessionUser.getUserName(), e.getLocalizedMessage());
		}catch (Exception e) {
			logger.error("{}赞出错{}", sessionUser.getUserName(), e.getLocalizedMessage());
			ajaxResponse.setErrorMsg("服务器出错");
			ajaxResponse.setResponseCode(ResponseCode.SERVERERROR);
		}
		return ajaxResponse;
	}
}
