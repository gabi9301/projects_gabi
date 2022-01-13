package com.trip.hotel_gabriella.common.exception.customHandler;

import com.trip.hotel_gabriella.common.exception.ErrorResponse;
import com.trip.hotel_gabriella.common.exception.customException.AccessTokenInvalidException;
import com.trip.hotel_gabriella.common.exception.customException.CustomException;
import com.trip.hotel_gabriella.common.exception.customException.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class customExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(customExceptionHandler.class);

    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ErrorResponse> handleCustomException(CustomException e){
        logger.error("handle customException", e);
        ErrorCode errorCode = e.getErrorCode();
        ErrorResponse response
                = ErrorResponse.create()
                .status(errorCode.getStatus())
                .code(errorCode.getCode())
                .message(e.toString());
        HttpStatus httpStatus = Objects.requireNonNull(HttpStatus.resolve(errorCode.getStatus()));

        return new ResponseEntity<>(response, httpStatus);

    }

}
