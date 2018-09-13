package top.fzqblog.po.model;

import top.fzqblog.po.enums.FileTopicType;

public class Attachment {
    private Integer attachmentId;

    private Integer topicId;

    private String fileName;

    private String fileUrl;

    private Integer downloadMark;

    private Integer downloadCount = 0;

    
    private FileTopicType fileTopicType;
    
    

    public FileTopicType getFileTopicType() {
		return fileTopicType;
	}

	public void setFileTopicType(FileTopicType fileTopicType) {
		this.fileTopicType = fileTopicType;
	}

	public Integer getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(Integer attachmentId) {
        this.attachmentId = attachmentId;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl == null ? null : fileUrl.trim();
    }

    public Integer getDownloadMark() {
        return downloadMark;
    }

    public void setDownloadMark(Integer downloadMark) {
        this.downloadMark = downloadMark;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

	@Override
	public String toString() {
		return "Attachment [attachmentId=" + attachmentId + ", topicId="
				+ topicId + ", fileName=" + fileName + ", fileUrl=" + fileUrl
				+ ", downloadMark=" + downloadMark + ", downloadCount="
				+ downloadCount + ", fileTopicType=" + fileTopicType + "]";
	}
    
    

}