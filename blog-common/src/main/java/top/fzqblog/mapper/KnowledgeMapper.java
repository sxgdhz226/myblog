package top.fzqblog.mapper;

import org.apache.ibatis.annotations.Param;

import top.fzqblog.po.enums.StatusEnum;
import top.fzqblog.po.query.UpdateQuery4ArticleCount;



public interface KnowledgeMapper<T, Q> extends BaseMapper<T, Q> {
	public void updateInfoCount(UpdateQuery4ArticleCount updateQuery4ArticleCount);
	
	public void updateKnowledgeStatus(@Param("status") StatusEnum status, @Param("ids") Integer[] ids);
	
	public void delete(@Param("id") Integer id);
}