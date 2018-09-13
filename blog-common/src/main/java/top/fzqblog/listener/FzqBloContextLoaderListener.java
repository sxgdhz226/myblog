package top.fzqblog.listener;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.quartz.SchedulerException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

import top.fzqblog.cache.CategoryCache;
import top.fzqblog.exception.BussinessException;
import top.fzqblog.po.model.Task;
import top.fzqblog.quartz.TaskMessage;
import top.fzqblog.quartz.trigger.CronTriggerManager;
import top.fzqblog.service.TaskService;
import top.fzqblog.utils.SpringContextUtil;

public class FzqBloContextLoaderListener extends ContextLoaderListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {
		super.contextInitialized(event);
		//初始化SpringContextUtil的context
		ServletContext context = event.getServletContext();
		ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
		SpringContextUtil.setContext(ctx);
		//项目初始化时需要做的事情
		CategoryCache categoryCache = (CategoryCache) ctx.getBean("categoryCache");
		categoryCache.refreshCategoryCache();
		
		TaskService taskService = (TaskService) ctx.getBean("taskServiceImpl");
		CronTriggerManager cronTriggerManager = ctx.getBean(CronTriggerManager.class);
		try {
			List<Task> tasks = taskService.findTaskList();
			for(Task task : tasks){
				if(task.getStatus() == 0){
					TaskMessage taskMessage = taskService.convert2TaskMessage(task);
					cronTriggerManager.addJob(taskMessage, false);
				}
			}
		} catch (BussinessException | SchedulerException e) {
			e.printStackTrace();
		}
		
	}

}