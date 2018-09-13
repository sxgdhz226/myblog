package top.fzqblog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import top.fzqblog.po.vo.UserVo;

@Repository
public interface UserMapper<T, Q>  extends BaseMapper<T, Q>{
	public Integer changeUserMark(@Param(value = "changeMark") Integer changeMark, @Param(value = "userId") Integer userId);
	
	public void delete(@Param("ids") Integer[] ids);
	
	public List<UserVo> selectUserVoList();
	
}