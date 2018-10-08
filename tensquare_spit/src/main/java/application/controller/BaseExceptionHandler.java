package application.controller;

import entity.Result;
import entity.StatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: tensquare_parent
 * @description: 统一异常处理类
 * @author: chenglong
 * @create:2018-09-25
 */
@ControllerAdvice
public class BaseExceptionHandler {

    /**
    * @Description: 处理异常的方法
    * @Param: [e]
    * @return: entity.Result
    */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();
        return new Result(false, StatusCode.ERROR,e.getMessage());
    }
}
