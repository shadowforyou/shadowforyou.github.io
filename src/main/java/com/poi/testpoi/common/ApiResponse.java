package com.poi.testpoi.common;

public class ApiResponse<T> {
    private T data;

    private boolean success;

    private String message;

    public ApiResponse(){
        this.data = null;
        this.success = false;
        this.message = null;
    }

    public ApiResponse(T data){
        this.success = true;
        this.data = data;
    }

    public ApiResponse(String message){
        this.message = message;
        this.success = false;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
