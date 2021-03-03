package com.example.exception;

import lombok.Data;

/**
 * todo
 * author：xuyy
 * date：2021/3/1  4:16 下午
 *
 */
@Data
public class BusinessException extends Exception {

    private Integer code;
}
