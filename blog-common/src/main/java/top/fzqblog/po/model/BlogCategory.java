package top.fzqblog.po.model;

import top.fzqblog.po.enums.BlogStatusEnum;

public class BlogCategory {
    private Integer categoryId;

    private Integer userId;

    private String name;

    private Integer rank;
    
    private Integer blogCount;

	public Integer getBlogCount() {
		return blogCount;
	}

	public void setBlogCount(Integer blogCount) {
		this.blogCount = blogCount;
	}

	public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

	@Override
	public String toString() {
		return "BlogCategory [categoryId=" + categoryId + ", userId=" + userId
				+ ", name=" + name + ", rank=" + rank + ", blogCount="
				+ blogCount + "]";
	}
    
    
}