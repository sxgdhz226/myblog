package top.fzqblog.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import top.fzqblog.exception.BussinessException;
import top.fzqblog.mapper.AskMapper;
import top.fzqblog.mapper.BlogMapper;
import top.fzqblog.mapper.CollectionMapper;
import top.fzqblog.mapper.KnowledgeMapper;
import top.fzqblog.mapper.TopicMapper;
import top.fzqblog.po.enums.ArticleType;
import top.fzqblog.po.enums.PageSize;
import top.fzqblog.po.enums.TextLengthEnum;
import top.fzqblog.po.model.Ask;
import top.fzqblog.po.model.Blog;
import top.fzqblog.po.model.Collection;
import top.fzqblog.po.model.Knowledge;
import top.fzqblog.po.model.Topic;
import top.fzqblog.po.query.AskQuery;
import top.fzqblog.po.query.BlogQuery;
import top.fzqblog.po.query.CollectionQuery;
import top.fzqblog.po.query.KnowledgeQuery;
import top.fzqblog.po.query.TopicQuery;
import top.fzqblog.po.query.UpdateQuery4ArticleCount;
import top.fzqblog.service.CollectionService;
import top.fzqblog.utils.Page;
import top.fzqblog.utils.PageResult;
import top.fzqblog.utils.StringUtils;

@Service
public class CollectionServiceImpl implements CollectionService {

	@Autowired
	private CollectionMapper<Collection, CollectionQuery> collectionMapper;
	
	@Autowired
	private TopicMapper<Topic, TopicQuery> topicMapper;
	
	@Autowired
	private KnowledgeMapper<Knowledge, KnowledgeQuery> knowledgeMapper;
	
	@Autowired
	private AskMapper<Ask, AskQuery> askMapper;
	
	@Autowired
	private BlogMapper<Blog, BlogQuery> blogMapper;
	
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=BussinessException.class)
	public void addCollection(Collection collection)
			throws BussinessException {
		if(collection.getArticleId() == null || collection.getArticleType() == null 
				|| StringUtils.isEmpty(collection.getTitle()) || 
				collection.getTitle().length() > TextLengthEnum.TEXT_300_LENGTH.getLength()){
			throw new BussinessException("参数错误");
		}
		
		CollectionQuery collectionQuery = new CollectionQuery(collection.getArticleId(), 
				collection.getArticleType(), collection.getUserId());
		Collection c = this.findCollectionByKey(collectionQuery);
		if(c != null){
			throw new BussinessException("您已经收藏过了");
		}
		collection.setCreateTime(new Date());
		this.collectionMapper.insert(collection);
		UpdateQuery4ArticleCount updateQuery4ArticleCount = new UpdateQuery4ArticleCount();
		updateQuery4ArticleCount.setAddCollectionCount(Boolean.TRUE);
		updateQuery4ArticleCount.setArticleId(collection.getArticleId());
		if(collection.getArticleType() == ArticleType.TOPIC){
			this.topicMapper.updateInfoCount(updateQuery4ArticleCount);
		}
		else if(collection.getArticleType() == ArticleType.KNOWLEDGE){
			this.knowledgeMapper.updateInfoCount(updateQuery4ArticleCount);
		}
		else if(collection.getArticleType() == ArticleType.Ask){
			this.askMapper.updateInfoCount(updateQuery4ArticleCount);
		}
		else if(collection.getArticleType() == ArticleType.BLOG){
			this.blogMapper.updateInfoCount(updateQuery4ArticleCount);
		}
		else{
			throw new BussinessException("参数错误");
		}
	}

	public Collection findCollectionByKey(CollectionQuery collectionQuery) {
		List<Collection> collectionList = this.collectionMapper.selectList(collectionQuery);
		if(collectionList.isEmpty()){
			return null;
		}
		return collectionList.get(0);
	}

	public PageResult<Collection> findCollectionByPage(
			CollectionQuery collectionQuery) {
		int count = this.collectionMapper.selectCount(collectionQuery);
		int pageSize = PageSize.PAGE_SIZE20.getSize();
		int pageNum = collectionQuery.getPageNum() == 1? 1:collectionQuery.getPageNum();
		Page page = new Page(pageNum, count, pageSize);
		collectionQuery.setPage(page);
		List<Collection> list = this.collectionMapper.selectList(collectionQuery);
		return new PageResult<Collection>(page, list);
	}

	public void deleteCollection(Collection collection) {
		this.collectionMapper.delete(collection);
	}

}
