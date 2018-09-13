package top.fzqblog.mapper;

import org.apache.ibatis.annotations.Param;



public interface AttachmentMapper<T, Q> extends BaseMapper<T, Q> {
	public void updateDownloadCount(@Param("attachmentId") Integer attachmentId);
	
	public void delete(@Param("attachmentId") Integer attachmentId);
}