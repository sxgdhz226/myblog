package top.fzqblog.test.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.ocpsoft.prettytime.PrettyTime;
import org.testng.annotations.Test;

import top.fzqblog.po.enums.DateTimePatternEnum;
import top.fzqblog.utils.DateUtil;

public class PrettyTimeTest {
	@Test
    public void testLocal() {
        PrettyTime p = new PrettyTime(new Locale("ZH_CN"));
        System.out.println(p.format(new Date()));
    }
  
    @Test
    public void testMinutesFromNow() throws Exception {
        PrettyTime t = new PrettyTime(new Date(0));
        System.out.println(t.format(new Date(1000 * 60 * 12)));
    }
  
    @Test
    public void testMomentsAgo() throws Exception {
        PrettyTime t = new PrettyTime(new Date(6000));
        System.out.println(t.format(new Date(0)));
    }
  
    @Test
    public void testMinutesAgo() throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:m:s");
        Date date = format.parse("2012-07-18 23:42:05");
        Date now = new Date();
        PrettyTime t = new PrettyTime(now);
        System.out.println(t.format(date));
    }
    
    @Test
    public void testDateUtils(){
    	Date createTime = null;
		try {
			createTime = new SimpleDateFormat(DateTimePatternEnum.YYYY_MM_DD.getPattern()).parse("2016-08-31");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.out.println(DateUtil.daysBetween(createTime, new Date()));
    }
}
