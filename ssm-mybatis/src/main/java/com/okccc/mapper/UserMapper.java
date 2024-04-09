package com.okccc.mapper;

/**
 * @Author: okccc
 * @Date: 2023/10/18 16:49:53
 * @Desc:
 *
 * idea设置mybatis核心配置文件和映射文件模板
 * Intellij IDEA - Settings - Editor - File and Code Templates - Files - mybatis-config & mybatis-mapper
 *
 * mybatis开发步骤
 * 1.创建maven工程导入依赖
 * 2.创建mybatis-config.xml,引入属性文件、全局配置、设置类型别名、分页插件、配置数据库连接、引入映射文件
 * 3.创建Mapper接口和对应的映射文件执行crud操作
 *
 * mybatis获取参数值的两种方式
 * 1.#{}占位符赋值,where id = #{id} -> where id = ? (推荐)
 * 2.${}字符串拼接,where username = '${username}' -> where username = 'grubby' 可能存在sql注入问题
 * 能用#{}实现就不用${},特殊场景才会使用字符串拼接,比如动态的不是value值而是表名/列名/关键字
 */
public interface UserMapper {
}
