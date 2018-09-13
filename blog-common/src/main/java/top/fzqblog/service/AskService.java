package top.fzqblog.service;

import java.util.List;

import top.fzqblog.exception.BussinessException;
import top.fzqblog.po.model.Ask;
import top.fzqblog.po.query.AskQuery;
import top.fzqblog.utils.PageResult;

public interface AskService {
	
	public PageResult<Ask> findAskByPage(AskQuery askQuery);
	
	public int findCount(AskQuery askQuery);
	
	public void addAsk(Ask ask) throws BussinessException;
	
	public void setBestAnswer(Integer bestAnswerId, Integer askId, Integer userId) throws BussinessException;
	
	public Ask getAskById(Integer askId);
	
	public Ask showAsk(Integer askId) throws BussinessException;
	
	public List<Ask> findTopUsers();
	
	public List<Ask> findAskList();
	
	public void deleteBatch(Integer[] ids) throws BussinessException;
	
}
