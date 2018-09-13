package top.fzqblog.service;


import java.util.List;

import top.fzqblog.exception.BussinessException;
import top.fzqblog.po.model.Task;
import top.fzqblog.quartz.TaskMessage;

public interface TaskService {
		
	public List<Task> findTaskList()throws BussinessException;
	
	public void deleteTask(Integer[] ids) throws BussinessException;
	
	public Task addTask(Task task, boolean isImmediateExcute) throws BussinessException;
	
	public void updateTask(Task task, boolean isImmediateExcute) throws BussinessException;
	
	public void pauseTask(Integer[] ids) throws BussinessException;
	
	public void excuteTask(Integer[] ids) throws BussinessException;
	
	public TaskMessage convert2TaskMessage(Task task);
	
}
