package com.okccc.factory;

import com.okccc.pojo.User;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * @Author: okccc
 * @Date: 2023/10/3 19:25:14
 * @Desc:
 */
@Component
public class UserFactoryBean implements FactoryBean<User> {
    @Override
    public User getObject() {
        return new User();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
