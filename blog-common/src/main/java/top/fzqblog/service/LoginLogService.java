package top.fzqblog.service;

import java.util.List;

import top.fzqblog.po.model.LoginLog;

public interface LoginLogService {
	
	public void addLoginLog(LoginLog loginLog);
	
	public List<LoginLog> findLoginLog();
	
	public List<LoginLog> findLoginLogGroupByIp();
}
