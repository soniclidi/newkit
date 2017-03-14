package cc.codehub.newkit.common;

import cc.codehub.newkit.base.Error;


public enum ApiError implements Error {

    NOT_AUTHORIZED ("401", "未授权的操作或访问"),
    ACCESS_DENIED ("403", "禁止访问"),
    SESSION_TIMEOUT ("600", "登陆超时"),
    ;


    private String code;
    private String message;

    ApiError(String code, String message) {
        this.code = code;
        this.message = message;
    }

    void setCode(String code) {
        this.code = code;
    }

    void setMessage(String message) {
        this.message = message;
    }

    public String getCode()
    {
        return this.code;
    }

    public String getMessage()
    {
        return this.message;
    }
}
