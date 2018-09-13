package top.fzqblog.po.query;

import java.util.Date;

public class SysMonitorQuery extends BaseQuery{

    private String resultDesc;// 结果描述
    private String target;// 结果描述
    private String taskName;// 结果描述
    private Date date_time;// 结果描述
    private Date next_time;// 结果描述

    public String getResultDesc() {
        return resultDesc;
    }

    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Date getDate_time() {
        return date_time;
    }

    public void setDate_time(Date date_time) {
        this.date_time = date_time;
    }

    public Date getNext_time() {
        return next_time;
    }

    public void setNext_time(Date next_time) {
        this.next_time = next_time;
    }
}
