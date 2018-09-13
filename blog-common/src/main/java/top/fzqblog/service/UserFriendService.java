package top.fzqblog.service;

import top.fzqblog.exception.BussinessException;
import top.fzqblog.po.model.UserFriend;
import top.fzqblog.po.query.UserFriendQuery;
import top.fzqblog.utils.PageResult;



public interface UserFriendService {
	/**
	 * 
	 * @param userFriendQuery
	 * @return关注的用户
	 */
	public PageResult<UserFriend> findFriendList(UserFriendQuery userFriendQuery);
	
	public int findCount(UserFriendQuery userFriendQuery);
	
	public PageResult<UserFriend> findFansList(UserFriendQuery userFriendQuery);
	
	public void addFocus(UserFriend userFriend) throws BussinessException;
	
	public void cancelFocus(UserFriend userFriend) throws BussinessException;
	
	public int findFocusType4UserHome(UserFriendQuery userFriendQuery);
}
