package per.yjc.hichat_back.exception;

import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import per.yjc.hichat_back.utils.Result;

import java.io.IOException;

@RestControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(RRException.class)
    public Result RRException(Exception e) {
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result MissingServletRequestParameterException(Exception e) {
        return Result.error(e.getMessage());

    }
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result HttpRequestMethodNotSupportedException(Exception e) {
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Result MethodArgumentTypeMismatchException(Exception e) {
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(IOException.class)
    public Result IOException(Exception e) {
        return Result.error(e.getMessage());
    }

}