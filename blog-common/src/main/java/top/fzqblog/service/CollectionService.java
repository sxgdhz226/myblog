package top.fzqblog.service;

import top.fzqblog.exception.BussinessException;
import top.fzqblog.po.model.Collection;
import top.fzqblog.po.query.CollectionQuery;
import top.fzqblog.utils.PageResult;

public interface CollectionService {
	
	public void addCollection(Collection collection) throws BussinessException;
	
	public Collection findCollectionByKey(CollectionQuery collectionQuery);
	
	public PageResult<Collection> findCollectionByPage(CollectionQuery collectionQuery);
	
	public void deleteCollection(Collection collection);
}
