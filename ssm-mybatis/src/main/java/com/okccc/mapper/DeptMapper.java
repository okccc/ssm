package com.okccc.mapper;

import com.okccc.pojo.Dept;
import com.okccc.pojo.Emp;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2023/10/26 10:42:07
 * @Desc: 多表查询之"对多"
 */
public interface DeptMapper {

    /**
     * 根据deptId查询部门信息及对应员工信息
     */
    Dept getDeptById(Integer deptId);

    /**
     * 分步查询部门以及对应员工的第一步
     */
    Dept getDeptByIdStepOne(Integer deptId);

    /**
     * 分步查询部门以及对应员工的第二步
     */
    List<Emp> getDeptByIdStepTwo(Integer deptId);
}
