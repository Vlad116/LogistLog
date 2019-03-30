package ru.itis.logistic_service.repositories;

import ru.itis.logistic_service.models.Auth;
import ru.itis.logistic_service.models.User;

import java.util.Optional;

public interface AuthRepository extends CrudRepository<Auth> {

    Optional<Auth> findByCookieValue(String cookieValue);

    Auth createSession(User user);

}
