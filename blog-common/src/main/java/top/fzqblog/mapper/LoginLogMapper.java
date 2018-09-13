package top.fzqblog.mapper;

import java.util.List;

public interface LoginLogMapper<T,Q> extends BaseMapper<T, Q> {
   
	public List<T> selectListGroupByIp(Q q);
}