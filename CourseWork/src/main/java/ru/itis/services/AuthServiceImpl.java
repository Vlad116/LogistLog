package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.models.Auth;
import ru.itis.models.User;
import ru.itis.repository.AuthRepository;

@Component
public class AuthServiceImpl implements AuthService {

    AuthRepository authRepository;

    @Autowired
    public AuthServiceImpl(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }


    @Override
    public Auth createSession(User user) {
        return authRepository.createSession(user);
    }

}
