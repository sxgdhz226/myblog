package top.fzqblog.test.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import top.fzqblog.po.model.SignInfo;
import top.fzqblog.po.model.SysMonitorLog;
import top.fzqblog.service.SysMonitorLogService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@ContextConfiguration(locations = {"classpath:spring.xml"})
public class SysMonitorTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private SysMonitorLogService sysMonitorLogService;

    @Test
    public void testFindSignInfoByUserid(){
        try {
            Predicate<SysMonitorLog> predicate = n -> n.getResultDesc_s().equals("success");
            List<SysMonitorLog> list = sysMonitorLogService.findSysList();

            list = Optional.ofNullable(list)
                    //.ifPresent(a -> a.forEach(b -> System.out.println(b.getResultDesc_s())));
                    .orElse(new ArrayList<SysMonitorLog>());


            list = list.stream().filter(predicate).
                    filter(n -> n.getTarget_s().equals("10.152.232.12"))
                    .collect(Collectors.toList());

            list.forEach(e -> {
                sysMonitorLogService.addSysMonitor(e);
                System.out.println(e.getTarget_s());
            });
//            List<SysMonitorLog> sysMonitorLogList = sysMonitorLogService.findSysList().subList(0,10);
//            for (SysMonitorLog sysMonitorLog : sysMonitorLogList){
//                sysMonitorLogService.addSysMonitor(sysMonitorLog);

//            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }
}
