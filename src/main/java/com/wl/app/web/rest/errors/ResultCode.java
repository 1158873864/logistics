
package com.wl.app.web.rest.errors;

/**
 * 响应码枚举，参考HTTP状态码的语义
 */
public enum ResultCode {
	
	USERNAME_NULL(100001001),//
	
    SUCCESS(0),//成功
    FAIL(400),//失败
    UNAUTHORIZED(401),//未认证（签名错误）
    NOT_FOUND(404),//接口不存在
    INTERNAL_SERVER_ERROR(500);//服务器内部错误
 
    public int code;
    ResultCode(int code) {
        this.code = code;
    }
}