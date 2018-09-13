package top.fzqblog.po.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;



import top.fzqblog.po.enums.BlogStatusEnum;
import top.fzqblog.po.enums.StatusEnum;

public class BlogStatusHandler extends BaseTypeHandler<BlogStatusEnum> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i,
			BlogStatusEnum parameter, JdbcType jdbcType) throws SQLException {
		ps.setInt(i, parameter.getType());
		
	}

	@Override
	public BlogStatusEnum getNullableResult(ResultSet rs, String columnName)
			throws SQLException {
		Integer type = rs.getInt(columnName);
		if(type != null){
			return BlogStatusEnum.getStatusByValue(type);
		}
		return null;
	}

	@Override
	public BlogStatusEnum getNullableResult(ResultSet rs, int columnIndex)
			throws SQLException {
		Integer type = rs.getInt(columnIndex);
		if(type != null){
			return BlogStatusEnum.getStatusByValue(type);
		}
		return null;
	}

	@Override
	public BlogStatusEnum getNullableResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		Integer type = cs.getInt(columnIndex);
		if(type != null){
			return BlogStatusEnum.getStatusByValue(type);
		}
		return null;
	}

}
