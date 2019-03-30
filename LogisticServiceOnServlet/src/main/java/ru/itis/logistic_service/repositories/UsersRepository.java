package ru.itis.logistic_service.repositories;

import ru.itis.logistic_service.models.User;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends CrudRepository<User> {

    Optional<User> findOneByPhoneNumber(String phoneNumber);

    Optional<User> findOneByEmail(String email);

    Optional<User> findOneByLogin(String login);

    User getUserByCookieValue(String uuid);

    Optional<User> createUser(User model);

    User getUserById(Long user_id);

    void setCompanyID(Long userId, Long companyID);

    void setProfileImg (String imgPath, Long userId);

    void setPassword (String password, Long userId);

    Long getCompanyID (Long userId);
}
