package com.woniu.car.commons.web.exception;

import com.woniu.car.commons.core.code.ResultEnum;
import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.commons.core.exception.CarException;
import org.apache.ibatis.javassist.NotFoundException;
import org.apache.rocketmq.remoting.exception.RemotingConnectException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.validation.UnexpectedTypeException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    /**
     * @Author Lints
     * @Date 2021/4/6/006 12:18
     * @Description 自定义异常类方法
     * @Param [e]
     * @Return com.woniu.car.commons.core.dto.ResultEntity<?>
     * @Since version-1.0
     */

    @ExceptionHandler(CarException.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR )
    public ResultEntity<?> handlerCommonsException(CarException e){
        //e.printStackTrace();
        System.out.println("自定义异常");
        return new ResultEntity<>(e.getMessage(),e.getCode());
    }


    /**
     * @Author Lints
     * @Date 2021/4/6/006 12:18
     * @Description 总异常类捕捉
     * @Param [e]
     * @Return com.woniu.car.commons.core.dto.ResultEntity<?>
     * @Since version-1.0
     */

    /*@ExceptionHandler(Exception.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR )
    public ResultEntity<?> MySystemException(Exception e){
       // e.printStackTrace();
        System.out.println("全局异常");
        return new ResultEntity<>(ResultEnum.RES_FAIL.getMessage(),ResultEnum.RES_FAIL.getCode());
    }*/

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR )
    public ResultEntity<?> maxUploadSizeExceededExceptionHandler(MaxUploadSizeExceededException e){
        System.out.println("文件过大");
        return new ResultEntity<>("文件过大",ResultEnum.RES_FAIL.getCode());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR )
    public ResultEntity<?> HttpMessageNotReadableExceptionExceptionHandler(HttpMessageNotReadableException e){
        System.out.println(e.getMessage());
        return new ResultEntity<>("请求参数格式不合法",ResultEnum.RES_FAIL.getCode());
    }

    /*@ExceptionHandler(NullPointerException.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR )
    public ResultEntity<?> NullPointerExceptionHandler(NullPointerException e){
        System.out.println("空指针异常"+e.getMessage());
        return new ResultEntity<>("空指针异常",ResultEnum.RES_FAIL.getCode());
    }*/

    @ExceptionHandler(RemotingConnectException.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR )
    public ResultEntity<?> RemotingConnectExceptionHandler(RemotingConnectException e){
        System.out.println("远程连接异常");
        return new ResultEntity<>("远程连接异常",ResultEnum.RES_FAIL.getCode());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR )
    public Map<String,String> MethodArgumentNotValidException(MethodArgumentNotValidException e){
        System.out.println("访问参数不合法");
        Map<String,String> maps=new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(fieldError -> {
            maps.put(fieldError.getField(),fieldError.getDefaultMessage());
        });
        return maps;
    }

    @ExceptionHandler(IndexOutOfBoundsException.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR )
    public ResultEntity<?> IndexOutOfBoundsExceptionHandler(IndexOutOfBoundsException e){
        System.out.println("索引越界异常");
        return new ResultEntity<>("索引越界异常",ResultEnum.RES_FAIL.getCode());
    }

    @ExceptionHandler(UnexpectedTypeException.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR )
    public ResultEntity<?> UnexpectedTypeExceptionHandler(UnexpectedTypeException e){
        System.out.println(e.getMessage());
        return new ResultEntity<>("参数不能为空",ResultEnum.RES_FAIL.getCode());
    }

}
