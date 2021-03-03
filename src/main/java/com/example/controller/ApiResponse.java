package com.example.controller;

import lombok.Data;
import org.omg.CORBA.OBJ_ADAPTER;

/**
 * todo
 * author：xuyy
 * date：2021/3/1  4:17 下午
 */
@Data
public class ApiResponse {



    private Boolean success;
    private Object data;
    private Integer code;
    private String message;


    public ApiResponse(Boolean success, Object data, Integer code, String message) {
        this.success = success;
        this.data = data;
        this.code = code;
        this.message = message;
    }


}
