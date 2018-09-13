package top.fzqblog.mapper;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;


public interface SysRoleMapper<T, Q> extends BaseMapper<T, Q> {
	public void delete(@Param("ids") Integer[] ids);
	
	public List<Integer> selectResourceIdByRoleId(@Param("id") Integer id);
	

}