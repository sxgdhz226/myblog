package top.fzqblog.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import bsh.This;
import top.fzqblog.exception.BussinessException;
import top.fzqblog.po.model.Ask;
import top.fzqblog.po.query.AskQuery;
import top.fzqblog.service.AskService;
import top.fzqblog.utils.PageResult;

@ContextConfiguration(locations = {"classpath:spring.xml"})
public class AskServiceTest extends AbstractTestNGSpringContextTests{
	
	@Autowired
	private AskService askService;
	

	public void testAddAsk() throws BussinessException{
		Ask ask = new Ask();
		ask.setTitle("使用axis在webSphere上发布webservice 访问wsdl出现javax/wsdl/Definition错误");
		ask.setContent("tomcat上正常。报错信息如下：java.lang.LinkageError: javax/wsdl/Definition.getBinding(Ljavax/xml/namespace/QName;)Ljavax/wsdl/Binding;");
		ask.setUserId(10033);
		this.askService.addAsk(ask);
	}
	
	@Test
	public void addBatch() throws BussinessException{
		for(int i = 0; i< 30; i++){
			testAddAsk();
		}
	}
	
	
	@Test
	public void findAskByPage(){
		PageResult<Ask> pageResult = this.askService.findAskByPage(new AskQuery());
		System.out.println(pageResult);
	}
	
	@Test
	public void testFindTopUser(){
		System.out.println(this.askService.findTopUsers());
	}
}
