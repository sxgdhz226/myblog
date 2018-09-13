package top.fzqblog.po.converter;

import org.springframework.core.convert.converter.Converter;

import top.fzqblog.po.enums.ExamChooseType;
import top.fzqblog.utils.StringUtils;

public class StringToChooseTypeConverter implements
		Converter<String, ExamChooseType> {

	public ExamChooseType convert(String source) {
		String value = source.trim();
		if(StringUtils.isEmpty(value)){
			return null;
		}
		return ExamChooseType.getExamChooseTypeByValue(Integer.parseInt(value));
	}

}
