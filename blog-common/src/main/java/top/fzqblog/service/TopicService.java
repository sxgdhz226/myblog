package top.fzqblog.service;

import java.util.List;

import top.fzqblog.exception.BussinessException;
import top.fzqblog.po.model.Attachment;
import top.fzqblog.po.model.Topic;
import top.fzqblog.po.model.TopicVote;
import top.fzqblog.po.query.TopicQuery;
import top.fzqblog.utils.PageResult;

public interface TopicService {
	
	public PageResult<Topic> findTopicByPage(TopicQuery topicQuery);
	
	
	public void addTopic(Topic topic, TopicVote topicVote, String[] voteTitle, Attachment file)throws BussinessException;
	
	
	public Topic showTopic(Integer topicId) throws BussinessException;
	
	
	public Topic getTopic(Integer topicId);
	
	public List<Topic> findActiveUsers();
	
	public Integer findCount(TopicQuery topicQuery);
	
	public List<Topic> findTopicList();
	
	public void updateTopicEssence(Integer[] topicId, int essence)throws BussinessException;
	
	public void updateTopicStick(Integer[] topicId, int stick)throws BussinessException;
	
	public void deleteBatch(Integer[] topicIds) throws BussinessException;
	
}
