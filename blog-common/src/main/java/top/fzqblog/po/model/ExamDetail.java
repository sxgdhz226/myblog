package top.fzqblog.po.model;

public class ExamDetail {
    private Integer id;

    private Integer examId;

    private String answer;

    private Integer isRightAnswer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }

    public Integer getIsRightAnswer() {
        return isRightAnswer;
    }

    public void setIsRightAnswer(Integer isRightAnswer) {
        this.isRightAnswer = isRightAnswer;
    }

	@Override
	public String toString() {
		return "ExamDetail [id=" + id + ", examId=" + examId + ", answer=" + answer + ", isRightAnswer=" + isRightAnswer
				+ "]";
	}
    
    
}