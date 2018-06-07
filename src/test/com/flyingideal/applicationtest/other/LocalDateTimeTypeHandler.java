package com.flyingideal.applicationtest.other;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.*;
import java.time.LocalDateTime;

/**
 * Created by Administrator on 2017/3/21.
 * MyBatis 处理 LocalDateTime 的typeHandler，使用mybatis-typehandlers-jsr310.jar提供了处理方法
 * 参考 https://github.com/mybatis/typehandlers-jsr310
 */
public class LocalDateTimeTypeHandler extends BaseTypeHandler<LocalDateTime> {

    public LocalDateTimeTypeHandler() {}

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, LocalDateTime parameter, JdbcType jdbcType) throws SQLException {
        ps.setTimestamp(i, Timestamp.valueOf(parameter));
    }

    @Override
    public LocalDateTime getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Timestamp sqlTimestamp = rs.getTimestamp(columnName);
        return sqlTimestamp != null ? sqlTimestamp.toLocalDateTime() : null;
    }

    @Override
    public LocalDateTime getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Timestamp sqlTimestamp = rs.getTimestamp(columnIndex);
        return sqlTimestamp != null ? sqlTimestamp.toLocalDateTime() : null;
    }

    @Override
    public LocalDateTime getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Timestamp sqlTimestamp = cs.getTimestamp(columnIndex);
        return sqlTimestamp != null ? sqlTimestamp.toLocalDateTime() : null;
    }
}
