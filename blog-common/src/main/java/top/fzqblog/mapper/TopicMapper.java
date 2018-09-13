package top.fzqblog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import top.fzqblog.po.query.UpdateQuery4ArticleCount;




public interface TopicMapper<T, Q> extends BaseMapper<T, Q> {
   public void updateInfoCount(UpdateQuery4ArticleCount updateQuery4ArticleCount);
   
   public List<T> selectActiveUser4Topic();
   
   public void delete(@Param("ids") Integer[] ids);
   

}