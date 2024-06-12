package com.wipro.app.payload;

import org.springframework.http.HttpStatus;

public class ApiResponse {
    private String message;
    private boolean isSuccess;
    private Integer code;

    public ApiResponse(String msg, boolean isSuccess, Integer code) {
        this.message = msg;
        this.isSuccess = isSuccess;
        this.code = code;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}