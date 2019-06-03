package com.pingan.pare.bi.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseData<T> {

    private int ret;

    private String msg;

    private T data;

    public static <T> ResponseData  ok(T data){
        ResponseData result=new ResponseData();
        result.setRet(0);
        result.setMsg("sucess");
        result.setData(data);
        return result;
    }

    public static <T> ResponseData  ok(String msg){
        ResponseData result=new ResponseData();
        result.setRet(0);
        result.setMsg("sucess");
        return result;
    }

    public static <T> ResponseData  ok(String msg,T data){
        ResponseData result=new ResponseData();
        result.setRet(0);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static <T> ResponseData  error(int code,String msg){
        ResponseData result=new ResponseData();
        result.setRet(code);
        result.setMsg(msg);
        result.setData(null);
        return result;
    }
}
