package top.fzqblog.po.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import top.fzqblog.po.enums.ExamChooseType;
import top.fzqblog.po.enums.StatusEnum;

public class Exam {
    private Integer id;

    private Integer categoryId;

    private ExamChooseType chooseType;

	private Integer userId;

    private String userIcon;

    private String userName;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date createTime;

    private StatusEnum status;
    
    private String examTitle;
    
    private String analyse;
    
    private boolean isCorrect;
    
    private int examCount;
    
    private List<Integer> correctAnswerIds;
    
	private List<ExamDetail> examDetails = new ArrayList<ExamDetail>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public ExamChooseType getChooseType() {
		return chooseType;
	}

	public void setChooseType(ExamChooseType chooseType) {
		this.chooseType = chooseType;
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

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public String getExamTitle() {
		return examTitle;
	}

	public void setExamTitle(String examTitle) {
		this.examTitle = examTitle;
	}

	public String getAnalyse() {
		return analyse;
	}

	public void setAnalyse(String analyse) {
		this.analyse = analyse;
	}

	public boolean isCorrect() {
		return isCorrect;
	}

	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

	public List<Integer> getCorrectAnswerIds() {
		return correctAnswerIds;
	}

	public void setCorrectAnswerIds(List<Integer> correctAnswerIds) {
		this.correctAnswerIds = correctAnswerIds;
	}

	public List<ExamDetail> getExamDetails() {
		return examDetails;
	}

	public void setExamDetails(List<ExamDetail> examDetails) {
		this.examDetails = examDetails;
	}

	public int getExamCount() {
		return examCount;
	}

	public void setExamCount(int examCount) {
		this.examCount = examCount;
	}

	@Override
	public String toString() {
		return "Exam [id=" + id + ", categoryId=" + categoryId
				+ ", chooseType=" + chooseType + ", userId=" + userId
				+ ", userIcon=" + userIcon + ", userName=" + userName
				+ ", createTime=" + createTime + ", status=" + status
				+ ", examTitle=" + examTitle + ", analyse=" + analyse
				+ ", isCorrect=" + isCorrect + ", examCount=" + examCount
				+ ", correctAnswerIds=" + correctAnswerIds + ", examDetails="
				+ examDetails + "]";
	}
	
	   

}