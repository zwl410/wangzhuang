package com.zhang.annotation;

import java.lang.annotation.*;

/**
 * @Auther: zhangsl
 * @Date: 2020/5/13 17:58
 * @Description: 自定义常量类注解
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ConstantName {

    String type() default "";//注解类型

    String name() default "";//注解名称

    String code() default "";//注解CODE

    String description() default "";//注解描述
}

/**
 * ElementType 这个枚举类型的常量提供了一个简单的分类：注释可能出现在Java程序中的语法位置（这些常量与元注释类型(@Target)一起指定在何处写入注释的合法位置）
 * <p>
 * RetentionPolicy这个枚举类型的常量描述保留注释的各种策略，它们与元注释(@Retention)一起指定注释要保留多长时间
 * <p>
 * Documented注解表明这个注释是由 javadoc记录的，在默认情况下也有类似的记录工具。 如果一个类型声明被注释了文档化，它的注释成为公共API的一部分。
 */

//TYPE               类, 接口 (包括注释类型), 或 枚举 声明
//FIELD              字段声明（包括枚举常量）
//METHOD             方法声明(Method declaration)
//PARAMETER          正式的参数声明
//CONSTRUCTOR        构造函数声明
//LOCAL_VARIABLE     局部变量声明
//ANNOTATION_TYPE    注释类型声明
//PACKAGE            包声明
//TYPE_PARAMETER     类型参数声明   @since 1.8
//TYPE_USE           使用的类型     @since 1.8

/**
 * RetentionPolicy这个枚举类型的常量描述保留注释的各种策略，它们与元注释(@Retention)一起指定注释要保留多长时间
 */

//SOURCE     注释只在源代码级别保留，编译时被忽略
//CLASS      注释将被编译器在类文件中记录 但在运行时不需要JVM保留。这是默认的 行为.
//RUNTIME    注释将被编译器记录在类文件中  在运行时保留VM，因此可以反读。

/**
 * Documented注解表明这个注释是由 javadoc记录的，在默认情况下也有类似的记录工具。 如果一个类型声明被注释了文档化，它的注释成为公共API的一部分。
 */