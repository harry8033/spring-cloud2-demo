package com.pf.core.entity;

/**
 * Author: Ru He
 * Date: Created in 2019/1/10.
 * Description:
 */
public class Result<T> {

    public final static int SUCCESS = 200;

    public final static int SYS_ERROR = 999;

    public final static int NOT_SUPPORTED = 405;

    public final static int NOT_FOUND = 404;

    private int code;

    private String msg;

    private T data;

    public Result(){
        this.code = SUCCESS;
        this.msg = "success";
    }

    public Result(T data){
        this.code = SUCCESS;
        this.msg = "success";
        this.data = data;
    }

    public Result(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public Result setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public Result setData(T data) {
        this.data = data;
        return this;
    }

    public static Result asNotSupported(String msg){
        return new Result(Result.NOT_SUPPORTED, msg);
    }

    public static Result asSuccess(){
        return new Result();
    }

    public static Result asSysErr(){
        return new Result().setCode(Result.SYS_ERROR);
    }

    public static Result asNotFound(){
        return new Result().setCode(Result.NOT_FOUND);
    }
}
