/******************************************************************
 *
 *    
 *
 *    Copyright (c) 2016-forever 
 *    http://www.fzqblog.top
 *
 *    Package:     top.fzqblog.service.impl
 *
 *    Filename:    StatisticalDataServiceImpl.java
 *
 *    Description: TODO(用一句话描述该文件做什么)
 *
 *    Copyright:   Copyright (c) 2001-2014
 *
 *    Company:     fzqblog
 *
 *    @author:     抽离
 *
 *    @version:    1.0.0
 *
 *    Create at:   2016年11月3日 下午3:17:48
 *
 *    Revision:
 *
 *    2016年11月3日 下午3:17:48
 *        - first revision
 *
 *****************************************************************/
package top.fzqblog.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.fzqblog.exception.BussinessException;
import top.fzqblog.mapper.AskMapper;
import top.fzqblog.mapper.BlogMapper;
import top.fzqblog.mapper.CommentMapper;
import top.fzqblog.mapper.ExamMapper;
import top.fzqblog.mapper.KnowledgeMapper;
import top.fzqblog.mapper.ShuoShuoCommentMapper;
import top.fzqblog.mapper.ShuoShuoMapper;
import top.fzqblog.mapper.SignInMapper;
import top.fzqblog.mapper.StatisticsMapper;
import top.fzqblog.mapper.TopicMapper;
import top.fzqblog.mapper.UserMapper;
import top.fzqblog.po.echart.Echart;
import top.fzqblog.po.echart.Series;
import top.fzqblog.po.echart.XAxis;
import top.fzqblog.po.enums.ArticleType;
import top.fzqblog.po.enums.DateTimePatternEnum;
import top.fzqblog.po.model.Ask;
import top.fzqblog.po.model.Blog;
import top.fzqblog.po.model.Comment;
import top.fzqblog.po.model.Exam;
import top.fzqblog.po.model.Knowledge;
import top.fzqblog.po.model.ShuoShuo;
import top.fzqblog.po.model.ShuoShuoComment;
import top.fzqblog.po.model.SignIn;
import top.fzqblog.po.model.Statistics;
import top.fzqblog.po.model.Topic;
import top.fzqblog.po.model.User;
import top.fzqblog.po.query.AskQuery;
import top.fzqblog.po.query.BlogQuery;
import top.fzqblog.po.query.CommentQuery;
import top.fzqblog.po.query.ExamQuery;
import top.fzqblog.po.query.KnowledgeQuery;
import top.fzqblog.po.query.ShuoShuoQuery;
import top.fzqblog.po.query.SignInQuery;
import top.fzqblog.po.query.StatisticsQuery;
import top.fzqblog.po.query.TopicQuery;
import top.fzqblog.po.query.UserQuery;
import top.fzqblog.service.IStatisticalDataService;
import top.fzqblog.utils.DateUtil;

/**
 * @ClassName StatisticalDataServiceImpl
 * @Description 
 * @author 抽离
 * @Date 2016年11月3日 下午3:17:48
 * @version 1.0.0
 */
@Service
public class StatisticalDataServiceImpl implements IStatisticalDataService {
	
	@Autowired
	private StatisticsMapper<Statistics, StatisticsQuery> statisticsMapper;
	
	@Autowired
	private SignInMapper<SignIn, SignInQuery> signInMapper;
	
	@Autowired
	private ShuoShuoMapper<ShuoShuo, ShuoShuoQuery> shuoShuoMapper;
	
	@Autowired
	private ShuoShuoCommentMapper<ShuoShuoComment, ShuoShuoQuery> shuoShuoCommentMapper;
	
	@Autowired
	private TopicMapper<Topic, TopicQuery> topicMapper;
	
	@Autowired
	private CommentMapper<Comment, CommentQuery> commentMapper;
	
	@Autowired
	private KnowledgeMapper<Knowledge, KnowledgeQuery> knowledgeMapper;
	
	@Autowired
	private AskMapper<Ask, AskQuery> askMapper;
	
	@Autowired
	private BlogMapper<Blog, BlogQuery> blogMapper;
	
	@Autowired
	private ExamMapper<Exam, ExamQuery> examMapper;
	
	@Autowired
	private UserMapper<User, UserQuery> userMapper;

	/* Description:
	 * @see top.fzqblog.service.IStatisticalDataService#caculateData()
	 */
	@Override
	public void caculateData() {
		System.out.println("统计中。。。。。。");
		Statistics statistics = new Statistics();
		
		Date date = new Date();
		
		statistics.setStatisticsDate(date);
		
		SignInQuery signInQuery = new SignInQuery();
		signInQuery.setCurDate(date);
		int signInCount = signInMapper.selectCount(signInQuery);
		statistics.setSigninCount(signInCount);
		
		ShuoShuoQuery shuoShuoQuery = new ShuoShuoQuery();
		shuoShuoQuery.setStartDate(DateUtil.format(date, DateTimePatternEnum.YYYY_MM_DD.getPattern()));
		shuoShuoQuery.setEndDate(DateUtil.format(date, DateTimePatternEnum.YYYY_MM_DD.getPattern()));
		int shuoshuoCount = shuoShuoMapper.selectCount(shuoShuoQuery);
		statistics.setShuoshuoCount(shuoshuoCount);
		
		int shuoshuoCommentCount = shuoShuoCommentMapper.selectCount(shuoShuoQuery);
		statistics.setShuoshuoCommentCount(shuoshuoCommentCount);
		
		TopicQuery topicQuery = new TopicQuery();
		topicQuery.setStartDate(DateUtil.format(date, DateTimePatternEnum.YYYY_MM_DD.getPattern()));
		topicQuery.setEndDate(DateUtil.format(date, DateTimePatternEnum.YYYY_MM_DD.getPattern()));
		int topicCount = topicMapper.selectCount(topicQuery);
		statistics.setTopicCount(topicCount);
		
		CommentQuery commentQuery = new CommentQuery();
		commentQuery.setStartDate(DateUtil.format(date, DateTimePatternEnum.YYYY_MM_DD.getPattern()));
		commentQuery.setEndDate(DateUtil.format(date, DateTimePatternEnum.YYYY_MM_DD.getPattern()));
		commentQuery.setArticleType(ArticleType.TOPIC);
		int topicCommentCount = commentMapper.selectCount(commentQuery);
		statistics.setTopicCommentCount(topicCommentCount);
		
		commentQuery.setArticleType(ArticleType.KNOWLEDGE);
		int knowledgeCommentCount = commentMapper.selectCount(commentQuery);
		statistics.setKnowledgeCommentCount(knowledgeCommentCount);
		
		commentQuery.setArticleType(ArticleType.Ask);
		int askCommentCount = commentMapper.selectCount(commentQuery);
		statistics.setAskCommentCount(askCommentCount);
		
		commentQuery.setArticleType(ArticleType.BLOG);
		int blogCommentCount = commentMapper.selectCount(commentQuery);
		statistics.setBlogCommentCount(blogCommentCount);
		
		KnowledgeQuery knowledgeQuery = new KnowledgeQuery();
		knowledgeQuery.setStartDate(DateUtil.format(date, DateTimePatternEnum.YYYY_MM_DD.getPattern()));
		knowledgeQuery.setEndDate(DateUtil.format(date, DateTimePatternEnum.YYYY_MM_DD.getPattern()));
		int knowledgeCount = knowledgeMapper.selectCount(knowledgeQuery);
		statistics.setKnowledgeCount(knowledgeCount);
		
		AskQuery askQuery = new AskQuery();
		askQuery.setStartDate(DateUtil.format(date, DateTimePatternEnum.YYYY_MM_DD.getPattern()));
		askQuery.setEndDate(DateUtil.format(date, DateTimePatternEnum.YYYY_MM_DD.getPattern()));	
		int askcount = askMapper.selectCount(askQuery);
		statistics.setAskCount(askcount);
		
		BlogQuery blogQuery = new BlogQuery();
		blogQuery.setStartDate(DateUtil.format(date, DateTimePatternEnum.YYYY_MM_DD.getPattern()));
		blogQuery.setEndDate(DateUtil.format(date, DateTimePatternEnum.YYYY_MM_DD.getPattern()));
		int blogcount = blogMapper.selectCount(blogQuery);
		statistics.setBlogCount(blogcount);
		
		ExamQuery examQuery = new ExamQuery();
		examQuery.setStartDate(DateUtil.format(date, DateTimePatternEnum.YYYY_MM_DD.getPattern()));
		examQuery.setEndDate(DateUtil.format(date, DateTimePatternEnum.YYYY_MM_DD.getPattern()));
		int examCount = examMapper.selectCount(examQuery);
		statistics.setExamCount(examCount);
		
		UserQuery userQuery = new UserQuery();
		userQuery.setStartDate(DateUtil.format(date, DateTimePatternEnum.YYYY_MM_DD.getPattern()));
		userQuery.setEndDate(DateUtil.format(date, DateTimePatternEnum.YYYY_MM_DD.getPattern()));		
		int userCount = userMapper.selectCount(userQuery);
		statistics.setUserCount(userCount);
		
		userQuery = new UserQuery();
		userQuery.setLoginStartDate(DateUtil.format(date, DateTimePatternEnum.YYYY_MM_DD.getPattern()));
		userQuery.setLoginEndDate(DateUtil.format(date, DateTimePatternEnum.YYYY_MM_DD.getPattern()));
		int activeUserCount = userMapper.selectCount(userQuery);
		statistics.setActiveUserCount(activeUserCount);
		
		statisticsMapper.insert(statistics);
	}

	@Override
	public List<Echart> findEcharts() throws BussinessException {
		List<Statistics> list = findStatistics();
		List<Echart> echarts = new ArrayList<Echart>();
		
		XAxis xAxis = new XAxis();
		List<String> dates = new ArrayList<String>();
		List<Integer> signInCounts	= new ArrayList<Integer>();	
		List<Integer> shuoshuoCounts	= new ArrayList<Integer>();	
		List<Integer> shuoshuoCommentCounts	= new ArrayList<Integer>();	
		List<Integer> topicCounts	= new ArrayList<Integer>();	
		List<Integer> topicCommentCounts	= new ArrayList<Integer>();	
		List<Integer> knowledgeCounts	= new ArrayList<Integer>();	
		List<Integer> knowledgeCommentCounts	= new ArrayList<Integer>();	
		List<Integer> askCounts	= new ArrayList<Integer>();	
		List<Integer> askCommentCounts	= new ArrayList<Integer>();	
		List<Integer> blogCounts	= new ArrayList<Integer>();	
		List<Integer> blogCommentCounts	= new ArrayList<Integer>();
		List<Integer> examCounts	= new ArrayList<Integer>();
		List<Integer> userCounts	= new ArrayList<Integer>();
		List<Integer> activeUserCounts	= new ArrayList<Integer>();
		for(Statistics statistics : list){
			dates.add(DateUtil.format(statistics.getStatisticsDate(), DateTimePatternEnum.YYYY_MM_DD.getPattern()));
			signInCounts.add(statistics.getSigninCount());
			shuoshuoCounts.add(statistics.getShuoshuoCount());
			shuoshuoCommentCounts.add(statistics.getShuoshuoCommentCount());
			topicCounts.add(statistics.getTopicCount());
			topicCommentCounts.add(statistics.getTopicCommentCount());
			knowledgeCounts.add(statistics.getKnowledgeCount());
			knowledgeCommentCounts.add(statistics.getKnowledgeCommentCount());
			askCounts.add(statistics.getAskCount());
			askCommentCounts.add(statistics.getAskCommentCount());
			blogCounts.add(statistics.getBlogCount());
			blogCommentCounts.add(statistics.getBlogCommentCount());
			examCounts.add(statistics.getExamCount());
			userCounts.add(statistics.getUserCount());
			activeUserCounts.add(statistics.getActiveUserCount());
		}
		xAxis.setData(dates);
		
		List<Series> series = new ArrayList<Series>();
		series.add(new Series("签到", signInCounts));
		Echart signInEchart = new Echart("一周之内的签到", new String[]{"签到"}, xAxis, series);
		echarts.add(signInEchart);
		
		
		series = new ArrayList<Series>();
		series.add(new Series("说说", shuoshuoCounts));
		series.add(new Series("说说评论", shuoshuoCommentCounts));
		Echart shuoshuoEchart = new Echart("一周之内的说说", new String[]{"说说","说说评论"}, xAxis, series);
		echarts.add(shuoshuoEchart);
		
		series = new ArrayList<Series>();
		series.add(new Series("帖子", topicCounts));
		series.add(new Series("帖子评论", topicCommentCounts));
		Echart topicEchart = new Echart("一周之内的帖子", new String[]{"帖子","帖子评论"}, xAxis, series);
		echarts.add(topicEchart);
		
		series = new ArrayList<Series>();
		series.add(new Series("知识库", knowledgeCounts));
		series.add(new Series("知识库评论", knowledgeCommentCounts));
		Echart knowledgeEchart = new Echart("一周之内的知识库", new String[]{"知识库","知识库评论"}, xAxis, series);
		echarts.add(knowledgeEchart);
		
		series = new ArrayList<Series>();
		series.add(new Series("提问", askCounts));
		series.add(new Series("回答", askCommentCounts));
		Echart askEchart = new Echart("一周之内的问答", new String[]{"提问","回答"}, xAxis, series);
		echarts.add(askEchart);
		
		series = new ArrayList<Series>();
		series.add(new Series("博客", blogCounts));
		series.add(new Series("博客评论", blogCommentCounts));
		Echart blogEchart = new Echart("一周之内的博客", new String[]{"博客","博客评论"}, xAxis, series);
		echarts.add(blogEchart);
		
		series = new ArrayList<Series>();
		series.add(new Series("考题", examCounts));
		Echart examEchart = new Echart("一周之内的考题", new String[]{"考题"}, xAxis, series);
		echarts.add(examEchart);
		
		series = new ArrayList<Series>();
		series.add(new Series("新增用户", userCounts));
		series.add(new Series("活跃用户", activeUserCounts));
		Echart userEchart = new Echart("一周之内的用户", new String[]{"新增用户","活跃用户"}, xAxis, series);
		echarts.add(userEchart);
		
		return echarts;
	}

	@Override
	public List<Statistics> findStatistics() throws BussinessException {
		StatisticsQuery statisticsQuery = new StatisticsQuery();
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -6);
		statisticsQuery.setStartDate(calendar.getTime());
		statisticsQuery.setEndDate(new Date());
		return this.statisticsMapper.selectList(statisticsQuery);
	}
}
