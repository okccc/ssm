package com.okccc.pojo;

import java.util.Arrays;
import java.util.Map;

/**
 * @Author: okccc
 * @Date: 2023/10/2 17:07:25
 * @Desc:
 */
public class Emp {

    private Integer empId;

    private String empName;

    private Integer age;

    private String sex;

    private Dept dept;

    private String[] hobbies;

    private Map<String, Game> games;

    public Emp() {
    }

    public Emp(Integer empId, String empName, Integer age, String sex) {
        this.empId = empId;
        this.empName = empName;
        this.age = age;
        this.sex = sex;
    }

    public Emp(Integer empId, String empName, Integer age, String sex, Dept dept, String[] hobbies, Map<String, Game> games) {
        this.empId = empId;
        this.empName = empName;
        this.age = age;
        this.sex = sex;
        this.dept = dept;
        this.hobbies = hobbies;
        this.games = games;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public String[] getHobbies() {
        return hobbies;
    }

    public void setHobbies(String[] hobbies) {
        this.hobbies = hobbies;
    }

    public Map<String, Game> getGames() {
        return games;
    }

    public void setGames(Map<String, Game> games) {
        this.games = games;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "empId=" + empId +
                ", empName='" + empName + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", dept=" + dept +
                ", hobbies=" + Arrays.toString(hobbies) +
                ", games=" + games +
                '}';
    }
}
