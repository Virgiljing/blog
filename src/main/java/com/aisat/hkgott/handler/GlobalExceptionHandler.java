package com.aisat.hkgott.handler;

import com.aisat.hkgott.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * GlobalExceptionHandler
 *
 * @author virgilin
 * @date 2018/12/4
 */
@ControllerAdvice
@RestController
@Slf4j
public class GlobalExceptionHandler implements ErrorController {


    @ExceptionHandler(value = Exception.class)
    @RequestMapping("/error")
    public Result<String> defaultErrorHandler(HttpServletRequest req, Exception ex) throws Exception {
        log.info(ex.getMessage());
        String result = "";
        if (ex instanceof BindException) {
            BindException bindException = (BindException) ex;
            List<ObjectError> allErrors = bindException.getAllErrors();
            ObjectError objectError = allErrors.get(0);
            String defaultMessage = objectError.getDefaultMessage();
            result = defaultMessage;
        } else if (ex instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException methodArgumentNotValidException = (MethodArgumentNotValidException) ex;
            for (FieldError fieldErro : methodArgumentNotValidException.getBindingResult().getFieldErrors()) {
                result += "【" + fieldErro.getField() + ":" + fieldErro.getDefaultMessage() + "】";
            }
        } else if (ex instanceof HttpRequestMethodNotSupportedException) {
            HttpRequestMethodNotSupportedException httpRequestMethodNotSupportedException = (HttpRequestMethodNotSupportedException) ex;
            result = httpRequestMethodNotSupportedException.getMessage();
        } else if (ex instanceof NoHandlerFoundException) {
            result = "无效的请求地址";
        } else if (ex instanceof MissingServletRequestParameterException) {
            MissingServletRequestParameterException missingServletRequestParameterException = (MissingServletRequestParameterException) ex;
            result = "缺少必要的参数[" + missingServletRequestParameterException.getMessage() + "]";
        } else {
            result = "Error handling OR Service unavaliable at client01";
        }
        Result<String> objectResult = new Result<>();
        objectResult.setMessages(result);
        return objectResult;
    }

    public String getErrorPath() {
        return "/error";
    }
}
