package ru.itis.services;

import org.springframework.web.multipart.MultipartFile;
import ru.itis.dto.UserDto;
import ru.itis.forms.LoginForm;
import ru.itis.forms.UserEditForm;
import ru.itis.forms.UserSignUpForm;
import ru.itis.models.User;

import java.util.Optional;

public interface UsersService {

    void signUp(UserSignUpForm userSignUpForm);

    void editUser(UserEditForm userEditForm);

    void setCompany(Long userId, Long companyId);

    void setPassword(String password, Long id);

    void setProfileImage(String imgPath, Long userId);

    String uploadUserPhoto(MultipartFile multipartFile);

    Long getCompanyID(Long userId);

    Optional<String> signIn(LoginForm loginForm);

    boolean isExistByCookie(String cookieValue);

    boolean isExistByLogin(String login);

    Optional<User> createUser(User model);

    User getUserByUUID(String uuid);

    UserDto showUserById(Long userId);

    UserDto showUserByUUID(String uuid);

    User getUserById(Long user_id);


}
