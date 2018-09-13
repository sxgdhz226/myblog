package top.fzqblog.test.service;

import com.mongodb.Mongo;
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
import top.fzqblog.po.model.Knowledge;
import top.fzqblog.po.model.SolrBean;
import top.fzqblog.po.model.SysMonitorLog;
import top.fzqblog.po.query.KnowledgeQuery;
import top.fzqblog.service.KnowledgeService;
import top.fzqblog.service.SolrService;
import top.fzqblog.utils.Page;
import top.fzqblog.utils.StringUtils;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

@ContextConfiguration(locations = {"classpath:spring.xml"})
public class MongodbTest extends AbstractTestNGSpringContextTests{
	
	@Autowired
	private SolrService solrService;
	

	@Test
	public void testAddSolrBean(){
		try {
			Mongo mongo = new Mongo("192.168.1.88", 27017);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}


	
}
