package ru.itis.services;

import ru.itis.models.Auth;
import ru.itis.models.User;

public interface AuthService {
    Auth createSession(User user);
}
