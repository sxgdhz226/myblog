package top.fzqblog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;


public interface TopicVoteUserMapper<T, Q> extends BaseMapper<T, Q> {
	public void insertBatch(@Param("list") List<T> list);
}