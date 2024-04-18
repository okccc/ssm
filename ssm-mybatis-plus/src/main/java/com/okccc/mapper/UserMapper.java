package com.okccc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.okccc.pojo.User;

/**
 * @Author: okccc
 * @Date: 2024/1/2 10:53:53
 * @Desc:
 *
 * ORM(Object-Relational Mapping)：将java对象和数据库表进行映射,用面向对象的思维通过调用方法操作数据库
 * 半自动ORM框架：xml指定实体类和数据库表的映射,手动编写sql可以优化,手动将查询结果转换成实体类(MyBatis)
 * 全自动ORM框架：自动进行实体类和数据库表的映射,自动组装sql无法优化,自动将查询结果转换成实体类(Hibernate、MyBatis-Plus)
 *
 * mybatis逆向工程(半自动 -> 全自动)
 * 单表查询无需优化,要是能像Hibernate那样全自动生成就好了,于是MyBatis的逆向工程出现了,但是多表查询依然需要手动编写
 * MyBatisX插件可以自动生成持久层代码,包括MyBatis的Pojo类、Mapper.java接口、Mapper.xml映射文件,大大简化开发流程
 * 步骤：idea右侧Database插件 - 选中数据库表 - 右键MyBatisX-Generator - base package、relative package - mybatis-plus3、Lombok、Model、mybatis-plus3
 */
public interface UserMapper extends BaseMapper<User> {
}
