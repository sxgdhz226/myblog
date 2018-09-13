package top.fzqblog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.fzqblog.mapper.LoginLogMapper;
import top.fzqblog.po.model.LoginLog;
import top.fzqblog.po.query.LoginLogQuery;
import top.fzqblog.service.LoginLogService;


@Service
public class LoginLogServiceImpl implements LoginLogService {
	
	@Autowired
	private LoginLogMapper<LoginLog, LoginLogQuery> loginLogMapper;

	@Override
	public void addLoginLog(LoginLog loginLog) {
		loginLogMapper.insert(loginLog);
	}

	@Override
	public List<LoginLog> findLoginLog() {
		LoginLogQuery loginLogQuery = new LoginLogQuery();
		return loginLogMapper.selectList(loginLogQuery);
	}

	@Override
	public List<LoginLog> findLoginLogGroupByIp() {
		LoginLogQuery loginLogQuery = new LoginLogQuery();
		return loginLogMapper.selectListGroupByIp(loginLogQuery);
	}

}
