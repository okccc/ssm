package com.okccc.common;

import lombok.Getter;

/**
 * @Author: okccc
 * @Date: 2023/12/13 10:31:29
 * @Desc: 状态码和业务含义对应关系的枚举
 */
@Getter
public enum ResultCodeEnum {

    SUCCESS(200,"操作成功"),

    FAIL(500, "操作失败"),

    USERNAME_ERROR(501,"用户名错误"),

    PASSWORD_ERROR(503,"密码错误"),

    NOT_LOGIN(504,"未登录"),

    USERNAME_USED(505,"用户名已使用");

    private final Integer code;

    private final String message;

    ResultCodeEnum(Integer code, String message){
        this.code= code;
        this.message = message;
    }

}
