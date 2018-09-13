package top.fzqblog.po.model;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

import org.ocpsoft.prettytime.PrettyTime;

import top.fzqblog.cache.CategoryCache;
import top.fzqblog.po.enums.DateTimePatternEnum;
import top.fzqblog.po.enums.StatusEnum;
import top.fzqblog.utils.DateUtil;
import top.fzqblog.utils.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Knowledge {
    private Integer topicId;

    private Integer pCategoryId;

    private Integer categoryId;

    private String title;

    private Integer userId;

    private String userIcon;

    private String userName;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="GMT+8")
    private Date createTime;
    
    private String createTimeString;

    private Date lastCommentTime;

    private Integer readCount = 0;
    
    private Integer commentCount = 0;
    
    private Integer likeCount = 0;
    
    private Integer collectionCount = 0 ;

    private String content;

    private String summary;

    private String topicImage;
    
    private String[] topicImageArray;

    private String topicImageThum;
    
    private String categoryName;//二级栏目名称
    
    private String pCategoryName;//一级栏目名称
    
    private Attachment attachment;
    
    private StatusEnum status;

	public Integer getTopicId() {
		return topicId;
	}

	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}

	public Integer getpCategoryId() {
		return pCategoryId;
	}

	public void setpCategoryId(Integer pCategoryId) {
		this.pCategoryId = pCategoryId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserIcon() {
		return userIcon;
	}

	public void setUserIcon(String userIcon) {
		this.userIcon = userIcon;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateTimeString() {
		SimpleDateFormat sdf = new SimpleDateFormat(DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern());
		return DateUtil.friendly_time(sdf.format(createTime));	
	}

	public void setCreateTimeString(String createTimeString) {
		this.createTimeString = createTimeString;
	}

	public Date getLastCommentTime() {
		return lastCommentTime;
	}

	public void setLastCommentTime(Date lastCommentTime) {
		this.lastCommentTime = lastCommentTime;
	}

	public Integer getReadCount() {
		return readCount;
	}

	public void setReadCount(Integer readCount) {
		this.readCount = readCount;
	}

	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	public Integer getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}

	public Integer getCollectionCount() {
		return collectionCount;
	}

	public void setCollectionCount(Integer collectionCount) {
		this.collectionCount = collectionCount;
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

	public String getTopicImage() {
		return topicImage;
	}

	public void setTopicImage(String topicImage) {
		this.topicImage = topicImage;
	}

	public String[] getTopicImageArray() {
		if(StringUtils.isEmpty(this.topicImage)){
			return null;
		}
		return topicImage.split("\\|");
	}

	public void setTopicImageArray(String[] topicImageArray) {
		this.topicImageArray = topicImageArray;
	}

	public String getTopicImageThum() {
		return topicImageThum;
	}

	public void setTopicImageThum(String topicImageThum) {
		this.topicImageThum = topicImageThum;
	}

	public String getCategoryName() {
		return CategoryCache.getCategoryById(categoryId).getName();
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	

	public String getpCategoryName() {
		return CategoryCache.getCategoryById(pCategoryId).getName();
	}

	public void setpCategoryName(String pCategoryName) {
		this.pCategoryName = pCategoryName;
	}

	public Attachment getAttachment() {
		return attachment;
	}

	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Knowledge [topicId=" + topicId + ", pCategoryId=" + pCategoryId
				+ ", categoryId=" + categoryId + ", title=" + title
				+ ", userId=" + userId + ", userIcon=" + userIcon
				+ ", userName=" + userName + ", createTime=" + createTime
				+ ", createTimeString=" + createTimeString
				+ ", lastCommentTime=" + lastCommentTime + ", readCount="
				+ readCount + ", commentCount=" + commentCount + ", likeCount="
				+ likeCount + ", collectionCount=" + collectionCount
				+ ", content=" + content + ", summary=" + summary
				+ ", topicImage=" + topicImage + ", topicImageArray="
				+ Arrays.toString(topicImageArray) + ", topicImageThum="
				+ topicImageThum + ", categoryName=" + categoryName
				+ ", pCategoryName=" + pCategoryName + ", attachment="
				+ attachment + ", status=" + status + "]";
	}
    


}