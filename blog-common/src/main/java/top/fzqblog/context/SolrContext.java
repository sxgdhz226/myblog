package top.fzqblog.context;

import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.springframework.stereotype.Component;

import top.fzqblog.utils.Constants;

@Component
public class SolrContext {
	
	
	
	private static HttpSolrServer httpSolrServer= null;
	
	static{
		if(httpSolrServer == null){
			httpSolrServer = new HttpSolrServer(Constants.SOLRSERVERURL);
		}
	}
	
	public static HttpSolrServer getHttpSolrServer() {
		return httpSolrServer;
	}

	
	
}
