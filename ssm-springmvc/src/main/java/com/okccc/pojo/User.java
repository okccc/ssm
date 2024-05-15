package com.okccc.pojo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author: okccc
 * @Date: 2023/11/7 16:52:18
 * @Desc:
 */
@Data
public class User {

    @NotBlank
    private String username;

    @Length(min = 6, max = 10)
    private String password;

    @Min(1)
    private int age;

    @Email
    private String email;

    // 实体类参数传递时报错 Field error in object 'user' on field 'birthday': rejected value [2011-01-01]
    // 分析：SpringMVC默认日期格式是yyyy/MM/dd
    // 解决：方案1.修改前端日期格式  方案2.给属性添加@DateTimeFormat注解
    @Past
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
}
