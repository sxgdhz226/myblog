package top.fzqblog.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;











import bsh.This;
import top.fzqblog.exception.BussinessException;
import top.fzqblog.po.model.Attachment;
import top.fzqblog.po.model.Blog;
import top.fzqblog.po.model.BlogCategory;
import top.fzqblog.po.query.BlogQuery;
import top.fzqblog.service.BlogCategoryService;
import top.fzqblog.service.BlogService;

@ContextConfiguration(locations = {"classpath:spring.xml"})
public class BlogServiceTest extends AbstractTestNGSpringContextTests{
	
	@Autowired
	private BlogCategoryService blogCategoryService;
	
	@Autowired
	private BlogService blogService;
	
	@Test
	public void testGetBlogCategoryList(){
		List<BlogCategory> list = this.blogCategoryService.findBlogCategoryList(10033);
		System.out.println(list);
	}
	
	@Test
	public void testAddBlogCategory() throws BussinessException{
		BlogCategory blogCategory = new BlogCategory();
		blogCategory.setName("生活趣事");
		blogCategory.setRank(1);
		blogCategory.setUserId(10034);
		this.blogCategoryService.addBlogCategory(blogCategory);
	}
	
	@Test
	public void testGetBlogList(){
		System.out.println(this.blogService.findBlogByPage(new BlogQuery()));
	}
	

	public  void testAddBlog() throws BussinessException{
		Blog blog = new Blog();
		blog.setTitle("测试");
		blog.setContent("测试博客");
		blog.setUserId(10033);
		blog.setUserName("fan220");
		blog.setUserIcon("user_icon/11.jpg");
		blog.setCategoryId(0);
		blogService.addBlog(blog, new Attachment());
	}
	
	@Test
	public void addBlogBatch() throws BussinessException{
		for(int i = 0; i < 20; i++){
			testAddBlog();
		}
	}
}
