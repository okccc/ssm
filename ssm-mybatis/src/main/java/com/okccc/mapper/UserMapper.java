package com.okccc.mapper;

import com.okccc.pojo.User;
import com.okccc.pojo.User02;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author: okccc
 * @Date: 2023/10/18 16:49:53
 * @Desc: 单表查询
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
 *
 * mybatis参数类型
 * 1.实体类类型参数：mybatis会根据#{}传入的属性,通过反射在实体类对象中调用该属性的get方法,将返回值填充到?占位符位置
 * 2.简单类型参数：如果有多个零散参数需要给参数绑定@Param("key")注解,通过#{key}或者${key}获取值,不然mybatis识别不了
 * 3.Map类型参数：如果有很多零散参数需要传递,且没有对应的实体类类型可用,挨个添加@Param注解又很麻烦,此时可以封装成Map集合
 */
public interface UserMapper {

    /**
     * 实体类类型参数
     */
    void insertUser01(User user);

    void insertUser02(User02 user);

    /**
     * 单个简单类型参数
     */
    void deleteUserById(int id);

    void deleteUserByIds(String ids);

    /**
     * 多个简单类型参数
     */
    void updateUser01(@Param("username") String username, @Param("password") String password);

    /**
     * Map类型参数
     */
    void updateUser02(Map<String, Object> map);

    /**
     * 返回单个简单类型
     */
    int getCount();

    /**
     * 返回单个实体类对象
     */
    User getUserById(int id);

    /**
     * 返回多个实体类对象
     */
    List<User> getAllUser();

    /**
     * 将返回的单个实体类对象装成Map集合,区别在于User类会返回所有字段而Map会过滤value=null的key
     */
    @MapKey("id")
    Map<String,Object> getUserByIdToMap(int id);

    /**
     * 将返回的多个实体类对象封装成Map集合,有两种方法
     * 1.将Mapper接口中方法的返回值设置为泛型为Map的List集合(常用)
     * 2.将多个小Map放到一个大Map,由于Map放的是k-v对,所以要给小Map指定key,可以添加@MapKey注解将某个字段作为key
     */
    @MapKey("id")
    List<Map<String, Object>> getAllUserToMap01();

    @MapKey("id")
    Map<String, Object> getAllUserToMap02();

    /**
     * 模糊查询
     */
    List<User> getUserByLike(String str);

    /**
     * 动态传入表名,比如普通用户表和vip用户表字段都是一样的,就要根据传入的表名查询相应数据
     */
    List<User> getByTableName(String tableName);
}
