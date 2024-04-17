package com.okccc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.okccc.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: okccc
 * @Date: 2024/1/2 10:53:53
 * @Desc:
 *
 * ORM(Object-Relational Mapping)：将java对象和数据库表进行映射,用面向对象的思维通过调用方法操作数据库
 * 半自动ORM框架：xml指定实体类和数据库表的映射,手动编写sql可以优化,手动将查询结果转换成实体类(MyBatis)
 * 全自动ORM框架：自动进行实体类和数据库表的映射,自动组装sql无法优化,自动将查询结果转换成实体类(Hibernate、MyBatis-Plus)
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
