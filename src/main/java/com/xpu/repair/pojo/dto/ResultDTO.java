package com.xpu.repair.pojo.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ResultDTO {

    private Boolean success;
    private Integer code;
    private String message;
    private Map<String, Object> data = new HashMap<String, Object>();

    //把构造方法私有
    private ResultDTO() {}

    //成功静态方法
    public static ResultDTO ok() {
        ResultDTO r = new ResultDTO();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("成功");
        return r;
    }

    //失败静态方法
    public static ResultDTO error() {
        ResultDTO r = new ResultDTO();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR);
        r.setMessage("失败");
        return r;
    }

    public ResultDTO success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public ResultDTO message(String message){
        this.setMessage(message);
        return this;
    }

    public ResultDTO code(Integer code){
        this.setCode(code);
        return this;
    }

    public ResultDTO data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public ResultDTO data(Map<String, Object> map){
        this.setData(map);
        return this;
    }
}
