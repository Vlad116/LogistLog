package ru.itis.logistic_service.services;

import ru.itis.logistic_service.models.Auth;
import ru.itis.logistic_service.models.User;

public interface AuthService {
    Auth createSession(User user);
}
