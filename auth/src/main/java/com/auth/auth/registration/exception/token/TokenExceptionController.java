package com.auth.auth.registration.exception.token;

import com.auth.auth.registration.exception.email.EmailTakenException;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TokenExceptionController {
    @ExceptionHandler(value = TokenExpiredException.class)
    public ResponseEntity<Object> tokenExpiredException(TokenExpiredException exception){
        return new ResponseEntity<>("Token expired!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
