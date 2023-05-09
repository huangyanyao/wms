package com.example.wms.common;

import lombok.Data;

@Data
public class Result {
    private int code;//編碼
    private String msg;//成功 失败
    private long total;//总记录数
    private Object data;//数据

    //失败
    public static Result fail(){
        return result(500,"失败",0L,null);
    }
    //成功
    public static Result suc(long total,Object data){
        return result(200,"成功",total,data);
    }

    private static Result result(int code,String msg,long total,Object data){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setTotal(total);
        result.setData(data);
        return result;
    }

}
