package top.fzqblog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;



public interface MessageMapper<T, Q> extends BaseMapper<T, Q> {
	public void insertBatch(@Param("list") List<T> list);
	
	public void delete(@Param("userId") Integer userId, @Param("ids") Integer[] ids);
	
	public void update(@Param("userId") Integer userId, @Param("ids") Integer[] ids);
}