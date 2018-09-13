package top.fzqblog.aspect;


import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import top.fzqblog.annotation.RequirePermissions;
import top.fzqblog.po.enums.Logical;
import top.fzqblog.po.model.SessionUser;
import top.fzqblog.po.model.SysRes;
import top.fzqblog.service.SysResService;
import top.fzqblog.service.SysRoleService;
import top.fzqblog.utils.Constants;

@Aspect
@Component
public class AuthorityAspect {
	
	@Autowired
	private SysRoleService sysRoleService;
	
	@Autowired
	private SysResService sysResService;

	@Around(value="@annotation(top.fzqblog.annotation.RequirePermissions)&&@annotation(perm)")
	public Object hasPermission(ProceedingJoinPoint point, RequirePermissions perm) throws Throwable{
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
			}
			String[] values = perm.key();
			if(permkey != null){
				if(perm.logical().equals(Logical.AND)){
					for(String value : values){
						if(!permkey.contains(value)){
							response.sendRedirect("/manage/noperm" + "?v=" + new Date().getTime());
						}
					}
				}
				else{
					for(String value : values){
						if(permkey.contains(value)){
							return  point.proceed();
						}
						else{
							response.sendRedirect("/manage/noperm" + "?v=" + new Date().getTime());
						}
					}
				}
			}
		}
		else{
			response.sendRedirect("/login?redirect=" + request.getRequestURI());
		}
		return point.proceed();
	}
	
}
