package top.fzqblog.service;

import java.util.List;
import java.util.Set;

import top.fzqblog.exception.BussinessException;
import top.fzqblog.po.model.SysRes;
import top.fzqblog.po.vo.Tree;

public interface SysResService {
	
	public List<SysRes> findAllRes();
	
	public void deleteSysRes(Integer[] ids)throws BussinessException;
	
	public void addSysRes(SysRes sysRes)throws BussinessException;
	
	public void updateSysRes(SysRes sysRes) throws BussinessException;
	
	public List<SysRes> findAllMenu()throws BussinessException;
	
	public List<Tree> findAllTree()throws BussinessException;
	
	public List<SysRes> findMenuByRoleIds(Set<Integer> roleIds);
	
	
}
