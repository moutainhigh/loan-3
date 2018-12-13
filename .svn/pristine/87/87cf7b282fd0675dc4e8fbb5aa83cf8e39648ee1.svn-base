package com.archimedes.mybatis;

import com.archimedes.date.TimeUtil;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.LocalDateTimeTypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class LocalDateTimeHandler extends LocalDateTimeTypeHandler {

  @Override
  public void setNonNullParameter(
      PreparedStatement ps, int i,
      LocalDateTime parameter,
      JdbcType jdbcType) throws SQLException {
    String res = "";
    if (parameter != null) {
      res = TimeUtil.format(parameter, TimeUtil.TimeFormat.PATTERN_DEFAULT_ON_NOSTYLE);
    }
    ps.setString(i, res);
  }

  public LocalDateTime getNullableResult(ResultSet rs, String columnName) throws SQLException {
    String timestamp = rs.getString(columnName);
    return getLocalDateTime(timestamp);
  }

  public LocalDateTime getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    String timestamp = rs.getString(columnIndex);
    return getLocalDateTime(timestamp);
  }

  public LocalDateTime getNullableResult(CallableStatement cs, int columnIndex)
      throws SQLException {
    String timestamp = cs.getString(columnIndex);
    return getLocalDateTime(timestamp);
  }

  private static LocalDateTime getLocalDateTime(String timestamp) {
    return timestamp != null ? TimeUtil.parse(timestamp, TimeUtil.TimeFormat.PATTERN_DEFAULT_ON_NOSTYLE)
        : null;
  }
}