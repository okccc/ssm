package com.okccc.pojo;

/**
 * @Author: okccc
 * @Date: 2024/3/11 11:28:06
 * @Desc:
 *
 * CREATE TABLE `t_user` (
 *   `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
 *   `username` varchar(20) DEFAULT NULL COMMENT '用户名',
 *   `balance` double(10,2) unsigned DEFAULT NULL COMMENT '余额',
 * PRIMARY KEY (`user_id`)
 * ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 */
public class User {

    private Integer userId;

    private String username;

    private Double balance;

    public User() {
        System.out.println("生命周期1：实例化,IOC容器初始化时反射会调用类的无参构造创建对象");
    }

    public User(Integer userId, String username, Double balance) {
        this.userId = userId;
        this.username = username;
        this.balance = balance;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
        System.out.println("生命周期2：依赖注入之setter方法给属性赋值");
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public void init(){
        System.out.println("生命周期3：init初始化");
    }

    public void destroy(){
        System.out.println("生命周期4：destroy销毁");
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", balance=" + balance +
                '}';
    }
}
