package top.fzqblog.service;

import java.util.List;

import top.fzqblog.exception.BussinessException;
import top.fzqblog.po.model.User;
import top.fzqblog.po.vo.UserVo;

public interface UserService {
	public void register(User user) throws BussinessException;
	
	public User findUserByUserName(String userName);
	
	public User findUserByEmail(String email);
	public User findUserByUserid(Integer userid);
	
	public User login(String account, String password) throws BussinessException;
	public User login(String account, String password,boolean isNeedEncoder) throws BussinessException;
	public void sendCheckCode(String email) throws BussinessException;
	
	public void modifyPassword(String email, String password, String checkcode) throws BussinessException;
	
	public void addMark(int mark, int userid);
	
	public Integer changeMark(int userid, int mark);
	public User findUserInfo4UserHome(Integer userId) throws BussinessException;
	
	public void updateUserInfo(User user) throws BussinessException;
	
	public void updatePassword(Integer userId, String oldPassword, String newPassword) throws BussinessException;
	
	public void copyUserIcon(String source,String dest);
	
	public void updateUserWithoutValidate(User user);
	
	public String findHeadIcon(String account)throws BussinessException;
	
	public List<User> findAllUsers();
	
	public void deleteUser(Integer[] userIds) throws BussinessException;
	
	public List<UserVo> findUserVoList();
	
	public void updateUserRole(Integer userId, Integer[] roleIds)throws BussinessException;
	
	public void updateBatchUserRole(Integer[] userId, Integer[] roleIds)throws BussinessException;
	
	public void markChangeAdvice(Integer[] userIds, Integer mark, String des)throws BussinessException;
	
}
