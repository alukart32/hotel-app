package ru.relex.hotelteam.db.handlers;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import ru.relex.hotelteam.shared.model.Authorities;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthoritiesHandler extends BaseTypeHandler<Authorities> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Authorities parameter, JdbcType jdbcType) throws SQLException {

    }

    @Override
    public Authorities getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return null;
    }

    @Override
    public Authorities getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return null;
    }

    @Override
    public Authorities getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return null;
    }
}
