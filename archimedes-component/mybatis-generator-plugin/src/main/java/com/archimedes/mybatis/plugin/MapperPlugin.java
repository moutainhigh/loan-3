package com.archimedes.mybatis.plugin;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.List;

/**
 *
 */
public class MapperPlugin extends tk.mybatis.mapper.generator.MapperPlugin {

  private FullyQualifiedJavaType dataAnnotation;

  /**
   * LombokPlugin contructor
   */
  public MapperPlugin() {
    dataAnnotation = new FullyQualifiedJavaType("lombok.Data");
  }

  /**
   * @return always true
   */
  @Override
  public boolean validate(List<String> warnings) {
    return true;
  }

  /**
   * Intercepts base record class generation
   */
  @Override
  public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass,
      IntrospectedTable introspectedTable) {
    addDataAnnotation(topLevelClass);
    super.modelBaseRecordClassGenerated(topLevelClass, introspectedTable);
    return true;
  }

  /**
   * Intercepts primary key class generation
   */
  @Override
  public boolean modelPrimaryKeyClassGenerated(TopLevelClass topLevelClass,
      IntrospectedTable introspectedTable) {
    addDataAnnotation(topLevelClass);
    return true;
  }

  /**
   * Intercepts "record with blob" class generation
   */
  @Override
  public boolean modelRecordWithBLOBsClassGenerated(
      TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
    addDataAnnotation(topLevelClass);
    return true;
  }

  /**
   * Prevents all getters from being generated. See SimpleModelGenerator
   */
  @Override
  public boolean modelGetterMethodGenerated(Method method,
      TopLevelClass topLevelClass,
      IntrospectedColumn introspectedColumn,
      IntrospectedTable introspectedTable,
      ModelClassType modelClassType) {
    return false;
  }

  /**
   * Prevents all setters from being generated See SimpleModelGenerator
   */
  @Override
  public boolean modelSetterMethodGenerated(Method method,
      TopLevelClass topLevelClass,
      IntrospectedColumn introspectedColumn,
      IntrospectedTable introspectedTable,
      ModelClassType modelClassType) {
    return false;
  }

  /**
   * Adds the @Data lombok import and annotation to the class
   */
  protected void addDataAnnotation(TopLevelClass topLevelClass) {
    topLevelClass.addImportedType(dataAnnotation);
    topLevelClass.addAnnotation("@Data");
  }
}
