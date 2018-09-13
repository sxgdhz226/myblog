package top.fzqblog.po.converter;

import org.springframework.core.convert.converter.Converter;

import top.fzqblog.po.enums.ExamChooseType;
import top.fzqblog.po.enums.TopicType;
import top.fzqblog.po.enums.VoteType;
import top.fzqblog.utils.StringUtils;

public class StringToTopicTypeConverter implements
		Converter<String, TopicType> {

	public TopicType convert(String source) {
		String value = source.trim();
		if(StringUtils.isEmpty(value)){
			return null;
		}
		return TopicType.getTopicTypeByValue(Integer.parseInt(value));
	}

}
