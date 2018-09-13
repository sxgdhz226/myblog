package top.fzqblog.po.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import top.fzqblog.po.enums.ArticleType;
import top.fzqblog.po.enums.ExamChooseType;
import top.fzqblog.po.enums.FileTopicType;
import top.fzqblog.po.enums.VoteType;

public class ArticleTypeHandler extends BaseTypeHandler<ArticleType> {

	

	@Override
	public ArticleType getNullableResult(ResultSet rs, String columnName)
			throws SQLException {
		String type = rs.getString(columnName);
		if(type != null){
			return ArticleType.getArticleTypeByType(type);
		}
		return null;
	}

	@Override
	public ArticleType getNullableResult(ResultSet rs, int columnIndex)
			throws SQLException {
		String type = rs.getString(columnIndex);
		if(type != null){
			return ArticleType.getArticleTypeByType(type);
		}
		return null;
	}

	@Override
	public ArticleType getNullableResult(CallableStatement cs,
			int columnIndex) throws SQLException {
		String type = cs.getString(columnIndex);
		if(type != null){
			return ArticleType.getArticleTypeByType(type);
		}
		return null;
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i,
			ArticleType parameter, JdbcType jdbcType) throws SQLException {
		ps.setString(i, parameter.getType());
	}

}
