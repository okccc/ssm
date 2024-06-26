package com.okccc;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.okccc.mapper.UserMapper;
import com.okccc.pojo.User;
import com.okccc.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: okccc
 * @Date: 2024/1/2 10:12:25
 * @Desc: 条件构造器组装查询条件替代sql语句,Lambda表达式使用实体类属性而不是字符串来表示列名,提高代码可读性和维护性
 */
//@SpringBootTest
// Could not detect default configuration classes for test class [com.okccc.MybatisPlusTest]
@SpringBootTest(classes = Main.class)  // 测试类也要放入主程序所在包,这样才能被扫描并加载到IOC容器
public class MybatisPlusTest {

    @Test
    public void testService() {
        System.out.println("=============== IService<User>通用CRUD ===============");
    }

    @Autowired
    private UserService userService;

    @Test
    public void testGetOrList() {
        // 根据id查询
        User user = userService.getById(1);
        System.out.println("user = " + user);

        // 查询所有
        List<User> users = userService.list();
        users.forEach(System.out::println);
    }

    @Test
    public void testSave() {
        // 批量保存
        ArrayList<User> users = new ArrayList<>();
        users.add(new User(null, "aaa", "111111", 19, null, "aaa@qq.com"));
        users.add(new User(null, "bbb", "222222", 29, null, "bbb@qq.com"));
        boolean b = userService.saveBatch(users);
        System.out.println("b = " + b);
    }

    @Test
    public void testSaveOrUpdate() {
        // id有值就修改,没值就插入
        User user1 = new User(10L, "aaa", "123456", 20, "男", "aaa@qq.com");
        User user2 = new User(null, "aaa", "123456", 20, "男", "aaa@qq.com");
        boolean b1 = userService.saveOrUpdate(user1);
        boolean b2 = userService.saveOrUpdate(user2);
        System.out.println("b1 = " + b1);
        System.out.println("b2 = " + b2);

        // 根据id修改
        User user3 = new User(9L, "bbb", "111111", 20, "女", "bbb@qq.com");
        boolean b3 = userService.updateById(user3);
        System.out.println("b = " + b3);
    }

    @Test
    public void testRemove() {
        boolean b = userService.removeById(29L);
        System.out.println("b = " + b);
    }

    @Test
    public void testMapper() {
        System.out.println("=============== BaseMapper<User>通用CRUD ===============");
    }

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        // 根据id查询
        User user = userMapper.selectById(1);
        System.out.println("user = " + user);

        // 根据id批量查询
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        System.out.println("users = " + users);

        // 条件查询,不写条件默认查询全部
        List<User> list = userMapper.selectList(null);
        list.forEach(System.out::println);
    }

    @Test
    public void testPage() {
        // 分页查询
        Page<User> page = new Page<>(1, 3);
        userMapper.selectPage(page, null);
        System.out.println(page.getTotal());  // 总条数
        System.out.println(page.getPages());  // 总页数
        System.out.println(page.getCurrent());  // 页码
        System.out.println(page.getSize());  // 页容量
        System.out.println(page.getRecords());  // 当前页数据
    }

    @Test
    public void testInsert() {
        // 插入数据 insert into t_user values(?, ?, ?, ?);
        User user = new User(null, "moon", null, 21, null, "ne@qq.com");
        int i = userMapper.insert(user);
        System.out.println("i = " + i);
    }

    @Test
    public void testUpdate() {
        // 根据id修改,主键必须有值 update t_user set age = 25 where id = ?;
        User user1 = new User();
        user1.setId(1L);
        // update操作不设置属性时就使用默认值null,表示不修改该字段
        user1.setPassword(null);
        // 所以age用的是包装类型Integer而不是基本类型int,因为int类型默认值是0,不设置的话会把所有age都改成0
        user1.setAge(25);
        int i1 = userMapper.updateById(user1);
        System.out.println("i1 = " + i1);

        // 尝试全表更新：不写条件就更新全部(危险!!!),会被BlockAttackInnerInterceptor拦截器阻止
        // Caused by: org.apache.ibatis.exceptions.PersistenceException: Error updating database.
        // Cause: com.baomidou.mybatisplus.core.exceptions.MybatisPlusException: Prohibition of table update operation
        User user2 = new User();
        user2.setAge(30);
        userMapper.update(user2, null);
    }

    @Test
    public void testLogicDelete() {
        // 根据id删除
        int i1 = userMapper.deleteById(1);
        System.out.println("i1 = " + i1);

        // 根据id批量删除
        int i2 = userMapper.deleteBatchIds(Arrays.asList(11, 12));
        System.out.println("i2 = " + i2);

        // 根据条件删除
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("age", 21);
        int i3 = userMapper.deleteByMap(hashMap);
        System.out.println("i3 = " + i3);

        // 尝试删库跑路：不写条件就删除全部(危险!!!),会被BlockAttackInnerInterceptor拦截器阻止
        // Caused by: org.apache.ibatis.exceptions.PersistenceException: Error updating database.
        // Cause: com.baomidou.mybatisplus.core.exceptions.MybatisPlusException: Prohibition of full table deletion
        userMapper.delete(null);
    }

    @Test
    public void testOptimisticLocker() {
        // 先查询两次,此时version都是1
        User user1 = userMapper.selectById(1L);
        User user2 = userMapper.selectById(1L);

        // 第一次修改,version + 1
        user1.setAge(19);
        userMapper.updateById(user1);

        // 第二次修改时version已经变了,乐观锁生效,修改失败
        user2.setAge(29);
        userMapper.updateById(user2);
    }

    @Test
    public void testWrapper() {
        System.out.println("=============== 条件构造器 ===============");
    }

    @Test
    public void testSelectByQueryWrapper() {
        // 创建QueryWrapper对象
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        // 拼接查询条件：SELECT * FROM t_user WHERE (username LIKE ? AND age BETWEEN ? AND ? AND email IS NOT NULL) ORDER BY id ASC
        queryWrapper.like("username", "gru");
        queryWrapper.like(StringUtils.isNotBlank("username"), "username", "gru");
        queryWrapper.between("age", 15, 30);
        queryWrapper.isNotNull("email");
        queryWrapper.orderByAsc("id");

        // 默认查询所有列,也可以指定查询列
        queryWrapper.select("id", "username", "email");

        // 执行BaseMapper的selectList操作
        List<User> list =  userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void testSelectByLambdaQueryWrapper() {
        // 创建LambdaQueryWrapper对象(推荐)
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();

        // 拼接查询条件：SELECT * FROM t_user WHERE (username LIKE ? AND age BETWEEN ? AND ? AND email IS NOT NULL) ORDER BY id ASC
        lambdaQueryWrapper.like(User::getUsername, "gru");
        lambdaQueryWrapper.between(User::getAge, 15, 30);
        lambdaQueryWrapper.isNotNull(User::getEmail);
        lambdaQueryWrapper.orderByAsc(User::getId);

        // 默认查询所有列,也可以指定查询列
        lambdaQueryWrapper.select(User::getId, User::getUsername, User::getEmail);

        // 执行BaseMapper的selectList操作
        List<User> list = userMapper.selectList(lambdaQueryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void testUpdateByQueryWrapper() {
        // 拼接查询条件：UPDATE t_user SET age=? WHERE (username LIKE ? AND age > ? OR email IS NULL)
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("username", "gru");
        queryWrapper.gt("age", 20);
        queryWrapper.or().isNull("email");  // 条件拼接默认使用and语句,or语句要显示声明

        // 使用QueryWrapper+实体类修改数据有个问题,无法将列值修改为null,于是引出UpdateWrapper
        User user = new User();
        user.setAge(29);
        user.setGender(null);

        // 执行BaseMapper的update操作
        int i = userMapper.update(user, queryWrapper);
        System.out.println("i = " + i);
    }

    @Test
    public void testUpdateByUpdateWrapper() {
        // 创建UpdateWrapper对象
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();

        // 拼接查询条件：UPDATE t_user SET age=?,gender=? WHERE (username LIKE ? AND age > ? OR email IS NULL)
        updateWrapper.like("username", "gru");
        updateWrapper.gt("age", 20);
        updateWrapper.or().isNull("email");

        // 给属性赋值
        updateWrapper.set("age", 29);
        updateWrapper.set("gender", null);

        // 执行BaseMapper的update操作
        int i = userMapper.update(null, updateWrapper);
        System.out.println("i = " + i);
    }

    @Test
    public void testUpdateByLambdaUpdateWrapper() {
        // 创建LambdaUpdateWrapper对象(推荐)
        LambdaUpdateWrapper<User> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();

        // 拼接查询条件：UPDATE t_user SET age=?,gender=? WHERE (username LIKE ? AND age > ? OR email IS NULL)
        lambdaUpdateWrapper.like(User::getUsername, "gru");
        lambdaUpdateWrapper.gt(User::getAge, 20);
        lambdaUpdateWrapper.or().isNull(User::getEmail );

        // 给属性赋值
        lambdaUpdateWrapper.set(User::getAge, 30);
        lambdaUpdateWrapper.set(User::getGender, null);

        // 执行BaseMapper的update操作
        int i = userMapper.update(null, lambdaUpdateWrapper);
        System.out.println("i = " + i);
    }

    @Test
    public void testDeleteByQueryWrapper() {
        // 拼接查询条件：DELETE FROM t_user WHERE (gender IS NULL)
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.isNull(User::getGender);

        // 执行BaseMapper的delete操作
        int i = userMapper.delete(lambdaQueryWrapper);
        System.out.println("i = " + i);
    }

}
