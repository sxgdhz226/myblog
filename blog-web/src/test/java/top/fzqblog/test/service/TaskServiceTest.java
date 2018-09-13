package top.fzqblog.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import top.fzqblog.exception.BussinessException;
import top.fzqblog.service.IStatisticalDataService;
import top.fzqblog.service.TaskService;

@ContextConfiguration(locations = {"classpath:spring.xml"})
public class TaskServiceTest extends AbstractTestNGSpringContextTests{
	@Autowired
	private TaskService taskService;
	
	@Test
	public void testCaculateData() throws BussinessException{
		System.out.println(taskService.findTaskList());
	}
}
