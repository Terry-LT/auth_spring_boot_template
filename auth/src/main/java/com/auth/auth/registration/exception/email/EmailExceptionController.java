package com.auth.auth.registration.exception.email;

import com.auth.auth.registration.exception.user.UsernameTakenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//https://www.tutorialspoint.com/spring_boot/spring_boot_exception_handling.htm
@ControllerAdvice
public class EmailExceptionController {
    @ExceptionHandler(value = EmailTakenException.class)
    public ResponseEntity<Object> emailTakeException(EmailTakenException exception) {
        return new ResponseEntity<>("User with such email already exists!", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(value = EmailNotValidException.class)
    public ResponseEntity<Object> emailNotValidException(EmailNotValidException exception) {
        return new ResponseEntity<>("Email Not Valid", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(value = EmailConfirmedException.class)
    public ResponseEntity<Object> emailConfirmed(EmailConfirmedException exception) {
        return new ResponseEntity<>("Email already confirmed", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(value = EmailSendFailedException.class)
    public ResponseEntity<Object> emailSendFailedException(EmailSendFailedException exception) {
        return new ResponseEntity<>("Failed to send email", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
