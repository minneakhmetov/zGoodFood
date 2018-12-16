package services;

import forms.LoginForm;
import forms.RegisterForm;
import models.Auth;
import models.CookieUser;
import models.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import repositories.AuthRepository;
import repositories.UsersRepository;

import java.util.UUID;

public class UserService {

    UsersRepository usersRepository;
    AuthRepository authRepository;
    PasswordEncoder passwordEncoder;

    public UserService(UsersRepository usersRepository, AuthRepository authRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.authRepository = authRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public CookieUser login(LoginForm form){
        User user = usersRepository.read(form.getEmail());
        if (user != null) {
            if (passwordEncoder.matches(form.getPassword(), user.getHashPassword())) {
                String cookieValue = UUID.randomUUID().toString();
                Auth auth = Auth.builder()
                        .userId(user.getId())
                        .cookieValue(cookieValue)
                        .build();
                authRepository.save(auth);
                return CookieUser.builder()
                        .user(user)
                        .cookie(cookieValue)
                        .build();
            }
        }
        return null;
    }

    public Auth register(RegisterForm form){
        User user = User.builder()
                            .name(form.getName())
                            .surname(form.getSurname())
                            .email(form.getEmail())
                            .hashPassword(passwordEncoder.encode(form.getPassword()))
                            .build();
        Long userId = usersRepository.save(user);
        String cookieValue = UUID.randomUUID().toString();
        Auth auth = Auth.builder()
                .userId(userId)
                .cookieValue(cookieValue)
                .build();
        authRepository.save(auth);
        return auth;
    }

    public User auth(String cookieValue) {
        Auth auth = authRepository.read(cookieValue);
        if (auth != null) {
            return usersRepository.read(auth.getUserId());
        }
        return null;
    }

    public void logout(String cookie){
        authRepository.delete(cookie);
    }

    public Auth getAuth(String cookieValue){
        return authRepository.read(cookieValue);
    }

    public User getUser(Long id){
        return usersRepository.read(id);
    }


    public boolean isExistByCookie(String cookieValue) {
        if (authRepository.read(cookieValue) != null) {
            return true;
        }
        return false;
    }

}