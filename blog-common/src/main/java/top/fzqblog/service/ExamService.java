package top.fzqblog.service;

import java.util.List;

import top.fzqblog.exception.BussinessException;
import top.fzqblog.po.model.Exam;
import top.fzqblog.po.query.ExamQuery;
import top.fzqblog.utils.PageResult;



public interface ExamService {
	public List<Exam> findExamListRand(Integer categoryId);
	
	public void saveExam(Exam exam, String[] answer, Integer[] rightAnswer)throws BussinessException;
	
	public List<Exam> doMark(String examIds, String rightAnswers)throws BussinessException;
	
	public PageResult<Exam> findExamUsers(ExamQuery examQuery);
	
	public List<Exam> findExamWithRightAnswer();
	
	public void deleteBatch(Integer[] ids)throws BussinessException;
	
	public void updateStatusBatch(Integer[] ids) throws BussinessException;
}
