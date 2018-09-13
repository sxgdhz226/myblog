package top.fzqblog.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import top.fzqblog.service.TopicService;

@ContextConfiguration(locations = {"classpath:spring.xml"})
public class TopicServiceTest extends AbstractTestNGSpringContextTests {
	@Autowired
	private TopicService topicService;
	
	@Test
	public void testFindActiveUsers(){
		System.out.println(this.topicService.findActiveUsers());
	}
	
	@Test
	public void testSplit(){
		String s = "http://localhost:8091/upload/201608/1470330093173.jpg|http://localhost:8091/upload/201608/1470330093173.jpg|http://localhost:8091/upload/201608/1470330093173.jpg";
		String [] strings = s.split("\\|");
		System.out.println(strings[1]);
	}
}
