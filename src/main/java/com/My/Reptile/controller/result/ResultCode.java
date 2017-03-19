package com.My.Reptile.controller.result;

/**
 * Created by Administrator on 2017/3/17.
 */
public enum ResultCode {

    SUCCESS("200", "success"), // 正确请求
    ERROR("500", "failure"), // 请求错误
    INVALID_REQUEST("40012", "invalid request"), // 非法请求
    INVALID_APPID("40013", "invalid appid"); // AppID无效错误

    /** 主键 */
    private final String code;

    /** 描述 */
    private final String desc;

    ResultCode(final String code, final String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return this.code;
    }

    public String getDesc() {
        return this.desc;
    }
}