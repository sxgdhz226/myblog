package top.fzqblog.service;



import java.util.List;

import top.fzqblog.exception.BussinessException;
import top.fzqblog.po.model.Blog;
import top.fzqblog.po.model.BlogCategory;

public interface BlogCategoryService {
	
	public void addBlogCategory(BlogCategory blogCategory) throws BussinessException;
	
	public List<BlogCategory> findBlogCategoryList(Integer userId);
	
	public void deleteBlogCategory(Integer categoryId)throws BussinessException;
	
	public BlogCategory getBlogCategory(Integer categoryId);
	
	public void saveOrUpdate(BlogCategory blogCategory)throws BussinessException;
	
}
