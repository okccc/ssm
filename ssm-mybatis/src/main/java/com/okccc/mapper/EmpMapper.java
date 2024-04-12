package com.okccc.mapper;

import com.okccc.pojo.Dept;
import com.okccc.pojo.Emp;

/**
 * @Author: okccc
 * @Date: 2023/10/26 10:41:59
 * @Desc: 多表查询之"对一"
 */
public interface EmpMapper {

    /**
     * 根据empId查询员工信息及对应部门信息
     */
    Emp getEmpById(Integer empId);

    /**
     * 分步查询第一步：先查员工
     */
    Emp getEmpByIdStepOne(Integer empId);

    /**
     * 分步查询第二步：再查部门
     */
    Dept getEmpByIdStepTwo(Integer deptId);
}
