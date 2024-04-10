package com.okccc;

import com.okccc.mapper.UserMapper;
import com.okccc.pojo.User;
import com.okccc.pojo.User02;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/**
 * @Author: okccc
 * @Date: 2023/10/18 18:29:46
 * @Desc:
 */
public class MybatisTest {

    private SqlSession sqlSession;

    // junit5会在每个@Test方法前执行@BeforeEach方法,或者放静态代码块也可以
    @BeforeEach
    public void init() throws IOException {
        // 读取mybatis核心配置文件
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");

        // 创建SqlSessionFactory对象
        // 工厂模式：如果创建某个对象的过程基本固定,就可以将其封装到工厂类中,以后都由工厂类生产该对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 创建SqlSession对象,SqlSession是java程序和数据库之间的会话,HttpSession是java程序和浏览器之间的会话
        sqlSession = sqlSessionFactory.openSession(true);
    }

    // junit5会在每个@Test方法后执行@AfterEach方法
    @AfterEach
    public void clear() {
        sqlSession.close();
    }

    @Test
    public void testParam() {
        // 创建UserMapper接口的代理实现类对象(jdk动态代理)
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        // 执行crud操作,会根据Mapper接口的全类名去映射文件匹配方法名对应的sql语句并执行

        // 1.实体类类型参数
        // 插入数据,主键id是自增列
        User user01 = new User(null, "grubby", "orc", 19, "男", "orc@qq.com");
        System.out.println(user01);  // User{id=null, username='grubby', ...}
        userMapper.insertUser01(user01);
        System.out.println(user01);  // User{id=1, username='grubby', ...}

        // 插入数据,主键id是UUID
        User02 user02 = new User02(null, "moon", "ne", 19, "女", "ne@qq.com");
        System.out.println(user02);  // User02{id='null', username='moon', ...}
        userMapper.insertUser02(user02);
        System.out.println(user02);  // User02{id='60cb3cde731411ee8c6fdf5e37f73628', username='moon', ...}

        // 2.单个简单类型参数
        userMapper.deleteUserById(2);
        userMapper.deleteUserByIds("54, 55");

        // 3.多个简单类型参数
        userMapper.updateUser01("grubby", "123456");

        // 4.Map类型参数
        HashMap<String, Object> map = new HashMap<>();
        map.put("username", "moon");
        map.put("password", "ne");
        map.put("age", 20);
        userMapper.updateUser02(map);
    }
}
