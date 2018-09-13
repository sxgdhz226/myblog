package top.fzqblog.po.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.ocpsoft.prettytime.PrettyTime;
import org.springframework.format.annotation.DateTimeFormat;

import top.fzqblog.po.enums.DateTimePatternEnum;
import top.fzqblog.utils.DateUtil;

import com.fasterxml.jackson.annotation.JsonFormat;

public class User {
    private Integer userid;

    private String email;

    private String userName;

    private String password;

    private String userIcon;

    private String userBg;

    private String age;

    private String sex;

    private String characters;

    private Integer mark=0;

    private String address;

    private String work;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")  
    private Date birthday;
    
    private String birthdayString;
    
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="GMT+8")
    private Date registerTime;

    private String registerTimeString;
    
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="GMT+8")
    private Date lastLoginTime;

    private String lastLoginTimeString ;
    
    private String activationCode;
    
    private Integer userPage = 0;
    
    

    /**
	 * @return the userPage
	 */
	public Integer getUserPage() {
		return userPage;
	}

	/**
	 * @param userPage the userPage to set
	 */
	public void setUserPage(Integer userPage) {
		this.userPage = userPage;
	}

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
        this.email = email == null ? null : email.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon == null ? null : userIcon.trim();
    }

    public String getUserBg() {
        return userBg;
    }

    public void setUserBg(String userBg) {
        this.userBg = userBg == null ? null : userBg.trim();
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age == null ? null : age.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getCharacters() {
        return characters;
    }

    public void setCharacters(String characters) {
        this.characters = characters == null ? null : characters.trim();
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
        this.address = address == null ? null : address.trim();
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work == null ? null : work.trim();
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

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode == null ? null : activationCode.trim();
    }

    
    
	public String getBirthdayString() {
		if(birthday == null){
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern());
		return DateUtil.friendly_time(sdf.format(birthday));
	}

	public void setBirthdayString(String birthdayString) {
		this.birthdayString = birthdayString;
	}

	public String getRegisterTimeString() {
		SimpleDateFormat sdf = new SimpleDateFormat(DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern());
		return DateUtil.friendly_time(sdf.format(registerTime));
	}

	public void setRegisterTimeString(String registerTimeString) {
		this.registerTimeString = registerTimeString;
	}

	public String getLastLoginTimeString() {
		SimpleDateFormat sdf = new SimpleDateFormat(DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern());
		return DateUtil.friendly_time(sdf.format(lastLoginTime));
	}

	public void setLastLoginTimeString(String lastLoginTimeString) {
		this.lastLoginTimeString = lastLoginTimeString;
	}

	@Override
	public String toString() {
		return "User [userid=" + userid + ", email=" + email + ", userName="
				+ userName + ", password=" + password + ", userIcon="
				+ userIcon + ", userBg=" + userBg + ", age=" + age + ", sex="
				+ sex + ", characters=" + characters + ", mark=" + mark
				+ ", address=" + address + ", work=" + work + ", registerTime="
				+ registerTime + ", lastLoginTime=" + lastLoginTime
				+ ", activationCode=" + activationCode + "]";
	}
    
    
}