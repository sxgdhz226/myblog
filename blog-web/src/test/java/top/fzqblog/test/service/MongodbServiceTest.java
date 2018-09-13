package top.fzqblog.test.service;

import com.mongodb.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import top.fzqblog.po.mongodb.SysMonitorLog;
import top.fzqblog.utils.MongoManger;

@ContextConfiguration(locations = {"classpath:spring*.xml"})
public class MongodbServiceTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private MongoTemplate mongoTemplate;

//    @Test
//    public void save(){

//        try {
//            SysMonitorLog log = new SysMonitorLog();
//            log.setSendSms_s("测试");
//            mongoTemplate.save(log);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//        }
//    }

    public static void queryDataBase(){
        Mongo mg = MongoManger.getMongo();
        mg.getDatabaseNames().forEach(a -> System.out.println(a));
    }

    public static void queryCollection(){
        Mongo mg = MongoManger.getMongo();
        mg.getDB("vshop").getCollectionNames().forEach(a -> System.out.println(a));
    }

    public static void queryData(){
        Mongo mg = MongoManger.getMongo();
        DBCollection db = mg.getDB("vshop").getCollection("user");
        DBCursor dbCursor = db.find();
        while(dbCursor.hasNext()){
            System.out.println(dbCursor.next());
        }
    }


    public static void add(){
        Mongo mg = MongoManger.getMongo();
        DBCollection db = mg.getDB("vshop").getCollection("user");

        DBObject user = new BasicDBObject();
        user.put("result",123);
        db.save(user);
    }

    public static void remove(){
        Mongo mg = MongoManger.getMongo();
        DBCollection db = mg.getDB("vshop").getCollection("user");

        DBObject user = new BasicDBObject();
        user.put("result",123);
        user.put("aaaa",123);
        db.remove(user);
    }


    public static void main(String[] args) {
//        queryDataBase();
//        queryCollection();
            //queryData();
       // add();
        remove();
    }
}
