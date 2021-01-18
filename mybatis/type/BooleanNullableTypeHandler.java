package com.gsitm.career.mybatis.type;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

/**
 * @author YongSang
 */
public class BooleanNullableTypeHandler implements TypeHandler<Boolean> {
    @Override
    public void setParameter(PreparedStatement ps, int i, Boolean parameter, JdbcType jdbcType) throws SQLException {
        if (parameter == Boolean.TRUE) {
            ps.setString(i, "1");
        } else if (parameter == Boolean.FALSE) {
            ps.setString(i, "0");
        } else {
            ps.setString(i, null);
        }
    }

    @Override
    public Boolean getResult(ResultSet rs, String columnName) throws SQLException {
        Boolean result = rs.getBoolean(columnName);
        return rs.wasNull() ? null : result;
    }

    @Override
    public Boolean getResult(ResultSet rs, int columnIndex) throws SQLException {
        Boolean result = rs.getBoolean(columnIndex);
        return rs.wasNull() ? null : result;
    }

    @Override
    public Boolean getResult(CallableStatement cs, int columnIndex) throws SQLException {
        Boolean result = cs.getBoolean(columnIndex);
        return cs.wasNull() ? null : result;
    }
}