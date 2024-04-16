package com.okccc;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.okccc.mapper.*;
import com.okccc.pojo.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Test
    public void testSelect() {
        // 单表查询
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        // 返回单个简单类型
        int count = userMapper.getCount();
        System.out.println(count);

        // 返回单个实体类对象
        User user01 = userMapper.getUserById(1);
        System.out.println(user01);

        // 返回多个实体类对象的列表
        List<User> users = userMapper.getAllUser();
        System.out.println(users);

        // 将查询结果User实体类转换成Map集合
        Map<String, Object> map01 = userMapper.getUserByIdToMap(1);
        System.out.println(map01);
        List<Map<String, Object>> list01 = userMapper.getAllUserToMap01();
        System.out.println(list01);
        Map<String, Object> map02 = userMapper.getAllUserToMap02();
        System.out.println(map02);

        // 模糊查询
        List<User> list02 = userMapper.getUserByLike("g");
        System.out.println(list02);

        // 动态传入表名查询
        List<User> list03 = userMapper.getByTableName("t_user");
        System.out.println(list03);
    }

    @Test
    public void testGetEmpById() {
        // 多表查询之"对一"
        EmpMapper empMapper = sqlSession.getMapper(EmpMapper.class);

        // 直接关联,执行了1条sql
        // ==>  Preparing: select * from t_emp a join t_dept b on a.dept_id = b.dept_id where a.emp_id = ?
        Emp emp01 = empMapper.getEmpById(1);
        System.out.println(emp01);  // Emp(empId=1, empName=grubby, deptId=1, dept=Dept(deptId=1, deptName=A, empList=null))

        // 分步查询,执行了2条sql
        // ==>  Preparing: select * from t_emp where emp_id = ?
        // ==>  Preparing: select * from t_dept where dept_id = ?
        Emp emp02 = empMapper.getEmpByIdStepOne(1);
        System.out.println(emp02);  // Emp(empId=1, empName=grubby, deptId=1, dept=Dept(deptId=1, deptName=A, empList=null))
    }

    @Test
    public void testGetDeptById() {
        // 多表查询之"对多"
        DeptMapper deptMapper = sqlSession.getMapper(DeptMapper.class);

        // 直接关联,执行了1条sql
        // ==>  Preparing: select * from t_dept a join t_emp b on a.dept_id = b.dept_id where a.dept_id = ?
        Dept dept01 = deptMapper.getDeptById(1);
        System.out.println(dept01);  // Dept(deptId=1, deptName=A, empList=[Emp(empId=1, empName=grubby, deptId=1, dept=null)])

        // 分步查询,执行了2条sql
        // ==>  Preparing: select * from t_dept where dept_id = ?
        // ==>  Preparing: select * from t_emp where dept_id = ?
        Dept dept02 = deptMapper.getDeptByIdStepOne(1);
        System.out.println(dept02);  // Dept(deptId=1, deptName=A, empList=[Emp(empId=1, empName=grubby, deptId=1, dept=null)])
    }

    @Test
    public void testDynamicSql() {
        // 动态拼接sql
        BookMapper bookMapper = sqlSession.getMapper(BookMapper.class);

        // 批量添加
        Book book01 = new Book(null, "java", 999.00, 100);
        Book book02 = new Book(null, "python", 699.00, 100);
        Book book03 = new Book(null, "sql", 299.00, 100);
        bookMapper.insertBatch(Arrays.asList(book01, book02, book03));

        // 批量修改
        Book book04 = new Book(10, "scala", 699.00, 100);
        Book book05 = new Book(11, "golang", 599.00, 100);
        Book book06 = new Book(12, "ruby", 299.00, 100);
        bookMapper.updateBatch(Arrays.asList(book04, book05, book06));

        // 批量删除
        bookMapper.deleteBatch(new Integer[]{21, 22, 23});

        // 批量查询
        System.out.println(bookMapper.selectBatch(new Integer[]{1, 2, 3}));

        // 条件查询
        System.out.println(bookMapper.getBookByWhere("java", 399.0));
        System.out.println(bookMapper.getBookByTrim("java", 399.0));
        System.out.println(bookMapper.getBookByChoose("java", 399.0));
    }

    @Test
    public void testPageHelper() {
        /*
         * 分页插件使用步骤：
         * 1.pom.xml添加依赖
         * 2.在mybatis核心配置文件配置<plugins>
         *
         * 分页相关数据
         * PageInfo{
         *     pageNum=1, pageSize=5, size=5, startRow=1, endRow=5, total=11, pages=3,
         *     list=Page{count=true, pageNum=1, pageSize=5, startRow=0, endRow=5, total=11, pages=3, reasonable=false, pageSizeZero=false}
         *     [Book(bookId=3, bookName=java, price=999.0, stock=100), Book(bookId=12, bookName=java, price=999.0, stock=100), ...],
         *     prePage=0, nextPage=2, isFirstPage=true, isLastPage=false, hasPreviousPage=false, hasNextPage=true,
         *     navigatePages=8, navigateFirstPage=1, navigateLastPage=3, navigatepageNums=[1, 2, 3]
         * }
         *
         * pageNum：当前页码
         * pageSize：每页显示条数
         * size：当前页显示的真实条数
         * total：总记录数
         * pages：总页数
         * prePage：上一页的页码
         * nextPage：下一页的页码
         * isFirstPage/isLastPage：是否为第一页/最后一页
         * hasPreviousPage/hasNextPage：是否存在上一页/下一页
         * navigatePages：导航分页的页码数
         * navigatepageNums：导航分页的页码，[1, 2, 3]
         */
        BookMapper bookMapper = sqlSession.getMapper(BookMapper.class);
        // 查询之前开启分页功能
        PageHelper.startPage(1, 5);
        // 查询数据列表
        List<Book> list = bookMapper.getBookByWhere("java", 399.0);
        // 将查询结果封装成PageInfo对象
        PageInfo<Book> pageInfo = new PageInfo<>(list);
        // 当前页数据
        System.out.println(pageInfo.getList());
        // 总页数
        System.out.println(pageInfo.getPages());
        // 总条数
        System.out.println(pageInfo.getTotal());
    }

    @Test
    public void testCache() throws IOException {
        /*
         * 一级缓存是SqlSession级别(默认开启),即相同SqlSession查询的数据会被缓存
         * 一级缓存失效情况
         * 1.相同SqlSession的两次查询之间手动清空缓存
         * 2.相同SqlSession的两次查询之间有增删改操作会自动清空缓存
         *
         * 二级缓存是SqlSessionFactory级别,即相同SqlSessionFactory查询的数据会被缓存
         * 二级缓存开启条件
         * 1.在核心配置文件添加配置<setting name="cacheEnabled" value="true"/>
         * 2.在映射文件添加标签<cache/>
         * 3.查询数据默认保存在一级缓存,只有在SqlSession提交或关闭后一级缓存的数据才会保存到二级缓存
         * 4.查询数据转换成的实体类要实现序列化接口
         * 二级缓存失效情况
         * 两次查询之间有增删改操作会自动清空缓存,一级缓存和二级缓存同时失效
         * 二级缓存之整合第三方EHCache
         * pom.xml添加mybatis-ehcache依赖,映射文件添加<cache type="org.mybatis.caches.ehcache.EhcacheCache"/>
         *
         * 查询顺序：二级缓存 -> 一级缓存 -> 数据库
         * 因为一个SqlSessionFactory可以包含多个SqlSession,所以二级缓存比一级缓存范围更大
         * 查询数据时会先查二级缓存,因为二级缓存可能包含别的SqlSession已经查出来的数据,没命中再去查一级缓存,还没命中就去查数据库
         */
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession01 = sqlSessionFactory.openSession(true);
        UserMapper mapper01 = sqlSession01.getMapper(UserMapper.class);
        System.out.println(mapper01.getUserById(1));
        // 手动清空或执行增删改操作(自动清空)会导致一级缓存失效
//        sqlSession01.clearCache();
//        mapper01.insertUser01(new User(null, "tod", "hum", 19, "女", "hum@qq.com"));
        System.out.println(mapper01.getUserById(1));

        // sqlSession提交或关闭后一级缓存的数据才会保存到二级缓存
//        sqlSession01.commit();
        sqlSession01.close();
        SqlSession sqlSession02 = sqlSessionFactory.openSession(true);
        UserMapper mapper02 = sqlSession02.getMapper(UserMapper.class);
        System.out.println(mapper02.getUserById(1));
        // 这里清理的是一级缓存,此时已经有二级缓存了所以仍然能命中缓存
        sqlSession02.clearCache();
        // 执行增删改操作(自动清空)会导致一级缓存和二级缓存同时失效,此时就会去查询数据库
//        mapper02.insertUser01(new User(null, "ted", "ud", 19, "男", "hum@qq.com"));
        System.out.println(mapper02.getUserById(1));
    }
}
