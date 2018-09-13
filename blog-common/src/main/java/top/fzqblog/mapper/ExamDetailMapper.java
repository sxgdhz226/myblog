package top.fzqblog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;


public interface ExamDetailMapper<T, Q> extends BaseMapper<T, Q> {
    public List<T> selectListWithRightAnswer(@Param("examId") Integer examId);
    
    public void insertBatch(@Param("list") List<T> list);
    
    public void delete(@Param("examId") Integer examId);
}