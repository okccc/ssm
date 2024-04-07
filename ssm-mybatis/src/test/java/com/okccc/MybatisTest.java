package com.okccc;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.io.InputStream;

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
}
