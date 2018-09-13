package top.fzqblog.service;

import java.util.List;

import top.fzqblog.po.model.SysLog;

public interface SysLogService {
	
	public void addSysLog(SysLog sysLog);
	
	public List<SysLog> findSysLogList();
}
