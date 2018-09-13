package top.fzqblog.po.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.omg.CORBA.PRIVATE_MEMBER;

import com.fasterxml.jackson.annotation.JsonFormat;

import top.fzqblog.po.enums.VoteType;

public class TopicVote {
    private Integer voteId;

    private Integer topicId;

    private VoteType voteType;
    
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="GMT+8")
    private Date endDate;
    
    private String endDateString;
    
    private int sumCount;
    
	private List<TopicVoteDetail> topicVoteDetailList = new ArrayList<TopicVoteDetail>();
	
	private List<TopicVoteUser> topicVoteUserList = new ArrayList<TopicVoteUser>();
	
	private boolean canVote;

	public Integer getVoteId() {
		return voteId;
	}

	public void setVoteId(Integer voteId) {
		this.voteId = voteId;
	}

	public Integer getTopicId() {
		return topicId;
	}

	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}

	public VoteType getVoteType() {
		return voteType;
	}

	public void setVoteType(VoteType voteType) {
		this.voteType = voteType;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getEndDateString() {
		return endDateString;
	}

	public void setEndDateString(String endDateString) {
		this.endDateString = endDateString;
	}

	public int getSumCount() {
		return sumCount;
	}

	public void setSumCount(int sumCount) {
		this.sumCount = sumCount;
	}

	public List<TopicVoteDetail> getTopicVoteDetailList() {
		return topicVoteDetailList;
	}

	public void setTopicVoteDetailList(List<TopicVoteDetail> topicVoteDetailList) {
		this.topicVoteDetailList = topicVoteDetailList;
	}

	public List<TopicVoteUser> getTopicVoteUserList() {
		return topicVoteUserList;
	}

	public void setTopicVoteUserList(List<TopicVoteUser> topicVoteUserList) {
		this.topicVoteUserList = topicVoteUserList;
	}

	public boolean isCanVote() {
		return canVote;
	}

	public void setCanVote(boolean canVote) {
		this.canVote = canVote;
	}
	
 
}