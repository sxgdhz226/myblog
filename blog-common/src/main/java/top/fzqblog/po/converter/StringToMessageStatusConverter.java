package top.fzqblog.po.converter;

import org.springframework.core.convert.converter.Converter;

import top.fzqblog.po.enums.ArticleType;
import top.fzqblog.po.enums.ExamChooseType;
import top.fzqblog.po.enums.MessageStatus;
import top.fzqblog.po.enums.VoteType;
import top.fzqblog.utils.StringUtils;

public class StringToMessageStatusConverter implements
		Converter<String, MessageStatus> {

	public MessageStatus convert(String source) {
		String value = source.trim();
		if(StringUtils.isEmpty(value)){
			return null;
		}
		return MessageStatus.getMessageStatusByType(Integer.parseInt(value));
	}

}
