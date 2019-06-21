
package com.wl.app.web.rest.errors;

/**
 * 服务（业务）异常
 */
public class ServiceException extends RuntimeException {
	
    public ServiceException() {
    }
    private ResultCode resultCode;

    public ServiceException(ResultCode code,String message) {
        super(message);
        this.resultCode = code;
    }

	public ResultCode getResultCode() {
		return resultCode;
	}
    
}