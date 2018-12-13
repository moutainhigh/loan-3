package com.archimedes.mybatis;

import com.archimedes.date.DateFormatUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.DateTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

/**
 * @create 2018/4/18
 */
public class DateTimeHandler extends DateTypeHandler {

  @Override
  public void setNonNullParameter(
      PreparedStatement ps, int i,
      Date parameter,
      JdbcType jdbcType) throws SQLException {
    String res = "";
    if (parameter != null) {
      res = DateFormatUtil.formatDate(DateFormatUtil.PATTERN_NO_STYLE, parameter);
    }
    ps.setString(i, res);
  }

  public Date getNullableResult(ResultSet rs, String columnName) throws SQLException {
    String timestamp = rs.getString(columnName);
    return getDate(timestamp);
  }

  public Date getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    String timestamp = rs.getString(columnIndex);
    return getDate(timestamp);
  }

  public Date getNullableResult(CallableStatement cs, int columnIndex)
      throws SQLException {
    String timestamp = cs.getString(columnIndex);
    return getDate(timestamp);
  }

  private static Date getDate(String timestamp) {
    try {
      return StringUtils.isNotBlank(timestamp) ?
          DateFormatUtil.parseDate(DateFormatUtil.PATTERN_NO_STYLE,timestamp)
      : null;
    } catch (ParseException e) {
      throw new RuntimeException("parse date error:",e);
    }
  }
}
