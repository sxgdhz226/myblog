package top.fzqblog.mapper;

import org.apache.ibatis.annotations.Param;




public interface TopicVoteMapper<T, Q> extends BaseMapper<T, Q>{
    public T selectVoteByTopicId(@Param("topicId") Integer topicId);
}