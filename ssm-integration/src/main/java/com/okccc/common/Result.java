package com.okccc.common;

import lombok.Data;

/**
 * @Author: okccc
 * @Date: 2023/12/13 10:28:17
 * @Desc: 与前端交互的统一数据格式
 *
 * {"code": 200, "message": "success", "data": Object}
 */
@Data
public class Result {

    // 返回状态码
    private Integer code;

    // 返回状态码对应的消息
    private String message;

    // 返回数据
    private Object data;

    public static Result build(ResultCodeEnum resultEnum) {
        Result result = new Result();
        result.setCode(resultEnum.getCode());
        result.setMessage(resultEnum.getMessage());
        return result;
    }

    public static Result build(Object data, ResultCodeEnum resultCodeEnum) {
        Result result = new Result();
        result.setData(data);
        result.setCode(resultCodeEnum.getCode());
        result.setMessage(resultCodeEnum.getMessage());
        return result;
    }

}
