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
import top.fzqblog.mapper.KnowledgeMapper;
import top.fzqblog.mapper.LikeMapper;
import top.fzqblog.mapper.TopicMapper;
import top.fzqblog.po.enums.ArticleType;
import top.fzqblog.po.enums.TextLengthEnum;
import top.fzqblog.po.model.Ask;
import top.fzqblog.po.model.Blog;
import top.fzqblog.po.model.Collection;
import top.fzqblog.po.model.Knowledge;
import top.fzqblog.po.model.Like;
import top.fzqblog.po.model.Topic;
import top.fzqblog.po.query.AskQuery;
import top.fzqblog.po.query.BlogQuery;
import top.fzqblog.po.query.CollectionQuery;
import top.fzqblog.po.query.KnowledgeQuery;
import top.fzqblog.po.query.LikeQuery;
import top.fzqblog.po.query.TopicQuery;
import top.fzqblog.po.query.UpdateQuery4ArticleCount;
import top.fzqblog.service.LikeService;
import top.fzqblog.utils.PageResult;
import top.fzqblog.utils.StringUtils;

@Service
public class LikeServiceImpl implements LikeService {
	
	@Autowired
	private LikeMapper<Like, LikeQuery> likeMapper;

	@Autowired
	private TopicMapper<Topic, TopicQuery> topicMapper;
	
	@Autowired
	private KnowledgeMapper<Knowledge, KnowledgeQuery> knowledgeMapper;
	
	@Autowired
	private AskMapper<Ask, AskQuery> askMapper;
	
	@Autowired
	private BlogMapper<Blog, BlogQuery> blogMapper;
	
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=BussinessException.class)
	public void addLike(Like like) throws BussinessException {
		
		if(like.getArticleId() == null || like.getArticleType() == null 
				|| StringUtils.isEmpty(like.getTitle()) || 
				like.getTitle().length() > TextLengthEnum.TEXT_300_LENGTH.getLength()){
			throw new BussinessException("参数错误");
		}
		
		LikeQuery likeQuery = new LikeQuery(like.getArticleId(), 
				like.getArticleType(), like.getUserId());
		Like l = this.findLikeByKey(likeQuery);
		if(l != null){
			throw new BussinessException("您已经赞过了");
		}
		like.setCreateTime(new Date());
		this.likeMapper.insert(like);
		
		UpdateQuery4ArticleCount updateQuery4ArticleCount = new UpdateQuery4ArticleCount();
		updateQuery4ArticleCount.setAddLikeCount(Boolean.TRUE);
		updateQuery4ArticleCount.setArticleId(like.getArticleId());
		if(like.getArticleType() == ArticleType.TOPIC){
			this.topicMapper.updateInfoCount(updateQuery4ArticleCount);
		}
		else if(like.getArticleType() == ArticleType.KNOWLEDGE){
			this.knowledgeMapper.updateInfoCount(updateQuery4ArticleCount);
		}
		else if(like.getArticleType() == ArticleType.Ask){
			this.askMapper.updateInfoCount(updateQuery4ArticleCount);
		}
		else if(like.getArticleType() == ArticleType.BLOG){
			this.blogMapper.updateInfoCount(updateQuery4ArticleCount);
		}
		else{
			throw new BussinessException("参数错误");
		}
	}

	public Like findLikeByKey(LikeQuery likeQuery) {
		List<Like> likes = this.likeMapper.selectList(likeQuery);
		if(likes.isEmpty()){
			return null;
		}
		return likes.get(0);
	}

	public PageResult<Like> findLikeByPage(LikeQuery likeQuery) {
		// TODO Auto-generated method stub
		return null;
	}

}
