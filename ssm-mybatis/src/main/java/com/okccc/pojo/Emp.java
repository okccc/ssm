package com.okccc.pojo;

import lombok.Data;

/**
 * @Author: okccc
 * @Date: 2022/10/6 9:40 上午
 * @Desc:
 * CREATE TABLE `t_emp` (
 *   `emp_id` int(11) NOT NULL AUTO_INCREMENT,
 *   `emp_name` varchar(20) DEFAULT NULL,
 *   `dept_id` int(11) DEFAULT NULL,
 *   PRIMARY KEY (`emp_id`)
 * ) ENGINE=InnoDB DEFAULT CHARSET=utf8
 */
@Data
public class Emp {

    private Integer empId;
    private String empName;
    private Integer deptId;
    private Dept dept;  // "对一"关系定义成实体类属性
}
