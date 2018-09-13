package top.fzqblog.po.model;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

import org.ocpsoft.prettytime.PrettyTime;

import top.fzqblog.po.enums.BlogStatusEnum;
import top.fzqblog.po.enums.DateTimePatternEnum;
import top.fzqblog.utils.DateUtil;


import top.fzqblog.utils.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Blog {
    private Integer blogId;

    private Integer categoryId;
    
    private String name;

    private String title;

    private Integer userId;

    private String userIcon;

    private String userName;
    
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="GMT+8")
    private Date createTime;
    
    private String createTimeString;

    private Integer commentCount = 0;

    private Integer readCount = 0;

    private Integer collectionCount = 0;

    private Integer likeCount = 0;

    private BlogStatusEnum status;

    private String content;

    private String summary;

    private String blogImage;
    
    private String[] blogImageArray;
    
    private Attachment attachment;
    

    public Attachment getAttachment() {
		return attachment;
	}

	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}

	public String[] getBlogImageArray() {
    	if(StringUtils.isEmpty(this.blogImage)){
			return null;
		}
		return blogImage.split("\\|");
	}

	public void setBlogImageArray(String[] blogImageArray) {
		this.blogImageArray = blogImageArray;
	}

	private String blogImageThum;
    
    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
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
        this.title = title == null ? null : title.trim();
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
        this.userIcon = userIcon == null ? null : userIcon.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
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



	public BlogStatusEnum getStatus() {
		return status;
	}

	public void setStatus(BlogStatusEnum status) {
		this.status = status;
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

	public String getBlogImage() {
		return blogImage;
	}

	public void setBlogImage(String blogImage) {
		this.blogImage = blogImage;
	}

	public String getBlogImageThum() {
		return blogImageThum;
	}

	public void setBlogImageThum(String blogImageThum) {
		this.blogImageThum = blogImageThum;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Blog [blogId=" + blogId + ", categoryId=" + categoryId
				+ ", title=" + title + ", userId=" + userId + ", userIcon="
				+ userIcon + ", userName=" + userName + ", createTime="
				+ createTime + ", createTimeString=" + createTimeString
				+ ", commentCount=" + commentCount + ", readCount=" + readCount
				+ ", collectionCount=" + collectionCount + ", likeCount="
				+ likeCount + ", status=" + status + ", content=" + content
				+ ", summary=" + summary + ", blogImage=" + blogImage
				+ ", blogImageArray=" + Arrays.toString(blogImageArray)
				+ ", attachment=" + attachment + ", blogImageThum="
				+ blogImageThum + "]";
	}
	
}