package top.fzqblog.mapper;

import org.apache.ibatis.annotations.Param;

public interface BlogCategoryMapper<T, Q> extends BaseMapper<T, Q> {
    public void delete(@Param("categoryId") Integer categoryId);
}