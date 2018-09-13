package top.fzqblog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;



public interface TopicVoteDetailMapper<T, Q> extends BaseMapper<T, Q> {
	public void insertBatch(@Param("voteDetailList") List<T> voteDetailList);
	
	public void updateVoteCountBatch(@Param("list") List<Integer> list);
}