package ru.itis.logistic_service.services;

import ru.itis.logistic_service.models.Auth;
import ru.itis.logistic_service.models.User;
import ru.itis.logistic_service.repositories.AuthRepository;
import ru.itis.logistic_service.repositories.AuthRepositoryJdbcTemplateImpl;

import javax.sql.DataSource;

public class AuthServiceImpl implements AuthService {

    AuthRepository authRepository;

    public AuthServiceImpl(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }


    @Override
    public Auth createSession(User user) {
        return authRepository.createSession(user);
    }

}
