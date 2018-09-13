package top.fzqblog.po.model;

import java.util.Date;

public class TopicVoteUser {
    private Integer voteDetailId;

    private Integer userId;

    private Date voteDate;
    
    private String title;

    public Integer getVoteDetailId() {
        return voteDetailId;
    }

    public void setVoteDetailId(Integer voteDetailId) {
        this.voteDetailId = voteDetailId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getVoteDate() {
        return voteDate;
    }

    public void setVoteDate(Date voteDate) {
        this.voteDate = voteDate;
    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
    
    
}