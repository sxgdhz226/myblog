package top.fzqblog.po.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.ocpsoft.prettytime.PrettyTime;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import top.fzqblog.po.enums.ArticleType;
import top.fzqblog.po.enums.DateTimePatternEnum;
import top.fzqblog.utils.CustomDateSerializer;
import top.fzqblog.utils.DateUtil;
import top.fzqblog.utils.Emotions;
import top.fzqblog.utils.Emotions.Dev;

public class Comment {
	
    private Integer id;

    private Integer pid;

    private Integer articleId;

    private Integer userId;

    private Date createTime;
    
    private String createTimeString;

    private String sourceFrom;

    private ArticleType articleType;

    private String userName;

    private String userIcon;

    private String content;
    
    private List<Comment> children = new ArrayList<Comment>();
    
    private String showContent;
    
    private Integer pageNum;
    
    public String getShowContent() {
    	this.showContent = Emotions.formatEmotion(this.content, Dev.WEB);
    	return showContent;
	}

	public void setShowContent(String showContent) {
		this.showContent = showContent;
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @JsonSerialize(using = CustomDateSerializer.class)  
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSourceFrom() {
        return sourceFrom;
    }

    public void setSourceFrom(String sourceFrom) {
        this.sourceFrom = sourceFrom == null ? null : sourceFrom.trim();
    }

    

    public ArticleType getArticleType() {
		return articleType;
	}

	public void setArticleType(ArticleType articleType) {
		this.articleType = articleType;
	}

	public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon == null ? null : userIcon.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

	public List<Comment> getChildren() {
		return children;
	}

	public void setChildren(List<Comment> children) {
		this.children = children;
	}

	public String getCreateTimeString() {
		SimpleDateFormat sdf = new SimpleDateFormat(DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern());
		return DateUtil.friendly_time(sdf.format(createTime));	
	}

	public void setCreateTimeString(String createTimeString) {
		this.createTimeString = createTimeString;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
    
    
	
}