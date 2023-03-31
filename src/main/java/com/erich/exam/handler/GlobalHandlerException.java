package com.erich.exam.handler;

import com.erich.exam.exception.NotFoundException;
import com.erich.exam.exception.ResourceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(NotFoundException.class)
    public ProblemDetail notFoundHandlerException(NotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle("Not_Found");
        problemDetail.setDetail(e.getMessage());
        problemDetail.setProperty("hora :", LocalDate.now());
        return problemDetail;
    }

    @ExceptionHandler(ResourceException.class)
    public ProblemDetail resourceHandlerException(ResourceException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Bad_Request");
        problemDetail.setDetail(e.getMessage());
        problemDetail.setProperty("hora :", LocalDate.now());
        return problemDetail;
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail methodHandlerException(MethodArgumentNotValidException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        Map<String,String> errors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(x -> {
            errors.put("El Campo" + " " + x.getField() + " " , x.getDefaultMessage());

        });
        problemDetail.setTitle("BAD_REQUEST");
        problemDetail.setDetail("");
        problemDetail.setProperty("hora :", LocalDate.now());
        problemDetail.setProperty("Invalid :", errors);
        return problemDetail;
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handlerException(Exception e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        problemDetail.setTitle("Internal_Server_Error");
        problemDetail.setDetail(e.getMessage());
        problemDetail.setProperty("hora :", LocalDate.now());
        return problemDetail;
    }


}
