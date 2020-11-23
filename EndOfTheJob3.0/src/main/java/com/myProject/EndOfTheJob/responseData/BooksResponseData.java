package com.myProject.EndOfTheJob.responseData;

/**
 * 前端请求响应的封装类
 *
 */
public class BooksResponseData<T> {
    private T payload;        //服务器响应数据
    private boolean success; //请求是否成功
    private String msg;       // 错误信息
    private int code = -1;   // 状态码
    private long timestamp; //服务器响应时间

    public BooksResponseData() {
        this.timestamp = System.currentTimeMillis() / 1000;
    }

    public BooksResponseData(boolean success) {
        this.timestamp = System.currentTimeMillis() / 1000;
        this.success = success;
    }

    public BooksResponseData(boolean success, T payload) {
        this.timestamp = System.currentTimeMillis() / 1000;
        this.success = success;
        this.payload = payload;
    }

    public BooksResponseData(boolean success, T payload, int code) {
        this.timestamp = System.currentTimeMillis() / 1000;
        this.success = success;
        this.payload = payload;
        this.code = code;
    }

    public BooksResponseData(boolean success, String msg) {
        this.timestamp = System.currentTimeMillis() / 1000;
        this.success = success;
        this.msg = msg;
    }

    public BooksResponseData(boolean success, String msg, int code) {
        this.timestamp = System.currentTimeMillis() / 1000;
        this.success = success;
        this.msg = msg;
        this.code = code;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public static BooksResponseData ok() {
        return new BooksResponseData(true);
    }

    public static <T> BooksResponseData ok(T payload) {
        return new BooksResponseData(true, payload);
    }

    public static <T> BooksResponseData ok(int code) {
        return new BooksResponseData(true, null, code);
    }

    public static <T> BooksResponseData ok(T payload, int code) {
        return new BooksResponseData(true, payload, code);
    }

    public static BooksResponseData fail() {
        return new BooksResponseData(false);
    }

    public static BooksResponseData fail(String msg) {
        return new BooksResponseData(false, msg);
    }

    public static BooksResponseData fail(int code) {
        return new BooksResponseData(false, null, code);
    }

    public static BooksResponseData fail(int code, String msg) {
        return new BooksResponseData(false, msg, code);
    }

}
