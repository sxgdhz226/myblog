package top.fzqblog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;




public interface CommentMapper<T, Q> extends BaseMapper<T, Q> {
   public List<T> selectChildren(@Param("id") Integer id);
}