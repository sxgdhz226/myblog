package top.fzqblog.directive;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import top.fzqblog.po.model.SessionUser;
import top.fzqblog.po.model.SysRes;
import top.fzqblog.service.SysResService;
import top.fzqblog.service.SysRoleService;
import top.fzqblog.utils.Constants;

@Component
public class PermissionDirective implements TemplateDirectiveModel {

	@Autowired
	private SysResService sysResService;
	
	@Autowired
	private SysRoleService sysRoleService;
	
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		if(params != null && params.containsKey("key")){
			String key = params.get("key").toString();
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
			SessionUser sessionUser = (SessionUser) request.getSession().getAttribute(Constants.SESSION_USER_KEY);
			if(sessionUser!= null){
				Set<Integer> roleSet = sysRoleService.findRoleIdsByUserId(sessionUser.getUserid());
				List<SysRes> list = sysResService.findMenuByRoleIds(roleSet);
				Set<String> permkey = new HashSet<>();
				if(list != null){
					for(SysRes sysRes : list){
						permkey.add(sysRes.getKey());
					}
					if(permkey !=null && permkey.contains(key)){
						body.render(env.getOut());
					}
				}
			}
		}
	}

}
