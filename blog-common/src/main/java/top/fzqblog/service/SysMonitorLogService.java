package top.fzqblog.service;

import top.fzqblog.exception.BussinessException;
import top.fzqblog.po.model.Attachment;
import top.fzqblog.po.model.Knowledge;
import top.fzqblog.po.model.SysMonitorLog;

import java.util.List;

public interface SysMonitorLogService  {

    public List<SysMonitorLog> findSysList();

    public void addSysMonitor(SysMonitorLog sysMonitorLog);
}
