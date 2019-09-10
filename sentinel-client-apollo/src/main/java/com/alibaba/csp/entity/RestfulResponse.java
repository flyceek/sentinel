package com.alibaba.csp.entity;

import java.io.Serializable;

public class RestfulResponse<T> implements Serializable{

    private Boolean success;

    private String code;


    private String message;
    
    private T content;

    private Object exception;

    public RestfulResponse() {
        this(Boolean.TRUE, "操作成功");
    }

    public RestfulResponse(Boolean success) {
        this(success, null);
    }

    public RestfulResponse(String message) {
        this(Boolean.TRUE, "操作成功");
    }

    public RestfulResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
        if (this.message == null) {
            if (Boolean.FALSE.equals(success)) {
                this.message = "操作失败";
            }
            if (Boolean.TRUE.equals(success)) {
                this.message = "操作成功";
            }

        }
    }

    public RestfulResponse(Boolean success,String code, String message) {
        this(success,message);
        this.code=code;
    }
    
    public RestfulResponse(Boolean success, String message, T content) {
        this.success = success;
        this.message = message;
        this.content = content;
        if (this.message == null) {
            if (Boolean.FALSE.equals(success)) {
                this.message = "操作失败";
            }
            if (Boolean.TRUE.equals(success)) {
                this.message = "操作成功";
            }

        }
    }


    public static RestfulResponse fail() {
        return fail(null);
    }

    public static RestfulResponse fail(String message) {
        return new RestfulResponse(Boolean.FALSE, message);
    }

    public static RestfulResponse fail(String code,String message) {
        return new RestfulResponse(Boolean.FALSE,code, message);
    }

    public static RestfulResponse success() {
        return success(null);
    }

    public static RestfulResponse success(String message) {
        return new RestfulResponse(Boolean.TRUE, message);
    }
    
    public static RestfulResponse successContent(Object content){
    	RestfulResponse res = RestfulResponse.success();
    	res.setContent(content);
    	return res;
    }
    
    public static RestfulResponse failException(Object exception){
    	RestfulResponse res = RestfulResponse.fail();
    	res.setException(exception);
    	return res;
    }


    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

	public Object getContent() {
		return content;
	}

	public void setContent(T content) {
		this.content = content;
	}

	public Object getException() {
		return exception;
	}

	public void setException(Object exception) {
		this.exception = exception;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
