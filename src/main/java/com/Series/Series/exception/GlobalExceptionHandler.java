package com.Series.Series.exception;

import com.Series.Series.payload.Errorinfo;
import org.antlr.v4.runtime.atn.ErrorInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<Errorinfo> resourcenotfoundexception(
                ResourceNotFoundException exception,
                WebRequest webRequest
    ){
        Errorinfo errorinfo = new Errorinfo(new Date(), exception.getMessage(), webRequest.getDescription(true));


        return new ResponseEntity<>(errorinfo,INTERNAL_SERVER_ERROR);
    }
}
