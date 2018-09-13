package top.fzqblog.po.mongodb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.solr.client.solrj.beans.Field;

import java.util.Date;

/**
 * 使用lombok插件
 */
@Data
@NoArgsConstructor   //启用无参构造器
@AllArgsConstructor  //启用所有参数构造器
public class SysMonitorLog {

    private long id;// id
    private Date dateTime_dt;// 创建时间
    private String taskId_s;// 任务id，关联task表
    private String parentId_s;
    private String taskName_s;// 任务名称
    private String target_s;// 任务目标地址
    private Date nextTime_dt;// 下次执行时间
    private int result_i;// 执行结果 0：成功，1：失败
    private String resultDesc_s;// 结果描述
    private String sendSms_s;//0:发送，1:不发送


}
