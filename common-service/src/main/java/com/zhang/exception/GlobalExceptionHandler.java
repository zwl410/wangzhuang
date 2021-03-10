package com.zhang.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理所有不可知异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> baseException(Exception e) {
        log.error(e.getMessage());
        ErrorResult result= ErrorResult.error(e.getMessage());
        return new ResponseEntity(result,HttpStatus.valueOf(result.getStatus()));
    }

    /**
     * 运行时异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> baseRuntimeException(RuntimeException e) {
        log.error(e.getMessage());
        ErrorResult result= ErrorResult.error(e.getMessage());
        return new ResponseEntity(result,HttpStatus.valueOf(result.getStatus()));
    }

    /**
     * 请求参数异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ServletRequestBindingException.class)
    public ResponseEntity<Object> baseRequestBindException(ServletRequestBindingException e) {
        String errorMsg = e.getLocalizedMessage();
        if (e instanceof MissingServletRequestParameterException) {
            errorMsg = "parameter参数缺失异常,请检查参数名称大小写，有无空格等：" + errorMsg;
        } else if (e instanceof MissingPathVariableException) {
            errorMsg = "path参数缺失异常：" + errorMsg;
        }
        log.error(errorMsg);
        ErrorResult result= ErrorResult.error(e.getMessage());
        return new ResponseEntity(result,HttpStatus.valueOf(result.getStatus()));
    }

    /**
     * 自定义异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<Object> baseBaseException(BaseException e) {
        log.error(e.getMessage());
        ErrorResult result= ErrorResult.error(e.getMessage());
        return new ResponseEntity(result,HttpStatus.valueOf(result.getStatus()));
    }
}
