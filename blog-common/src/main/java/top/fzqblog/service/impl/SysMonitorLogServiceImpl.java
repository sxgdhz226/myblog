package top.fzqblog.service.impl;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.fzqblog.context.SolrContext;
import top.fzqblog.mapper.SysMonitorMapper;
import top.fzqblog.po.model.SysMonitorLog;
import top.fzqblog.po.query.SysMonitorQuery;
import top.fzqblog.service.SysMonitorLogService;

import java.io.IOException;
import java.util.List;

@Service("sysMonitorLogServiceImpl")
public class SysMonitorLogServiceImpl implements SysMonitorLogService {

    @Autowired
    private SysMonitorMapper<SysMonitorLog,SysMonitorQuery> sysMonitorMapper;

    @Override
    public List<SysMonitorLog> findSysList() {
        return sysMonitorMapper.selectList(new SysMonitorQuery());
    }

    @Override
    public void addSysMonitor(SysMonitorLog sysMonitorLog) {
        HttpSolrServer httpSolrServer = SolrContext.getHttpSolrServer();
        try {
            httpSolrServer.addBean(sysMonitorLog);
            httpSolrServer.optimize();
            httpSolrServer.commit();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
        //sysMonitorMapper.insert(sysMonitorLog);
    }
}
