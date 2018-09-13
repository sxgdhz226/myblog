package top.fzqblog.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import top.fzqblog.service.IStatisticalDataService;

@ContextConfiguration(locations = {"classpath:spring.xml"})
public class StatisticeServiceTest extends AbstractTestNGSpringContextTests{
	@Autowired
	private IStatisticalDataService iStatisticalDataService;
	
	@Test
	public void testCaculateData(){
		this.iStatisticalDataService.caculateData();
	}
}
