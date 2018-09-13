package top.fzqblog.directive;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import top.fzqblog.exception.BussinessException;
import top.fzqblog.po.model.SessionUser;
import top.fzqblog.po.model.SysRes;
import top.fzqblog.service.SysResService;
import top.fzqblog.service.SysRoleService;
import top.fzqblog.utils.Constants;
import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component
public class MyPermissionDirective implements TemplateDirectiveModel {

	@Autowired
	private SysResService sysResService;
	
	@Autowired
	private SysRoleService sysRoleService;
	
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
			SessionUser sessionUser = (SessionUser) request.getSession().getAttribute(Constants.SESSION_USER_KEY);
			TemplateModel templateModel = null;
			if(sessionUser!= null){
				Set<Integer> roleSet = sysRoleService.findRoleIdsByUserId(sessionUser.getUserid());
				List<SysRes> list = sysResService.findMenuByRoleIds(roleSet);
				if(list != null){
					templateModel	 = ObjectWrapper.DEFAULT_WRAPPER.wrap(list);
					env.setGlobalVariable("menu",templateModel);
					body.render(env.getOut());
				}
			}
			else{
				response.sendRedirect("/login?redirect=" + request.getRequestURI());
			}

	}

}
