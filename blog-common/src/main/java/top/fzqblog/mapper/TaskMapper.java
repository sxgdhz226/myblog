package top.fzqblog.mapper;

import org.apache.ibatis.annotations.Param;

public interface TaskMapper<T, Q> extends BaseMapper<T, Q> {
	public void delete(@Param("ids") Integer[] ids);
}