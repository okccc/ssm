package com.okccc.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: okccc
 * @Date: 2024/1/2 10:50:21
 * @Desc:
 *
 * CREATE TABLE `t_user` (
 *   `id` bigint(20) PRIMARY KEY NOT NULL,
 *   `username` varchar(20) DEFAULT NULL,
 *   `password` varchar(20) DEFAULT NULL,
 *   `age` int(11) DEFAULT NULL,
 *   `gender` char(1) DEFAULT NULL,
 *   `email` varchar(20) DEFAULT NULL
 * ) ENGINE=InnoDB DEFAULT CHARSET=utf8
 *
 * CREATE TABLE `t_user1` (
 *   `id` bigint(20) PRIMARY KEY AUTO_INCREMENT NOT NULL,
 *   `username` varchar(20) DEFAULT NULL,
 *   `password` varchar(20) DEFAULT NULL,
 *   `age` int(11) DEFAULT NULL,
 *   `gender` char(1) DEFAULT NULL,
 *   `email` varchar(20) DEFAULT NULL,
 *   `deleted` int(11) DEFAULT 0,
 *   `version` int(11) DEFAULT 1
 * ) ENGINE=InnoDB DEFAULT CHARSET=utf8
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long id;

    private String username;

    private String password;

    private Integer age;

    private String gender;

    private String email;

}
