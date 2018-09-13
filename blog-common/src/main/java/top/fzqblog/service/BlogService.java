package top.fzqblog.service;

import java.util.List;

import top.fzqblog.exception.BussinessException;
import top.fzqblog.po.model.Attachment;
import top.fzqblog.po.model.Blog;
import top.fzqblog.po.query.BlogQuery;
import top.fzqblog.utils.PageResult;

public interface BlogService {
	
	public PageResult<Blog> findBlogByPage(BlogQuery blogQuery);
	
	public Blog getBlog(Integer blogId);
	
	public Blog showBlog(Integer blogId) throws BussinessException;
	
	public void addBlog(Blog blog, Attachment attachment) throws BussinessException;
	
	public void deleteBlog(Integer blogId) throws BussinessException;
	
	public void modifyBlog(Blog blog, Attachment attachment)throws BussinessException;
	
	public List<Blog> findBlogList();
	
	public void deleteBatch(Integer[] blogIds)throws BussinessException;
}
