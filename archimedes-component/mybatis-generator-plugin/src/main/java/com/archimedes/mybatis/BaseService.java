package com.archimedes.mybatis;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * mapper 基础业务，业务类可以直接进行集成此类
 *
 * @create 2017/10/17
 */
@Slf4j
public abstract class BaseService<T,ID extends Serializable> {

  @Getter
  @Autowired
  private BaseMapper<T,ID> baseMapper;

  /**
   * 不查总数
   */
  public void buildPage(PageMeta pageMeta) {
    PageHelper.startPage(pageMeta.getPageNo(), pageMeta.getPageSize(), false);
  }

  /**
   * 根据实体中的属性值进行查询，查询条件使用等号
   */
  public List<T> find(T entity) {
    return baseMapper.select(entity);
  }

  /**
   * 分页查询
   */
  public List<T> find(T entity, PageMeta pageMeta) {
    buildPage(pageMeta);
    return baseMapper.select(entity);
  }

  /**
   * @see PageInfo 分页查询 PageInfo<T>
   */
  public PageInfo<T> findAsPage(T entity, PageMeta pageMeta) {
    return new PageInfo<T>(find(entity, pageMeta));
  }


  /**
   * 根据主键字段进行查询， 方法参数必须包含完整的主键属性， 查询条件使用等号
   *
   * @return Optional<T>
   */
  public Optional<T> findById(ID id) {
    T res = baseMapper.selectByPrimaryKey(id);
    return Optional.ofNullable(res);
  }

  /**
   * 以(,)分割，英文
   * @param ids
   * @return
   */
  public List<T> findByIds(String ids){
    Assert.notNull(ids,"ids can't be null");
    return baseMapper.selectByIds(ids);
  }

  /**
   * 查询全部结果，select(null)方法能达到同样的效果
   */
  public List<T> findAll() {
    return baseMapper.selectAll();
  }

  /**
   * 根据实体中的属性进行查询， 只能有一个返回值， 查询条件使用等号
   *
   * @return Optional<T>
   */
  public Optional<T> findOne(T entity) {
    T result = null;
    List<T> list = this.find(entity);
    if (list != null && !list.isEmpty()) {
      result = list.get(0);
    }
    return Optional.ofNullable(result);
  }

  /**
   * 根据实体中的属性查询总数，查询条件使用等号
   */
  public long count(T entity) {
    return baseMapper.selectCount(entity);
  }

  public long countByExample(Object example) {
    return baseMapper.selectCountByExample(example);
  }

  /**
   * 保存一个实体
   * @param entity
   * @param ignoreNull 是否或略null
   * @return
   */
  @Transactional
  public int create(T entity, boolean ignoreNull) {
    if(ignoreNull){
      return baseMapper.insertSelective(entity);
    }else{
      return baseMapper.insert(entity);
    }
  }

  /**
   * 创建一个实体，null的属性不会保存，会使用数据库默认值
   */
  @Transactional
  public int create(T entity) {
    return this.create(entity, true);
  }

  /**
   * 批量插入 id 必须是自增
   */
  @Transactional
  public int createBatch(List<T> entitys) {
    return baseMapper.insertList(entitys);
  }

  /**
   * 插入数据，限制为实体包含id属性并且必须为自增列， 实体配置的主键策略无效
   */
  @Transactional
  public int createUseGeneratedKeys(T entity) {
    return baseMapper.insertUseGeneratedKeys(entity);
  }

  /**
   * 根据实体属性作为条件进行删除，查询条件使用等号
   */
  @Transactional
  public int delete(T entity) {
    return baseMapper.delete(entity);
  }

  /**
   * 根据主键删除
   */
  @Transactional
  public int deleteById(ID id) {
    return baseMapper.deleteByPrimaryKey(id);
  }

  /**
   * 根据条件删除
   */
  @Transactional
  public int deleteByExample(Object example) {

    return baseMapper.deleteByExample(example);
  }

  /**
   *
   * @param entity
   * @param ignoreNull true 忽略null属性,会采用数据库默认属性
   * @return int 更新条数
   */
  @Transactional
  public int update(T entity,boolean ignoreNull) {
    if(ignoreNull){
      return baseMapper.updateByPrimaryKeySelective(entity);
    }else{
      return baseMapper.updateByPrimaryKey(entity);
    }

  }

  /**
   * 根据主键更新属性不为null的值
   */
  @Transactional
  public int update(T entity) {
    return this.update(entity,true);
  }

  /**
   * 条件查询
   * ```
   * Example condition = new Example(LogInfo.class);
   * condition.createCriteria() .
   * andGreaterThanOrEqualTo("id", 3);
   * SQL select *from log_info where id >=3;
   * ```
   *
   * @see tk.mybatis.mapper.entity.Example
   * @see tk.mybatis.mapper.entity.Example.Criteria
   */
  public List<T> findByExample(Object example) {
    return baseMapper.selectByExample(example);
  }

  /**
   * 分页查询不查总数
   *
   * @return PageInfo<T>
   */
  public PageInfo<T> findByExampleAsPage(Object example, PageMeta pageMeta) {
    return new PageInfo<T>(findByExample(example, pageMeta));
  }

  /**
   * 分页查询
   *
   * @return List<T>
   */
  public List<T> findByExample(Object example, PageMeta pageMeta) {
    buildPage(pageMeta);
    return findByExample(example);
  }

  /**
   * @param entity 更新的实体
   * @param example 条件
   * @param ignoreNull 忽略null值,采用数据库默认值
   * @return int 影响条数
   */
  public int update(T entity, Object example, boolean ignoreNull) {
    if(ignoreNull){
      return baseMapper.updateByExampleSelective(entity, example);
    }else {
      return baseMapper.updateByExample(entity, example);
    }
  }

  /**
   * 根据条件更新 忽略null值,采用数据库默认值
   */
  @Transactional
  public int update(T entity, Object example) {
    return this.update(entity, example ,true);
  }

  /**
   * mybatis 默认分页 建议使用{@link PageHelper#startPage(int, int)}
   * 紧跟着的第一个查询方法会被分页,返回结果(Page)list,强转成Page类型
   */
  public List<T> findByRowBounds(T entity, RowBounds rowBounds) {
    return baseMapper.selectByRowBounds(entity, rowBounds);
  }

  /**
   * 分页查询
   */
  public PageInfo<T> findAllAsPage(PageMeta page) {
    return new PageInfo<T>(findAll(page));
  }

  /**
   *
   * @param page
   * @return
   */
  public List<T> findAll(PageMeta page) {
    buildPage(page);
    return baseMapper.selectAll();
  }
}