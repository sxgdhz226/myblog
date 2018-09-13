package top.fzqblog.mapper;

import org.apache.ibatis.annotations.Param;

public interface SysRoleResMapper<T, Q> extends BaseMapper<T, Q> {
	   public void insertBatch(@Param("roleId") Integer roleId, @Param("resIds") Integer[] resIds) ;
}