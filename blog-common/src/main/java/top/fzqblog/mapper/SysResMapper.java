package top.fzqblog.mapper;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;


import top.fzqblog.po.model.SysRes;

public interface SysResMapper<T, Q> extends BaseMapper<T, Q>{
	
	public void delete(@Param("ids") Integer[] ids);
	
	public void  deletePermission(@Param("ids") Integer[] ids) ;
	
	public List<SysRes> selectMenuByRoleIds(@Param("roleIds") Set<Integer> roleIds);
	
}