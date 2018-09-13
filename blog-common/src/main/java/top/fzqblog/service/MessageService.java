package top.fzqblog.service;

import org.springframework.scheduling.annotation.Async;

import top.fzqblog.exception.BussinessException;
import top.fzqblog.po.model.Message;
import top.fzqblog.po.model.MessageParams;
import top.fzqblog.po.query.MessageQuery;
import top.fzqblog.utils.PageResult;

public interface MessageService {
	
	@Async
	public void createMessage(MessageParams messageParams);
	
	public Message getMessageById(Integer id, Integer userId);
	
	public PageResult<Message> findMessageByPage(MessageQuery messageQuery);
	
	public int findMessageCount(MessageQuery messageQuery);
	
	public void update(Integer[] ids, Integer userId)throws BussinessException;
	
	public void delMessage(Integer userId, Integer[] ids)throws BussinessException;
	
}
