package top.fzqblog.service;

import java.util.List;
import top.fzqblog.po.model.Category;
import top.fzqblog.po.query.CategoryQuery;

public interface CategoryService {
	
	public List<Category> findCategoryList(CategoryQuery categoryQuery);
	
	public List<Category> findCategory4TopicCount(CategoryQuery categoryQuery);
	
	public Category findCategoryBypCategoryId(Integer pCategoryId);
	
	public Category findCategoryByCategoryId(Integer categoryId);
	
	public Category findSingleCategoryByCategoryId(Integer category);
}
