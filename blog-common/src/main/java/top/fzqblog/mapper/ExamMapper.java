package top.fzqblog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import top.fzqblog.po.enums.StatusEnum;



public interface ExamMapper<T, Q> extends BaseMapper<T, Q> {
	public List<T> selectExamRand(Q q);
	
	public List<T> selectListWithRightAnswer(Q q);
	
	public List<T> selectExamUsers(Q q);
	
	public int selectExamUsersCount(Q q);
	
	public void updateExamStatus(@Param("status") StatusEnum status, @Param("ids") Integer[] ids);
	
	public void delete(@Param("id") Integer id);
	
}