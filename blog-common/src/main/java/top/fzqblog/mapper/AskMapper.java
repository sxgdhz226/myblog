package top.fzqblog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import top.fzqblog.po.query.UpdateQuery4ArticleCount;

public interface AskMapper<T, Q> extends BaseMapper<T, Q> {
	public void updateInfoCount(UpdateQuery4ArticleCount updateQuery4ArticleCount);
	
	public List<T> selectTopUser(Q q);
	
	public void updateBestAnswer(T t);
	
	public void delete(@Param("askId") Integer askId);
}