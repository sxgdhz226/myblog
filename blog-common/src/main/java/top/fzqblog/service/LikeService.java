package top.fzqblog.service;

import top.fzqblog.exception.BussinessException;
import top.fzqblog.po.model.Like;
import top.fzqblog.po.query.LikeQuery;
import top.fzqblog.utils.PageResult;

public interface LikeService {
	
	public void addLike(Like like) throws BussinessException;
	
	public Like findLikeByKey(LikeQuery likeQuery);
	
	public PageResult<Like> findLikeByPage(LikeQuery likeQuery);
}
