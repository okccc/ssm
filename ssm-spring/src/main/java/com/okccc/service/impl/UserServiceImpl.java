package com.okccc.service.impl;

import com.okccc.dao.UserDao;
import com.okccc.pojo.User;
import com.okccc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2023/10/4 10:14:45
 * @Desc: 事务属性之只读、超时、回滚策略、隔离级别、传播行为
 *
 * 事务处理
 * 事务就是对表的更新操作,使数据从一种状态变换到另一种状态
 * 一个事务中的所有操作要么全部失败回滚(rollback),要么全部成功提交(commit)并且一旦提交就无法回滚
 * 什么时候会提交数据？
 * 1.执行DML操作,默认情况下一旦执行完就自动提交数据 -> conn.setAutoCommit(false);
 * 2.一旦断开数据库连接,也会提交数据 -> 将获取conn步骤从update方法中剥离出来单独关闭
 *
 * 模拟场景：用户购买图书,先查询图书价格,再更新图书库存和用户余额
 * unsigned属性会将数字类型的字段无符号化即不能为负数,比如tinyint范围(-128,127)无符号后范围(0,255)
 * user_id=1的用户购买book_id=1的图书,余额50但图书80购买之后余额-30,但是balance字段设置了unsigned所以-30写不进去
 * 此时执行sql会抛异常Data truncation: Out of range value for column 'balance' at row 1
 * 添加事务前：图书库存更新但用户余额没更新,这显然是错的,买书是一个完整功能,更新库存和更新余额要么都成功要么都失败
 * 添加事务后：执行sql还是抛那个异常,但是查看数据库表发现图书库存和用户余额都没更新,这才是合理的
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> queryAll() {
        return userDao.queryAll();
    }

    @Override
    @Transactional(
            // @Transactional注解通常是加在类上使所有方法都有事务,在方法上重新定义@Transactional会将其覆盖
            // 只读：明确告诉数据库当前操作不涉及写操作,这样数据库就能针对查询操作进行优化
            // DML操作设置只读模式会抛异常 Connection is read-only. Queries leading to data modification are not allowed
            readOnly = true
    )
    public void buyBook(int userId, int bookId) {
        // 查询图书价格
        Double price = userDao.getPriceByBookId(bookId);

        // 更新图书库存
        userDao.updateStock(bookId);

        // 更新用户余额
        userDao.updateBalance(userId, price);
    }
}

// 基于xml的自动装配(了解)
//public class UserServiceImpl implements UserService {
//
//    private UserDao userDao;
//
//    public void setUserDao(UserDao userDao) {
//        this.userDao = userDao;
//    }
//
//    @Override
//    public List<User> queryAll() {
//        return userDao.queryAll();
//    }
//}