package top.fzqblog.po.model;

import org.apache.solr.client.solrj.beans.Field;

import java.io.Serializable;
import java.util.Date;

/**
 * solr dynamicField
 *
 */
public class SysMonitorLog  implements Serializable {

    private static final long serialVersionUID = 1L;
    @Field
    private long id;// id
    @Field
    private Date dateTime_dt;// 创建时间
    @Field
    private String taskId_s;// 任务id，关联task表
    @Field
    private String parentId_s;
    @Field
    private String taskName_s;// 任务名称
    @Field
    private String target_s;// 任务目标地址
    @Field
    private Date nextTime_dt;// 下次执行时间
    @Field
    private int result_i;// 执行结果 0：成功，1：失败
    @Field
    private String resultDesc_s;// 结果描述
    @Field
    private String sendSms_s;//0:发送，1:不发送

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Date getDateTime_dt() {
        return dateTime_dt;
    }

    public void setDateTime_dt(Date dateTime_dt) {
        this.dateTime_dt = dateTime_dt;
    }

    public String getTaskId_s() {
        return taskId_s;
    }

    public void setTaskId_s(String taskId_s) {
        this.taskId_s = taskId_s;
    }

    public String getParentId_s() {
        return parentId_s;
    }

    public void setParentId_s(String parentId_s) {
        this.parentId_s = parentId_s;
    }

    public String getTaskName_s() {
        return taskName_s;
    }

    public void setTaskName_s(String taskName_s) {
        this.taskName_s = taskName_s;
    }

    public String getTarget_s() {
        return target_s;
    }

    public void setTarget_s(String target_s) {
        this.target_s = target_s;
    }

    public Date getNextTime_dt() {
        return nextTime_dt;
    }

    public void setNextTime_dt(Date nextTime_dt) {
        this.nextTime_dt = nextTime_dt;
    }

    public int getResult_i() {
        return result_i;
    }

    public void setResult_i(int result_i) {
        this.result_i = result_i;
    }

    public String getResultDesc_s() {
        return resultDesc_s;
    }

    public void setResultDesc_s(String resultDesc_s) {
        this.resultDesc_s = resultDesc_s;
    }

    public String getSendSms_s() {
        return sendSms_s;
    }

    public void setSendSms_s(String sendSms_s) {
        this.sendSms_s = sendSms_s;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
