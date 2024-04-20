package com.okccc.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: okccc
 * @Date: 2024/1/2 10:50:21
 * @Desc: mybatis-plus核心注解
 *
 * 1.@TableName
 * 作用于实体类,默认情况下,实体类名称就是数据库表名(忽略大小写),属性名对应字段名
 * 但不是所有实体类都和数据库表完全映射,比如实体类是User而表名是t_user,此时操作表就会报错
 * 方式1：给每个实体类添加注解标识
 * 方式2：在application.yaml全局配置,给表名统一加前缀"t_"(推荐)
 *
 * 2.@TableId
 * 作用于主键列,mysql数据库的主键策略默认是auto_increment,如果要使用其它策略可以添加该注解修改
 *
 * 3.@TableField
 * 作用于非主键列,当实体类属性名和数据库列名不一致时添加该注解指定
 *
 * CREATE TABLE `t_user` (
 *   `id` bigint(20) PRIMARY KEY NOT NULL,
 *   `username` varchar(20) DEFAULT NULL,
 *   `password` varchar(20) DEFAULT NULL,
 *   `age` int(11) DEFAULT NULL,
 *   `gender` char(1) DEFAULT NULL,
 *   `email` varchar(20) DEFAULT NULL
 * ) ENGINE=InnoDB DEFAULT CHARSET=utf8
 *
 * CREATE TABLE `t_user1` (
 *   `id` bigint(20) PRIMARY KEY AUTO_INCREMENT NOT NULL,
 *   `username` varchar(20) DEFAULT NULL,
 *   `password` varchar(20) DEFAULT NULL,
 *   `age` int(11) DEFAULT NULL,
 *   `gender` char(1) DEFAULT NULL,
 *   `email` varchar(20) DEFAULT NULL,
 *   `deleted` int(11) DEFAULT 0,
 *   `version` int(11) DEFAULT 1
 * ) ENGINE=InnoDB DEFAULT CHARSET=utf8
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "t_user")
public class User {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField(value = "username", exist = true)
    private String username;

    private String password;

    private Integer age;

    private String gender;

    private String email;

}
