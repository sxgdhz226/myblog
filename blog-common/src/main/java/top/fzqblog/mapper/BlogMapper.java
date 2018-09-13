package top.fzqblog.mapper;

import org.apache.ibatis.annotations.Param;

import top.fzqblog.po.query.UpdateQuery4ArticleCount;


public interface BlogMapper<T, Q> extends BaseMapper<T, Q> {
	public void updateInfoCount(UpdateQuery4ArticleCount updateQuery4ArticleCount);
	
	public void delete(@Param("blogId") Integer blogId);
}