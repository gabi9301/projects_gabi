package com.trip.hotel_gabriella.common.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trip.hotel_gabriella.common.exception.ErrorResponse;
import com.trip.hotel_gabriella.common.exception.customException.AccessTokenInvalidException;
import com.trip.hotel_gabriella.common.exception.customException.CustomException;
import com.trip.hotel_gabriella.common.exception.customException.ErrorCode;
import com.trip.hotel_gabriella.common.exception.customException.RefreshTokenInvalidException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class ExceptionHandlerFilter extends OncePerRequestFilter {

    private final ObjectMapper mapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //noinspection CatchMayIgnoreException
        try {
            filterChain.doFilter(request, response);

        }catch (RuntimeException e) {
            if(e instanceof CustomException){
                ErrorCode errorCode = ((CustomException)e).getErrorCode();
                ErrorResponse errResponse
                        = ErrorResponse.create()
                        .status(errorCode.getStatus())
                        .code(errorCode.getCode())
                        .message(e.toString());

                System.out.println("-------------ExceptionHandlerFilter working...----------");
                mapper.writeValue(response.getWriter(),errResponse);
            }
        }

    }
}
