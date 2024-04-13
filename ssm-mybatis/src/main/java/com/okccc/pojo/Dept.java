package com.okccc.pojo;

import lombok.Data;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2022/10/6 9:40 上午
 * @Desc:
 * CREATE TABLE `t_dept` (
 *   `dept_id` int(11) NOT NULL AUTO_INCREMENT,
 *   `dept_name` varchar(20) DEFAULT NULL,
 *   PRIMARY KEY (`dept_id`)
 * ) ENGINE=InnoDB DEFAULT CHARSET=utf8
 */
@Data
public class Dept {

    private Integer deptId;
    private String deptName;
    private List<Emp> empList;  // "对多"关系定义成集合属性
}
