package com.auth.auth.email;

import com.auth.auth.registration.exception.email.EmailSendFailedException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;



@Service
@AllArgsConstructor
public class EmailService implements EmailSender {
    private final static Logger LOGGER =
            LoggerFactory.getLogger(EmailService.class);
    private final JavaMailSender mailSender;
    @Override
    @Async
    public void send(String to, String email) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper =
                    new MimeMessageHelper(mimeMessage,"utf-8");
            mimeMessageHelper.setText(email,true);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject("Confirm your email");
            mimeMessageHelper.setFrom("hello@man.com");
            mailSender.send(mimeMessage);
        }catch (MessagingException e){
            LOGGER.error("Failed to send email",e);
            //Throw Custom Email Send Exception
            throw new EmailSendFailedException();
        }
    }
}
