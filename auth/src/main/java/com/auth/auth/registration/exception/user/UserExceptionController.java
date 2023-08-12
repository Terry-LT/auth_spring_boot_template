package com.auth.auth.registration.exception.user;

import com.auth.auth.registration.exception.email.EmailTakenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionController {
    @ExceptionHandler(value = UsernameTakenException.class)
    public ResponseEntity<Object> exception(UsernameTakenException exception) {
        return new ResponseEntity<>("User with such username already exists!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
