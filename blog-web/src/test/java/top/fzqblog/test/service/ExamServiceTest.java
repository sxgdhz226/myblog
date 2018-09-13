package top.fzqblog.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import bsh.This;
import top.fzqblog.mapper.ExamMapper;
import top.fzqblog.po.enums.StatusEnum;
import top.fzqblog.po.model.Exam;
import top.fzqblog.po.query.CategoryQuery;
import top.fzqblog.po.query.ExamQuery;
import top.fzqblog.service.CategoryService;
import top.fzqblog.service.ExamService;
import top.fzqblog.utils.Constants;

@ContextConfiguration(locations = {"classpath:spring.xml"})
public class ExamServiceTest extends AbstractTestNGSpringContextTests{
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ExamService examService;
	
	@Autowired
	private ExamMapper<Exam, ExamQuery> examMapper;
	
	
	@Test
	public void testFindCategoryList(){
		CategoryQuery categoryQuery = new CategoryQuery();
		categoryQuery.setShowInExam(Constants.Y);
		System.out.println(this.categoryService.findCategoryList(categoryQuery));
	}
	
	@Test
	public void testFindExamListRand(){
		System.out.println(this.examService.findExamListRand(0));
	}
	
	@Test
	public void testfindExamUsers(){
		System.out.println(this.examService.findExamUsers(new ExamQuery()));
	}
	
	@Test
	public void testfindExamsWithRightAnswer(){
		ExamQuery examQuery = new ExamQuery();
		examQuery.setStatus(StatusEnum.INIT);
		examQuery.setShowAnalyse(Boolean.TRUE);
		List<Exam> examsWithRightAnswer = this.examMapper.selectListWithRightAnswer(examQuery);
		System.out.println(examsWithRightAnswer);
	}
}
