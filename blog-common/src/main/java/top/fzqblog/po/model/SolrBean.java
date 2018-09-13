package top.fzqblog.po.model;

import java.io.Serializable;

import org.apache.solr.client.solrj.beans.Field;

public class SolrBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Field
	private String id;
	@Field
	private String title;
	@Field
	private String content;
	@Field
	private String summary;
	@Field
	private String userName;
	@Field
	private String userId;
	@Field
	private String articleType;
	@Field
	private String createTime;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getArticleType() {
		return articleType;
	}
	public void setArticleType(String articleType) {
		this.articleType = articleType;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "SolrBean [id=" + id + ", title=" + title + ", content="
				+ content + ", summary=" + summary + ", userName=" + userName
				+ ", userId=" + userId + ", articleType=" + articleType
				+ ", createTime=" + createTime + "]";
	}
	
	
	
	
}
