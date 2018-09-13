package top.fzqblog.po.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import top.fzqblog.po.enums.ExamChooseType;
import top.fzqblog.po.enums.SolveEnum;

public class SolveTypeHandler extends BaseTypeHandler<SolveEnum> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i,
			SolveEnum parameter, JdbcType jdbcType) throws SQLException {
		// TODO Auto-generated method stub
		ps.setInt(i, parameter.getType());
	}

	@Override
	public SolveEnum getNullableResult(ResultSet rs, String columnName)
			throws SQLException {
		Integer type = rs.getInt(columnName);
		if(type != null){
			return SolveEnum.getSolveEnumByType(type);
		}
		return null;
	}

	@Override
	public SolveEnum getNullableResult(ResultSet rs, int columnIndex)
			throws SQLException {
		Integer type = rs.getInt(columnIndex);
		if(type != null){
			return SolveEnum.getSolveEnumByType(type);
		}
		return null;
	}

	@Override
	public SolveEnum getNullableResult(CallableStatement cs,
			int columnIndex) throws SQLException {
		Integer type = cs.getInt(columnIndex);
		if(type != null){
			return SolveEnum.getSolveEnumByType(type);
		}
		return null;
	}



}
