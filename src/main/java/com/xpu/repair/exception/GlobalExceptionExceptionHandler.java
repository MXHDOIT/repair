package com.xpu.repair.exception;

import com.xpu.repair.pojo.dto.ResultDTO;

import com.xpu.repair.util.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultDTO error(Exception e){
        log.error(ExceptionUtil.getMessage(e));
        return ResultDTO.error();
    }

    @ExceptionHandler(RepairException.class)
    @ResponseBody
    public ResultDTO error(RepairException e){
        log.error(ExceptionUtil.getMessage(e));
        return ResultDTO.error().message(e.getMsg()).code(e.getCode());
    }
}
