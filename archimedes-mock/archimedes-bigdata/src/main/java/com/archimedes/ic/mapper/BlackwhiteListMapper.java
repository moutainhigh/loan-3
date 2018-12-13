package com.archimedes.ic.mapper;

import com.archimedes.ic.domain.BlackwhiteList;
import com.archimedes.ic.domain.BlackwhiteListKey;

public interface BlackwhiteListMapper {
    int deleteByPrimaryKey(BlackwhiteListKey key);

    int insert(BlackwhiteList record);

    int insertSelective(BlackwhiteList record);

    BlackwhiteList selectByPrimaryKey(BlackwhiteListKey key);

    int updateByPrimaryKeySelective(BlackwhiteList record);

    int updateByPrimaryKey(BlackwhiteList record);
}