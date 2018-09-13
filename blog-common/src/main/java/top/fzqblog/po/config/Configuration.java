package top.fzqblog.po.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Configuration {
	@Value("#{applicationProperties['fzqblog.top.httpSolrUrl']}")
	private String httpSolrUrl;

	public String getHttpSolrUrl() {
		return httpSolrUrl;
	}

	public void setHttpSolrUrl(String httpSolrUrl) {
		this.httpSolrUrl = httpSolrUrl;
	}
	
	
}
