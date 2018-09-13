package top.fzqblog.po.model;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private Integer categoryId;

    private Integer pid;

    private String name;

    private String desc;

    private Integer rank;

    private Integer allowPost;

    private String showInBbs;

    private String showInQuestion;

    private String showInKnowledge;

    private String showInExam;
    
    private Integer count;
    
    private Integer todayCount;
    
    List<Category> children = new ArrayList<Category>();

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getAllowPost() {
        return allowPost;
    }

    public void setAllowPost(Integer allowPost) {
        this.allowPost = allowPost;
    }

    public String getShowInBbs() {
        return showInBbs;
    }

    public void setShowInBbs(String showInBbs) {
        this.showInBbs = showInBbs == null ? null : showInBbs.trim();
    }

    public String getShowInQuestion() {
        return showInQuestion;
    }

    public void setShowInQuestion(String showInQuestion) {
        this.showInQuestion = showInQuestion == null ? null : showInQuestion.trim();
    }

    public String getShowInKnowledge() {
        return showInKnowledge;
    }

    public void setShowInKnowledge(String showInKnowledge) {
        this.showInKnowledge = showInKnowledge == null ? null : showInKnowledge.trim();
    }

    public String getShowInExam() {
        return showInExam;
    }

    public void setShowInExam(String showInExam) {
        this.showInExam = showInExam == null ? null : showInExam.trim();
    }

	public List<Category> getChildren() {
		return children;
	}

	public void setChildren(List<Category> children) {
		this.children = children;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getTodayCount() {
		return todayCount;
	}

	public void setTodayCount(Integer todayCount) {
		this.todayCount = todayCount;
	}

	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", pid=" + pid
				+ ", name=" + name + ", desc=" + desc + ", rank=" + rank
				+ ", allowPost=" + allowPost + ", showInBbs=" + showInBbs
				+ ", showInQuestion=" + showInQuestion + ", showInKnowledge="
				+ showInKnowledge + ", showInExam=" + showInExam + ", count="
				+ count + ", todayCount=" + todayCount + ", children="
				+ children + "]";
	}

	
    
    
}