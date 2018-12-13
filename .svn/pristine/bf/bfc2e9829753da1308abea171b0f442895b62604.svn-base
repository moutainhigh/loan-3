package com.archimedes.mybatis;

import org.apache.ibatis.annotations.InsertProvider;

import java.util.List;

/**
 *
 * @create 2018/4/13
 */
public interface SeqInsertMapper<T> {

  @InsertProvider(
      type = SeqSpecialProvider.class,
      method = "dynamicSQL"
  )
  int insertList(List<T> recordList);
}
