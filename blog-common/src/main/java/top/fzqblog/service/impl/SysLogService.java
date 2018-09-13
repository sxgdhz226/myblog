package top.fzqblog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.fzqblog.mapper.SysLogMapper;
import top.fzqblog.po.model.SysLog;
import top.fzqblog.po.query.SysLogQuery;

@Service
public class SysLogService implements top.fzqblog.service.SysLogService {
	
	@Autowired
	private SysLogMapper<SysLog, SysLogQuery> sysLogMapper;
	
	@Override
	public void addSysLog(SysLog sysLog) {
		sysLogMapper.insert(sysLog);
	}

	@Override
	public List<SysLog> findSysLogList() {
		SysLogQuery sysLogQuery = new SysLogQuery();
		return sysLogMapper.selectList(sysLogQuery);
	}

}
