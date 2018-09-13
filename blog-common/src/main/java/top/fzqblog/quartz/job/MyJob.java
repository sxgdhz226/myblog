/******************************************************************
 *
 *    
 *
 *    Copyright (c) 2016-forever 
 *    http://www.fzqblog.top
 *
 *    Package:     top.fzqblog.job
 *
 *    Filename:    MyJob.java
 *
 *    Description: TODO(用一句话描述该文件做什么)
 *
 *    Copyright:   Copyright (c) 2001-2014
 *
 *    Company:     fzqblog
 *
 *    @author:     抽离
 *
 *    @version:    1.0.0
 *
 *    Create at:   2016年11月2日 下午4:36:10
 *
 *    Revision:
 *
 *    2016年11月2日 下午4:36:10
 *        - first revision
 *
 *****************************************************************/
package top.fzqblog.quartz.job;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import top.fzqblog.quartz.TaskMessage;
import top.fzqblog.utils.Constants;

/**
 * @ClassName MyJob
 * @Description Job接口实现类
 * @author 抽离
 * @Date 2016年11月2日 下午4:36:10
 * @version 1.0.0
 */
public class MyJob implements Job{

	/* (非 Javadoc)
	 * Description:
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 */
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		
		TaskMessage task = (TaskMessage) jobDataMap.get(Constants.TASKKEY);
		Class<?> classz = null;
		try {
			classz = Class.forName(task.getTaskClassz());//得到对应的类
			
			Method method = classz.getDeclaredMethod(task.getTaskMethod());//得到方法
			
			method.invoke(classz.newInstance());//调用方法
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
