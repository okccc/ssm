package com.okccc;

import com.okccc.pojo.User;
import com.okccc.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: okccc
 * @Date: 2024/1/2 10:12:25
 * @Desc:
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

}
