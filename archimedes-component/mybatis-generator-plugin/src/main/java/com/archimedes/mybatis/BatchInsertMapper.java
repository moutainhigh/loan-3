package com.archimedes.mybatis;

import org.apache.ibatis.annotations.InsertProvider;

import java.util.List;

/**
 * @create 2018/7/17
 */
public interface BatchInsertMapper<T> {

  /**
   * 批量插入，支持批量插入的数据库可以使用，例如MySQL,H2等
   * <p>
   * 不支持主键策略，插入前需要设置好主键的值
   *
   * @param recordList
   * @return
   */
  @InsertProvider(type = BatchInsertProvider.class, method = "dynamicSQL")
  int batchInsert(List<T> recordList);

}
