package com.okccc.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: okccc
 * @Date: 2023/10/18 16:48:13
 * @Desc:
 * CREATE TABLE `t_user_uuid` (
 *   `id` varchar(32) NOT NULL,  # 主键是UUID
 *   `username` varchar(20) DEFAULT NULL,
 *   `password` varchar(20) DEFAULT NULL,
 *   `age` int(11) DEFAULT NULL,
 *   `gender` char(1) DEFAULT NULL,
 *   `email` varchar(20) DEFAULT NULL,
 *   PRIMARY KEY (`id`)
 * ) ENGINE=InnoDB DEFAULT CHARSET=utf8 |
 */
@Data
@AllArgsConstructor
public class User02 implements Serializable {
    private String id;
    private String username;
    private String password;
    private Integer age;
    private String gender;
    private String email;
}
