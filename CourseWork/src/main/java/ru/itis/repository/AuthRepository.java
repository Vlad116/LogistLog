package ru.itis.repository;

import ru.itis.models.Auth;
import ru.itis.models.User;

import java.util.Optional;

public interface AuthRepository extends CrudRepository<Auth> {

    Optional<Auth> findByCookieValue(String cookieValue);

    Auth createSession(User user);

}
