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
 * 数据库表太大要做数据拆分
 * 垂直拆分是列拆分,经常查询的列就是热数据,冷热数据是一条数据拆开的,所以是同一个主键,新增数据也就不存在主键重复的问题
 * 水平拆分是行拆分,比如上亿条数据必须分库分表,如果主键策略是自增长,拆分的多个表新增数据会出现主键重复的问题,此时可以用UUID或雪花算法
 * UUID：随机生成一个不重复的字符串,算法复杂且字符串太长效率较低
 * 雪花算法：随机生成一个不重复的数字,实体类用Long或String,数据库用bigint或varchar,解决分布式系统中生成全局唯一id的需求
 *
 * 3.@TableField
 * 作用于非主键列,当实体类属性名和数据库列名不一致时添加该注解指定
 *
 * 4.@TableLogic
 * alter table t_user add deleted int default 0;
 * 逻辑删除,默认0表示未删除,执行删除操作时会将该属性修改为1,查询操作只查deleted=0的数据
 * 添加逻辑删除前是DELETE操作 ==> Preparing: DELETE FROM t_user WHERE id=?
 * 添加逻辑删除后是UPDATE操作 ==> Preparing: UPDATE t_user SET deleted=1 WHERE id=? AND deleted=0
 *
 * 5.@Version
 * alter table t_user add version int default 1;
 * 乐观锁版本号,解决并发场景修改数据的安全问题,针对updateById(id)和update(entity,wrapper)
 * 乐观锁和悲观锁是并发编程中处理并发访问和资源竞争的两种不同的锁机制
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
//    @TableId(type = IdType.ASSIGN_UUID)
//    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    @TableField(value = "username", exist = true)
    private String username;

    private String password;

    private Integer age;

    private String gender;

    private String email;

    @TableLogic
    private Integer deleted;

    @Version
    private Integer version;

    public User(Long id, String username, String password, Integer age, String gender, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.age = age;
        this.gender = gender;
        this.email = email;
    }

}
