package top.fzqblog.service.impl;

import java.util.ArrayList;
import java.util.List;









import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import freemarker.core._RegexBuiltins.matchesBI;
import top.fzqblog.cache.CategoryCache;
import top.fzqblog.mapper.CategoryMapper;
import top.fzqblog.po.model.Category;
import top.fzqblog.po.query.CategoryQuery;
import top.fzqblog.service.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryMapper<Category, CategoryQuery> categoryMapper;
	
	@Autowired
	private CategoryCache categoryCache;

	public List<Category> findCategoryList(CategoryQuery categoryQuery) {
		List<Category> list =  categoryMapper.selectList(categoryQuery);
		list = getChildren(list, 0);
		return list;
	}
	
	public static List<Category> getChildren(List<Category> categories, int id){
		List<Category> children = new ArrayList<Category>();
		for(Category category : categories){
			if(category.getPid() == id){
				category.setChildren(getChildren(categories, category.getCategoryId()));
				children.add(category);
			}
		}
		return children;
	}

	public List<Category> findCategory4TopicCount(CategoryQuery categoryQuery) {
		List<Category> list =  categoryMapper.selectCategory4TopicCount(categoryQuery);
		list = getChildren(list, 0);
		return list;
	}

	public Category findCategoryBypCategoryId(Integer pCategoryId) {
		List<Category> bbCategories = categoryCache.getBbsCategories();
		for(Category category : bbCategories){
			if(category.getCategoryId() == pCategoryId){
				return category;
			}
		}
		return null;
	}

	public Category findCategoryByCategoryId(Integer categoryId) {
		List<Category> bbCategories = categoryCache.getBbsCategories();
		for(Category category : bbCategories){
			List<Category> children = category.getChildren();
			for(Category c : children){
				if(c.getCategoryId() == categoryId){
					return category;
				}
			}
		}
		return null;
	}

	public Category findSingleCategoryByCategoryId(Integer categoryId) {
		List<Category> bbCategories = categoryCache.getBbsCategories();
		for(Category category : bbCategories){
			List<Category> children = category.getChildren();
			for(Category c : children){
				if(c.getCategoryId() == categoryId){
					return c;
				}
			}
		}
		return null;
	}

}
