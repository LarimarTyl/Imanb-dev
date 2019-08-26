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
    private Object data;

    public Msg() {
    }

    public Msg(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
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
        this.setdata(o);
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

    public Object getdata() {
        return data;
    }

    public void setdata(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
