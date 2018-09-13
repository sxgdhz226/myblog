package top.fzqblog.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import top.fzqblog.exception.BussinessException;
import top.fzqblog.po.enums.DateTimePatternEnum;
import top.fzqblog.po.enums.ResponseCode;
import top.fzqblog.po.enums.SolveEnum;
import top.fzqblog.po.model.Ask;
import top.fzqblog.po.model.SessionUser;
import top.fzqblog.po.query.AskQuery;
import top.fzqblog.po.vo.AjaxResponse;
import top.fzqblog.service.AskService;
import top.fzqblog.service.UserService;
import top.fzqblog.utils.Constants;
import top.fzqblog.utils.DateUtil;
import top.fzqblog.utils.PageResult;

@Controller
@RequestMapping("ask")
public class AskController extends BaseController {

	@Autowired
	private AskService askService;
	
	@Autowired
	private UserService userService;
	
	
	private Logger logger = LoggerFactory.getLogger(AskController.class);

	
	@RequestMapping
	public ModelAndView ask(HttpSession session, AskQuery askQuery){
		ModelAndView view = new ModelAndView("/page/ask/ask");
		if(askQuery.getSolveType() == null){
			askQuery.setSolveType(SolveEnum.WAIT_SOLVE);
		}
		PageResult<Ask> result = this.askService.findAskByPage(askQuery);
		view.addObject("result", result);
		view.addObject("haveSolved", askQuery.getSolveType().getType());
		AskQuery todayAskQuery = new AskQuery();
		view.addObject("totalAsk", this.askService.findCount(todayAskQuery));
		todayAskQuery.setSolveType(SolveEnum.SOLVED);
		view.addObject("totalSolved", this.askService.findCount(todayAskQuery));	
		todayAskQuery = new AskQuery();
		Date curDate = new Date();
		todayAskQuery.setStartDate(DateUtil.format(curDate, DateTimePatternEnum.YYYY_MM_DD.getPattern()));
		todayAskQuery.setEndDate(DateUtil.format(curDate, DateTimePatternEnum.YYYY_MM_DD.getPattern()));
		view.addObject("todayAskCount", this.askService.findCount(todayAskQuery));
		todayAskQuery.setSolveType(SolveEnum.SOLVED);
		view.addObject("todaySolvedCount", this.askService.findCount(todayAskQuery));
		view.addObject("topUsers", this.askService.findTopUsers());
		return view;
	}
	
	@RequestMapping("/prePublicAsk")
	public ModelAndView prePublicAsk(HttpSession session){
		ModelAndView view = new ModelAndView("/page/ask/publicAsk");
		SessionUser sessionUser = (SessionUser) session.getAttribute(Constants.SESSION_USER_KEY);
		if(sessionUser==null){
			 view = new ModelAndView("/page/login");
			return view;
		}
		view.addObject("myCount", userService.findUserByUserid(sessionUser.getUserid()).getMark());
		return view;
	}
	
	@ResponseBody
	@RequestMapping("publicAsk")
	public AjaxResponse<Integer> publicAsk(HttpSession session, Ask ask){
		AjaxResponse<Integer> ajaxResponse = new AjaxResponse<Integer>();
		this.setUserBaseInfo(Ask.class, ask, session);
		try {
			this.askService.addAsk(ask);
			ajaxResponse.setData(ask.getAskId());
			ajaxResponse.setResponseCode(ResponseCode.SUCCESS);
		} catch (BussinessException e) {
			ajaxResponse.setErrorMsg(e.getLocalizedMessage());
			ajaxResponse.setResponseCode(ResponseCode.BUSSINESSERROR);
			logger.error("{}提问出错{}", ask.getUserName(), e);
		}catch (Exception e) {
			ajaxResponse.setErrorMsg("服务器出错，提问失败");
			ajaxResponse.setResponseCode(ResponseCode.SERVERERROR);
			logger.error("{}提问出错{}", ask.getUserName(), e);
		}
		return ajaxResponse;
	}
	
	@RequestMapping(value="/{askId}", method=RequestMethod.GET)
	public ModelAndView askDetail(@PathVariable Integer askId, HttpSession session){
		ModelAndView view = new ModelAndView("/page/ask/ask_detail");
		SessionUser sessionUser = (SessionUser) session.getAttribute(Constants.SESSION_USER_KEY);
		try {
			view.addObject("ask", this.askService.showAsk(askId));
		} catch (BussinessException e) {
			logger.error("{}问题加载出错{}", sessionUser.getUserName(), e.getLocalizedMessage());
			view.setViewName("redirect:" + Constants.ERROR_404);
		}
		return view;
	}
	
	@ResponseBody
	@RequestMapping("acceptAnswer")
	public AjaxResponse<Object> acceptAnswer(HttpSession session, Ask ask){
		AjaxResponse<Object> ajaxResponse = new AjaxResponse<Object>();
		this.setUserBaseInfo(Ask.class, ask, session);
		try {
			this.askService.setBestAnswer(ask.getBestAnswerId(), ask.getAskId(), ask.getUserId());
			ajaxResponse.setResponseCode(ResponseCode.SUCCESS);
		} catch (BussinessException e) {
			ajaxResponse.setErrorMsg(e.getLocalizedMessage());
			ajaxResponse.setResponseCode(ResponseCode.BUSSINESSERROR);
			logger.error("采纳答案出错{}", e);
		}catch (Exception e) {
			ajaxResponse.setErrorMsg("服务器出错了");
			ajaxResponse.setResponseCode(ResponseCode.SERVERERROR);
			logger.error("采纳答案出错{}", e);
		}
		return ajaxResponse;
	}
}
