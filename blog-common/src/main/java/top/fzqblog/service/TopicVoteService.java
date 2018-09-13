package top.fzqblog.service;

import top.fzqblog.exception.BussinessException;
import top.fzqblog.po.model.TopicVote;

public interface TopicVoteService {
	
	public void addVote(TopicVote topicVote, String[] voteTitle)throws BussinessException;
	
	public TopicVote getTopicVote(Integer topicId, Integer userId);
	
	public TopicVote doVote(Integer voteId, Integer voteType, Integer[] voteDetailId, Integer userId, Integer topicId)throws BussinessException;
}
