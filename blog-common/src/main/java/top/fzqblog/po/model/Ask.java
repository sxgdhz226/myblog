package top.fzqblog.po.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.management.loading.PrivateClassLoader;

import org.ocpsoft.prettytime.PrettyTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import top.fzqblog.po.enums.DateTimePatternEnum;
import top.fzqblog.po.enums.SolveEnum;
import top.fzqblog.utils.DateUtil;

public class Ask {
    private Integer askId;

    private Integer pCategoryId;

    private Integer categoryId;

    private String title;

    private Integer userId;

    private String userIcon;

    private String userName;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="GMT+8")
    private Date createTime;
    
    private String createTimeString;

    private Integer readCount = 0;
    
    private Integer commentCount = 0;
    
    private Integer likeCount = 0;
    
    private Integer collectionCount = 0 ;

    private Integer mark = 0;

    private Integer bestAnswerId;

    private Integer bestAnswerUserId;

    private String bestAnswerUserName;
    
    private String bestAnswerUserIcon;

    private SolveEnum solveType;
    
    private String content;

    private String summary;

    private String askImage;

    private String askImageThum;
    
    private Comment bestAnswer;
    
    private Integer solveCount;

	public Integer getAskId() {
		return askId;
	}

	public void setAskId(Integer askId) {
		this.askId = askId;
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

	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	public Integer getReadCount() {
		return readCount;
	}

	public void setReadCount(Integer readCount) {
		this.readCount = readCount;
	}

	public Integer getCollectionCount() {
		return collectionCount;
	}

	public void setCollectionCount(Integer collectionCount) {
		this.collectionCount = collectionCount;
	}

	public Integer getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}

	public Integer getMark() {
		return mark;
	}

	public void setMark(Integer mark) {
		this.mark = mark;
	}

	public Integer getBestAnswerId() {
		return bestAnswerId;
	}

	public void setBestAnswerId(Integer bestAnswerId) {
		this.bestAnswerId = bestAnswerId;
	}

	public Integer getBestAnswerUserId() {
		return bestAnswerUserId;
	}

	public void setBestAnswerUserId(Integer bestAnswerUserId) {
		this.bestAnswerUserId = bestAnswerUserId;
	}

	public String getBestAnswerUserName() {
		return bestAnswerUserName;
	}

	public void setBestAnswerUserName(String bestAnswerUserName) {
		this.bestAnswerUserName = bestAnswerUserName;
	}


	public String getBestAnswerUserIcon() {
		return bestAnswerUserIcon;
	}

	public void setBestAnswerUserIcon(String bestAnswerUserIcon) {
		this.bestAnswerUserIcon = bestAnswerUserIcon;
	}

	public SolveEnum getSolveType() {
		return solveType;
	}

	public void setSolveType(SolveEnum solveType) {
		this.solveType = solveType;
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

	public String getAskImage() {
		return askImage;
	}

	public void setAskImage(String askImage) {
		this.askImage = askImage;
	}

	public String getAskImageThum() {
		return askImageThum;
	}

	public void setAskImageThum(String askImageThum) {
		this.askImageThum = askImageThum;
	}

	public Comment getBestAnswer() {
		return bestAnswer;
	}

	public void setBestAnswer(Comment bestAnswer) {
		this.bestAnswer = bestAnswer;
	}

	public Integer getSolveCount() {
		return solveCount;
	}

	public void setSolveCount(Integer solveCount) {
		this.solveCount = solveCount;
	}

	@Override
	public String toString() {
		return "Ask [askId=" + askId + ", pCategoryId=" + pCategoryId
				+ ", categoryId=" + categoryId + ", title=" + title
				+ ", userId=" + userId + ", userIcon=" + userIcon
				+ ", userName=" + userName + ", createTime=" + createTime
				+ ", createTimeString=" + createTimeString + ", readCount="
				+ readCount + ", commentCount=" + commentCount + ", likeCount="
				+ likeCount + ", collectionCount=" + collectionCount
				+ ", mark=" + mark + ", bestAnswerId=" + bestAnswerId
				+ ", bestAnswerUserId=" + bestAnswerUserId
				+ ", bestAnswerUserName=" + bestAnswerUserName + ", solveType="
				+ solveType + ", content=" + content + ", summary=" + summary
				+ ", askImage=" + askImage + ", askImageThum=" + askImageThum
				+ ", bestAnswer=" + bestAnswer + ", solveCount=" + solveCount
				+ "]";
	}
    
    


}