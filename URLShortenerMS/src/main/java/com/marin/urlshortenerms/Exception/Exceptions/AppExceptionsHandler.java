package com.marin.urlshortenerms.Exception.Exceptions;

import com.marin.urlshortenerms.ui.model.response.ErrorMessageResponseModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class AppExceptionsHandler {

    @ExceptionHandler(value = {URLShortenerServiceException.class})
    public ResponseEntity<Object> handleURLShortenerServiceException(URLShortenerServiceException ex, WebRequest request) {

        ErrorMessageResponseModel errorModel = new ErrorMessageResponseModel(LocalDateTime.now(), ex.getMessage());

        return new ResponseEntity<>(errorModel, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
