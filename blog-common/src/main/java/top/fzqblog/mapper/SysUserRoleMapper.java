package top.fzqblog.mapper;

import java.util.Set;

import org.apache.ibatis.annotations.Param;

public interface SysUserRoleMapper<T, Q> extends BaseMapper<T, Q> {
	   public void insertBatch(@Param("userId") Integer userId, @Param("roleIds") Integer[] roleIds) ;
	   
		public Set<Integer> selectRoleIdsByUserId(@Param("userId") Integer userId);
}