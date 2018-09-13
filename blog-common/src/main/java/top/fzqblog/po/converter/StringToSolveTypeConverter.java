package top.fzqblog.po.converter;

import org.springframework.core.convert.converter.Converter;

import top.fzqblog.po.enums.ArticleType;
import top.fzqblog.po.enums.ExamChooseType;
import top.fzqblog.po.enums.SolveEnum;
import top.fzqblog.po.enums.VoteType;
import top.fzqblog.utils.StringUtils;

public class StringToSolveTypeConverter implements
		Converter<String, SolveEnum> {

	public SolveEnum convert(String source) {
		if(StringUtils.isEmpty(source)){
			return null;
		}
		return SolveEnum.getSolveEnumByType(Integer.parseInt(source));
	}

}
