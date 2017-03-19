package com.My.Reptile.controller.result;

/**
 * Created by Administrator on 2017/3/17.
 */
public class JsonResult {
    private String code;
    private String message;
    private Object data;

    public JsonResult success() {
        this.code = ResultCode.SUCCESS.getCode();
        this.message = ResultCode.SUCCESS.getDesc();
        return this;
    }

    public JsonResult success(Object data) {
        this.code = ResultCode.SUCCESS.getCode();
        this.message = ResultCode.SUCCESS.getDesc();
        this.data = data;
        return this;
    }

    public JsonResult failure() {
        this.code = ResultCode.ERROR.getCode();
        this.message = ResultCode.ERROR.getDesc();
        return this;
    }

    public JsonResult failure(String message) {
        this.code = ResultCode.ERROR.getCode();
        this.message = message;
        return this;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }
}
