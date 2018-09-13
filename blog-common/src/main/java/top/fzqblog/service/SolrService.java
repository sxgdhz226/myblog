package top.fzqblog.service;

import java.util.List;

import top.fzqblog.exception.BussinessException;
import top.fzqblog.po.model.SolrBean;
import top.fzqblog.utils.PageResult;

public interface SolrService {
	public void addArticle(SolrBean solrBean);
	
	public void addArticleBatch(List<SolrBean> solrBeans);
	
	public PageResult<SolrBean> findSolrBeanByPage(String keyWord, String articleType, 
			Integer pageNum, Integer countTotal) throws BussinessException;
}
