package ru.itis.logistic_service.services;

import ru.itis.logistic_service.dto.UserDto;
import ru.itis.logistic_service.forms.LoginForm;
import ru.itis.logistic_service.forms.UserEditForm;
import ru.itis.logistic_service.forms.UserSignUpForm;
import ru.itis.logistic_service.models.User;

import java.util.Optional;

public interface UsersService {

    void signUp(UserSignUpForm userSignUpForm);

    void editUser(UserEditForm userEditForm);

    void setCompany(Long userId, Long companyId);

    void setProfileImage(String imgPath, Long userId);

    void setPassword (String password, Long id);

    Long getCompanyID(Long userId);

    Optional<String> signIn(LoginForm loginForm);

    boolean isExistByCookie(String cookieValue);

    boolean isExistByLogin(String login);

    Optional<User> createUser(User model);

    User getUserByUUID(String uuid);

    UserDto showUserById(Long userId);

    UserDto showUserByUUID (String uuid);

    User getUserById(Long user_id);


}
