package top.mxzero.cloudstore.advice;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.mxzero.common.exception.APIException;
import top.mxzero.common.response.RestResponse;

import java.util.LinkedList;
import java.util.List;

/**
 * 全局异常处理
 *
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/16
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public RestResponse<?> handlerHttpMessageNotReadableException(MethodArgumentNotValidException exception) {
        List<ObjectError> allErrors = exception.getAllErrors();
        List<String> errors = new LinkedList<>();

        allErrors.forEach(error -> errors.add(error.getDefaultMessage()));

        return RestResponse.fail(errors.toString()).code(422);
    }

    @ExceptionHandler(APIException.class)
    public RestResponse<?> handlerAPIException(APIException exception) {
        return RestResponse.fail(exception.getMessage()).code(400);
    }

    @ExceptionHandler(Exception.class)
    public RestResponse<?> handlerException(Exception exception) {
        LOGGER.error("error:{}", exception.getMessage());
        exception.printStackTrace();
        return RestResponse.error(exception.getMessage());
    }

}

