package com.okccc.pojo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @Author: okccc
 * @Date: 2023/11/7 16:52:18
 * @Desc:
 */
@Data
public class User {

    private Integer id;

    @NotBlank
    private String username;

    @Length(min = 3, max = 10)
    private String password;

    @Min(1)
    private int age;

    private String gender;

    @Email
    private String email;
}
