package top.fzqblog.po.converter;

import org.springframework.core.convert.converter.Converter;

import top.fzqblog.po.enums.ExamChooseType;
import top.fzqblog.po.enums.VoteType;
import top.fzqblog.utils.StringUtils;

public class StringToVoteTypeConverter implements
		Converter<String, VoteType> {

	public VoteType convert(String source) {
		String value = source.trim();
		if(StringUtils.isEmpty(value)){
			return null;
		}
		return VoteType.getVoteTypeByValue(Integer.parseInt(value));
	}

}
