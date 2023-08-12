package com.auth.auth.appuser;

import com.auth.auth.registration.exception.email.EmailTakenException;
import com.auth.auth.registration.exception.user.UsernameTakenException;
import com.auth.auth.registration.token.ConfirmationToken;
import com.auth.auth.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {
    private final static String USER_NOT_FOUND_MSG = "User with username %s not found";
    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return appUserRepository.findByUsername(username).
                orElseThrow(()->new UsernameNotFoundException(
                        String.format(USER_NOT_FOUND_MSG,username)
                ));
    }

    public String singUpUser(AppUser appUser){
        boolean userWithSuchNameExists = appUserRepository.findByUsername(appUser.getUsername()).isPresent();
        boolean userWithSuchEmailExists = appUserRepository.findByEmail(appUser.getEmail()).isPresent();
        if (userWithSuchNameExists){
            //Throw Username Custom Exception
            throw new UsernameTakenException();

        }
        if (userWithSuchEmailExists){
            //Throw Email Custom Exception
            throw new EmailTakenException();

        }
        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodedPassword);
        appUserRepository.save(appUser);
        //TODO: Send confirmation token
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser
        );
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        //TODO: SEND EMAIL
        return token;
    }

    public int enableUser(String email){
        return appUserRepository.enableAppUser(email);
    }
}
