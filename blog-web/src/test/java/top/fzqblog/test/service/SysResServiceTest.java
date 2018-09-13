package top.fzqblog.test.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import top.fzqblog.mapper.StatisticsMapper;
import top.fzqblog.po.model.Statistics;
import top.fzqblog.po.model.UserFriend;
import top.fzqblog.po.query.StatisticsQuery;
import top.fzqblog.po.query.UserFriendQuery;
import top.fzqblog.service.SysResService;
import top.fzqblog.service.UserFriendService;
import top.fzqblog.utils.DateUtil;
import top.fzqblog.utils.PageResult;

@ContextConfiguration(locations = {"classpath:spring.xml"})
public class SysResServiceTest extends AbstractTestNGSpringContextTests{
	@Autowired
	private SysResService sysResService;
	
	@Autowired
	private StatisticsMapper<Statistics, StatisticsQuery> statisticeMapper;
	
	@Test
	public void testFindFriendList(){
		System.out.println(this.sysResService.findAllRes());
	}
	
	@Test
	public void testGetStatistics(){
		StatisticsQuery statisticsQuery = new StatisticsQuery();
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -6);
		statisticsQuery.setStartDate(calendar.getTime());
		statisticsQuery.setEndDate(new Date());
		System.out.println(this.statisticeMapper.selectList(statisticsQuery));
	}
	
}
