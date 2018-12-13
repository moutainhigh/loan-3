package com.archimedes.mybatis;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页信息
 *
 * @create 16/10/20
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel("分页信息")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PageMeta {

  public final static int DEFAULT_SIZE = 15;
  private final static int MAX_SIZE = 1000;
  public static final String DESC = "desc";
  public static final String ASC = "asc";

  @ApiModelProperty("排序字段")
  @Getter
  @Setter
  private List<Sort> sorts = new ArrayList<>();
  @ApiModelProperty(value = "页码,默认从1开始", example = "1")
  private Integer pageNo = 1;
  @ApiModelProperty(value = "页容量,默认15", example = "15")
  private Integer pageSize = DEFAULT_SIZE;

  public PageMeta() {
    this.pageNo = 1;
    this.pageSize = DEFAULT_SIZE;
  }

  @ApiModelProperty(hidden = true)
  private Long total;

  @ApiModelProperty(hidden = true)
  protected Object data;

  public PageMeta(Integer pageNo, Integer pageSize) {
    this.pageNo = pageNo == null ? 1 : pageNo;
    this.pageSize = ajust(pageSize);
  }

  public Integer getPageNo() {
    return pageNo;
  }

  public Integer getPageNum() {
    return this.getPageNo();
  }

  public Integer getOffset() {
    if (this.getPageNo() <= 0) {
      return 0;
    }
    return (this.getPageNo() - 1) * this.getLimit();
  }

  public Integer getLimit() {
    return this.getPageSize();
  }

  public void setPageNo(Integer pageNo) {
    this.pageNo = pageNo == null ? 1 : pageNo;
  }

  public Integer getPageSize() {
    return pageSize;
  }

  public void setPageSize(Integer pageSize) {
    this.pageSize = ajust(pageSize);
  }

  private int ajust(Integer value) {
    if (value == null || value < 0 || value > MAX_SIZE) {
      return DEFAULT_SIZE;
    }
    return value;
  }

  @ApiModel("排序模型")
  @Data
  public static class Sort {

    @ApiModelProperty("排序字段")
    private String name;
    @ApiModelProperty("顺序 : true 降序,false 升序")
    private Boolean order = true;

    @ApiModelProperty(hidden = true)
    private String desc = DESC.toUpperCase();

    public Sort() {
    }

    public Sort(String name, boolean order) {
      this();
      this.name = name;
      this.order = order;
      adjust(order);
    }

    private void adjust(boolean order) {
      if (!order) {
        this.desc = ASC.toUpperCase();
      }
    }

    public void setOrder(boolean order) {
      this.order = order;
      adjust(order);
    }

  }
}