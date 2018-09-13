package top.fzqblog.po.vo;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import top.fzqblog.po.model.SysRole;

public class UserVo {
    private Integer userid;

    private String email;

    private String userName;

    private String userIcon;

    private String age;

    private String sex;

    private String characters;

    private Integer mark=0;

    private String address;

    private String work;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")  
    private Date birthday;
    
    
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date registerTime;

    
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date lastLoginTime;
    
    
    private List<SysRole> rolesList;


	public Integer getUserid() {
		return userid;
	}


	public void setUserid(Integer userid) {
		this.userid = userid;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getUserIcon() {
		return userIcon;
	}


	public void setUserIcon(String userIcon) {
		this.userIcon = userIcon;
	}


	public String getAge() {
		return age;
	}


	public void setAge(String age) {
		this.age = age;
	}


	public String getSex() {
		return sex;
	}


	public void setSex(String sex) {
		this.sex = sex;
	}


	public String getCharacters() {
		return characters;
	}


	public void setCharacters(String characters) {
		this.characters = characters;
	}


	public Integer getMark() {
		return mark;
	}


	public void setMark(Integer mark) {
		this.mark = mark;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getWork() {
		return work;
	}


	public void setWork(String work) {
		this.work = work;
	}


	public Date getBirthday() {
		return birthday;
	}


	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}


	public Date getRegisterTime() {
		return registerTime;
	}


	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}


	public Date getLastLoginTime() {
		return lastLoginTime;
	}


	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}


	public List<SysRole> getRolesList() {
		return rolesList;
	}


	public void setRolesList(List<SysRole> rolesList) {
		this.rolesList = rolesList;
	}
    
    
    
    
    
}
