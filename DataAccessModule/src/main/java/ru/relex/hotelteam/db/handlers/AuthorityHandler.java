package ru.relex.hotelteam.db.handlers;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import ru.relex.hotelteam.shared.model.Authority;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorityHandler extends BaseTypeHandler<Authority> {
  @Override
  public void setNonNullParameter(PreparedStatement ps, int i, Authority parameter, JdbcType jdbcType) throws SQLException {
    ps.setInt(i, parameter.getId());
  }

  @Override
  public Authority getNullableResult(ResultSet rs, String columnName) throws SQLException {
    return Authority.of(rs.getInt(columnName));
  }

  @Override
  public Authority getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    return Authority.of(rs.getInt(columnIndex));
  }

  @Override
  public Authority getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
    return Authority.of(cs.getInt(columnIndex));
  }
}
