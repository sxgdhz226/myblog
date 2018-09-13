package top.fzqblog.test.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import top.fzqblog.context.SolrContext;
import top.fzqblog.exception.BussinessException;
import top.fzqblog.mapper.KnowledgeMapper;
import top.fzqblog.po.enums.ArticleType;
import top.fzqblog.po.enums.PageSize;
import top.fzqblog.po.model.Knowledge;
import top.fzqblog.po.model.SolrBean;
import top.fzqblog.po.model.SysMonitorLog;
import top.fzqblog.po.query.KnowledgeQuery;
import top.fzqblog.service.KnowledgeService;
import top.fzqblog.service.SolrService;
import top.fzqblog.utils.Page;
import top.fzqblog.utils.StringUtils;

@ContextConfiguration(locations = {"classpath:spring.xml"})
public class SolrServiceTest extends AbstractTestNGSpringContextTests{
	
	@Autowired
	private SolrService solrService;
	
	@Autowired
	private KnowledgeMapper<Knowledge, KnowledgeQuery> knowledgeMapper;
	
	@Autowired
	private KnowledgeService knowledgeService;
	
	@Test
	public void testAddSolrBean(){
		SolrBean solrBean = new SolrBean();
		Knowledge knowledge = this.knowledgeService.getKnowledge(327);
		solrBean.setArticleType(ArticleType.KNOWLEDGE.getType());
		solrBean.setId(knowledge.getTopicId().toString());
		solrBean.setTitle(knowledge.getTitle());
		solrBean.setSummary(knowledge.getSummary());
		solrBean.setContent(knowledge.getContent());
		solrBean.setUserId(knowledge.getUserId().toString());
		solrBean.setUserName(knowledge.getUserName());
		solrBean.setCreateTime(knowledge.getCreateTimeString());
		this.solrService.addArticle(solrBean);
	}

	@Test
	public void testAddSysLog(){
		SysMonitorLog sysMonitorLog = new SysMonitorLog();
	}
	
	@Test
	public void testAddSolrBeanBatch(){
		KnowledgeQuery knowledgeQuery = new KnowledgeQuery();
		int pageSize  = this.knowledgeMapper.selectCount(new KnowledgeQuery());
		int pageNum = 1;
		int count = pageSize;
		Page page = new Page(pageNum, count, pageSize);
		knowledgeQuery.setPage(page);
		knowledgeQuery.setShowContent(true);
		List<Knowledge> list = this.knowledgeMapper.selectList(knowledgeQuery);
		List<SolrBean> solrBeans = new ArrayList<SolrBean>();
		for(Knowledge knowledge : list){
			SolrBean solrBean = new SolrBean();
			solrBean.setArticleType(ArticleType.KNOWLEDGE.getType());
			solrBean.setId(knowledge.getTopicId().toString());
			solrBean.setTitle(knowledge.getTitle());
			solrBean.setSummary(knowledge.getSummary());
			solrBean.setContent(StringUtils.cleanHtmlTag(knowledge.getContent()));
			solrBean.setUserId(knowledge.getUserId().toString());
			solrBean.setUserName(knowledge.getUserName());
			solrBean.setCreateTime(knowledge.getCreateTimeString());
			solrBeans.add(solrBean);
		}
		this.solrService.addArticleBatch(solrBeans);		
	}
	
	@Test
	public void deleteAllIndex(){
		HttpSolrServer server = SolrContext.getHttpSolrServer();
		try {
			server.deleteByQuery("*:*");
			server.commit();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void SolrQueryTest() throws BussinessException{
		System.out.println(this.solrService.findSolrBeanByPage("java", "K", null, null));
	}
	
	
}
