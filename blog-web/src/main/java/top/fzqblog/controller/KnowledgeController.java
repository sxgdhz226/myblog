package top.fzqblog.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import bsh.This;
import top.fzqblog.cache.CategoryCache;
import top.fzqblog.exception.BussinessException;
import top.fzqblog.po.enums.ResponseCode;
import top.fzqblog.po.model.Attachment;
import top.fzqblog.po.model.Category;
import top.fzqblog.po.model.Knowledge;
import top.fzqblog.po.model.SessionUser;
import top.fzqblog.po.model.Topic;
import top.fzqblog.po.model.TopicVote;
import top.fzqblog.po.query.KnowledgeQuery;
import top.fzqblog.po.vo.AjaxResponse;
import top.fzqblog.service.AttachmentService;
import top.fzqblog.service.CategoryService;
import top.fzqblog.service.KnowledgeService;
import top.fzqblog.utils.Constants;
import top.fzqblog.utils.Page;
import top.fzqblog.utils.PageResult;

@Controller
@RequestMapping("/knowledge")
public class KnowledgeController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(BbsController.class);
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private KnowledgeService knowledgeService;
	
	@Autowired
	private AttachmentService attachmentService;
	
	@Autowired
	private CategoryCache categoryCache;
	
	@RequestMapping
	public ModelAndView knowledge(HttpSession session, KnowledgeQuery knowledgeQuery){
		ModelAndView view = new ModelAndView("/page/knowledge/knowledge");
		PageResult<Knowledge> pageResult = this.knowledgeService.findKnowledgeByPage(knowledgeQuery);
		view.addObject("categories", categoryCache.getKnowledgeCategories());
		view.addObject("result", pageResult);
		return view;
	}
	
	@RequestMapping(value = "/pCategoryId/{pCategoryId}", method=RequestMethod.GET)
	public ModelAndView pCategoryId(@PathVariable Integer pCategoryId, KnowledgeQuery knowledgeQuery){
		ModelAndView view = new ModelAndView("/page/knowledge/knowledge");
		PageResult<Knowledge> pageResult = this.knowledgeService.findKnowledgeByPage(knowledgeQuery);
		view.addObject("categories", categoryCache.getKnowledgeCategories());
		view.addObject("KnwoledgeTitleCategory", CategoryCache.getCategoryById(pCategoryId));
		view.addObject("result", pageResult);
		return view;
	}
	
	@RequestMapping(value = "/categoryId/{categoryId}", method=RequestMethod.GET)
	public ModelAndView categoryId(@PathVariable Integer categoryId, KnowledgeQuery knowledgeQuery){
		ModelAndView view = new ModelAndView("/page/knowledge/knowledge");
		PageResult<Knowledge> pageResult = this.knowledgeService.findKnowledgeByPage(knowledgeQuery);
		view.addObject("categories", categoryCache.getKnowledgeCategories());
		view.addObject("KnwoledgeTitleCategory", CategoryCache.getCategoryById(categoryId));
		view.addObject("result", pageResult);
		return view;
	}
	
	@RequestMapping("/{knowledgeId}")
	public ModelAndView knowledgeDetail(@PathVariable Integer knowledgeId, HttpSession session){
		ModelAndView view = new ModelAndView("/page/knowledge/knowledge_detail");
		SessionUser sessionUser = (SessionUser) session.getAttribute(Constants.SESSION_USER_KEY);
		Integer userId = null;
		try {
			userId = sessionUser==null ? null : sessionUser.getUserid();
			Knowledge topic = this.knowledgeService.showKnowledge(knowledgeId, userId);
			view.addObject("topic", topic);
		} catch (Exception e) {
			logger.error("{}文章加载出错", e);
			view.setViewName("redirect:" + Constants.ERROR_404);
		}
		return view;
	}
	
	@RequestMapping("/prePublicKnowledge")
	public ModelAndView prePublicknowledge(HttpSession session){
		ModelAndView view = new ModelAndView("/page/knowledge/publicKnowledge");
		return view;
	}
	
	
	@ResponseBody
	@RequestMapping("loadCategories")
	public AjaxResponse<List<Category>> loadCategories(){
		AjaxResponse<List<Category>> ajaxResponse = new AjaxResponse<List<Category>>();
		try {
			ajaxResponse.setData(this.categoryCache.getKnowledgeCategories());
			ajaxResponse.setResponseCode(ResponseCode.SUCCESS);
			return ajaxResponse;
		} catch (Exception e) {
			ajaxResponse.setErrorMsg("加载分类出错");
			ajaxResponse.setResponseCode(ResponseCode.SERVERERROR);
			logger.error("{}加载分类出错",e);
		}
		return ajaxResponse;
	}
	
	@ResponseBody
	@RequestMapping("publicKnowledge")
	public AjaxResponse<Integer> publicKnowledge(HttpSession session, Knowledge knowledge, Attachment attachment){
		AjaxResponse<Integer> ajaxResponse = new AjaxResponse<Integer>();
		SessionUser sessionUser = (SessionUser) session.getAttribute(Constants.SESSION_USER_KEY);
		try {
			this.setUserBaseInfo(Knowledge.class, knowledge, session);
			this.knowledgeService.addKnowledge(knowledge, attachment);
			ajaxResponse.setResponseCode(ResponseCode.SUCCESS);
			ajaxResponse.setData(knowledge.getTopicId());
		} catch (BussinessException e) {
			ajaxResponse.setErrorMsg(e.getLocalizedMessage());
			ajaxResponse.setResponseCode(ResponseCode.BUSSINESSERROR);
			logger.error("{}投稿失败", sessionUser.getUserName());
		} catch (Exception e) {
			ajaxResponse.setErrorMsg("服务器出错,投稿失败");
			ajaxResponse.setResponseCode(ResponseCode.SERVERERROR);
			logger.error("{}投稿失败", sessionUser.getUserName());
		}
		return ajaxResponse;
	}
	
	
}
