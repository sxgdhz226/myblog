package top.fzqblog.po.enums;

public enum OrderByEnum {
	LAST_COMMENT_TIME_DESC_CREATE_TIME_DESC("essence desc, grade desc, last_comment_time desc, create_time desc", "根据帖子等级, 最后回复时间, 发表时间"),
	CREATE_TIME_DESC("create_time desc", "根据提问时间倒序排序"),
	MESSAGE_STATUS_ASC_CREATE_TIME_DESC("status asc, create_time desc", "根据提问时间倒序排序");
	
	private String orderBy;
	private String desc;
	
	private OrderByEnum(String orderBy, String desc) {
		this.orderBy = orderBy;
		this.desc = desc;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
}
