package com.larimar.util;

/**
 * @author Larimar
 * @time 2019/8/23 周五 10:03
 */
public class Msg {
    //状态码 200成功 400失败
    private int code;
    //提示信息
    private String msg;
    //返回给客户端的数据
    private Object date;

    public Msg() {
    }

    public Msg(int code, String msg, Object date) {
        this.code = code;
        this.msg = msg;
        this.date = date;
    }
    public static Msg success(){
        Msg result = new Msg();
        result.setCode(200);
        return result;
    }

    public static Msg fail(){
        Msg result = new Msg();
        result.setCode(400);
        return result;
    }
    public Msg add(String msg,Object o){
        this.setMsg(msg);
        this.setDate(o);
        return this;
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getDate() {
        return date;
    }

    public void setDate(Object date) {
        this.date = date;
    }
}
