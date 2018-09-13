package top.fzqblog.po.converter;

import org.springframework.core.convert.converter.Converter;

import top.fzqblog.po.enums.ArticleType;
import top.fzqblog.po.enums.ExamChooseType;
import top.fzqblog.po.enums.VoteType;
import top.fzqblog.utils.StringUtils;

public class StringToArticleTypeConverter implements
		Converter<String, ArticleType> {

	public ArticleType convert(String source) {
		String value = source.trim();
		if(StringUtils.isEmpty(value)){
			return null;
		}
		return ArticleType.getArticleTypeByType(value);
	}

}
