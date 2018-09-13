package top.fzqblog.test.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import top.fzqblog.po.enums.DateTimePatternEnum;
import top.fzqblog.po.query.CategoryQuery;
import top.fzqblog.service.CategoryService;
import top.fzqblog.utils.DateUtil;

@ContextConfiguration(locations = {"classpath:spring.xml"})
public class CategoryServiceTest extends AbstractTestNGSpringContextTests{
	
	@Autowired
	private CategoryService categoryService;
	
	@Test
	public void testGetCategoryList(){
		CategoryQuery categoryQuery = new CategoryQuery();
		Date date = new Date();
		String curDate = DateUtil.format(date, DateTimePatternEnum.YYYY_MM_DD.getPattern());
		categoryQuery.setStartDate(curDate);
		categoryQuery.setEndDate(curDate);
		System.out.println(categoryService.findCategory4TopicCount(categoryQuery));
	}
}
