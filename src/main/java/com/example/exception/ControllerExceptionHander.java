package com.example.exception;

import com.example.controller.ApiResponse;
import com.example.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * todo
 * author：xuyy
 * date：2021/3/1  4:11 下午
 */
@RestControllerAdvice
@Slf4j
public class ControllerExceptionHander {


    /**
     * 统一处理异常
     * @param request
     * @param handlerMethod
     * @param ex
     * @return
     */
    @ExceptionHandler
    public ApiResponse handle(HttpServletRequest request, HandlerMethod handlerMethod, Exception ex) {
        if (ex instanceof BusinessException) {
            log.warn(String.format("访问 %s -> %s 出现异常", request.getRequestURI(), ex));
            return new ApiResponse(false,null, ((BusinessException) ex).getCode(),ex.getMessage());
        } else {
            log.error(String.format("访问 %s -> %s 出现异常", request.getRequestURI(), ex));
            return new ApiResponse(false,null, 1000,ex.getMessage());
        }
    }
}
