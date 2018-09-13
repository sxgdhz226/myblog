package top.fzqblog.service;

import java.util.List;

import top.fzqblog.exception.BussinessException;
import top.fzqblog.po.model.Attachment;
import top.fzqblog.po.model.Knowledge;
import top.fzqblog.po.query.KnowledgeQuery;
import top.fzqblog.utils.PageResult;

public interface KnowledgeService {
	
	public PageResult<Knowledge> findKnowledgeByPage(KnowledgeQuery knowledgeQuery);
	
	public Knowledge getKnowledge(Integer knowledgeId);
	
	public Knowledge showKnowledge(Integer knowledgeId, Integer userId) throws BussinessException;
	
	public void addKnowledge(Knowledge knowledge, Attachment attachment) throws BussinessException;
	
	public List<Knowledge> findKnowledgeList();
	
	public void deleteBatch(Integer[] ids)throws BussinessException;
	
	public void updateStatusBatch(Integer[] ids) throws BussinessException;
}


