package top.fzqblog.service;

import java.util.List;
import java.util.Set;

import top.fzqblog.exception.BussinessException;
import top.fzqblog.po.model.SysRole;

public interface SysRoleService {
	
	public List<SysRole> findSysRoleList();
	
	public void deleteSysRole(Integer[] ids)throws BussinessException;
	
	public void addSysRole(SysRole sysRole) throws BussinessException;
	
	public void updateSysRole(SysRole sysRole) throws BussinessException;
	
	public SysRole findSysRoleById(Integer id) throws BussinessException;
	
	public List<Integer> findResourceIdByRoleId(Integer id)throws BussinessException;
	
	public void updateAuthority(Integer roleId, Integer[] resIds) throws BussinessException;
	
	public Set<Integer> findRoleIdsByUserId(Integer userId);
	
}
