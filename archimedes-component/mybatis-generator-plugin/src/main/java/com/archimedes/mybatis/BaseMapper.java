package com.archimedes.mybatis;

import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertUseGeneratedKeysMapper;

/**
 * 通用 Mapper 接口
 *
 * @author Shaojun Liu <liushaojun@qianbao.com>
 * @create 2018/4/4
 */
public interface BaseMapper<T,PK> extends Mapper<T>
    , SeqInsertMapper<T>
    , BatchInsertMapper<T>
    , InsertUseGeneratedKeysMapper<T>
    ,IdsMapper<T>{
}
