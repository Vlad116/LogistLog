package ru.itis.logistic_service.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.logistic_service.dto.UserDto;
import ru.itis.logistic_service.forms.LoginForm;
import ru.itis.logistic_service.forms.UserEditForm;
import ru.itis.logistic_service.forms.UserSignUpForm;
import ru.itis.logistic_service.models.Auth;
import ru.itis.logistic_service.models.User;
import ru.itis.logistic_service.repositories.AuthRepository;
import ru.itis.logistic_service.repositories.UsersRepository;

import java.util.Optional;
import java.util.UUID;

public class UsersServiceImpl implements UsersService {

    private UsersRepository usersRepository;
    private AuthRepository authRepository;

    private PasswordEncoder passwordEncoder;

    public UsersServiceImpl(UsersRepository usersRepository, AuthRepository authRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.authRepository = authRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void signUp(UserSignUpForm userSignUpForm) {

        User user = User.builder()
                .email(userSignUpForm.getEmail())
                .login(userSignUpForm.getLogin())
                .hashPassword(passwordEncoder.encode(userSignUpForm.getPassword()))
                .firstName(userSignUpForm.getFirstName())
                .lastName(userSignUpForm.getLastName())
                .address(userSignUpForm.getAddress())
                .phoneNumber(userSignUpForm.getPhoneNumber())
                .build();

        usersRepository.save(user);
    }

    @Override
    public void editUser(UserEditForm userEditForm) {

        User user = User.builder()
                .id(userEditForm.getId())
                .email(userEditForm.getEmail())
                .login(userEditForm.getLogin())
                .firstName(userEditForm.getFirstName())
                .lastName(userEditForm.getLastName())
                .address(userEditForm.getAddress())
                .phoneNumber(userEditForm.getPhoneNumber())
                .build();
        usersRepository.update(user);
    }

    @Override
    public void setPassword(String password, Long id) {
        String hashPassowrd = passwordEncoder.encode(password);
        usersRepository.setPassword(hashPassowrd, id);
    }

    @Override
    public void setCompany(Long userId, Long companyId) {
        usersRepository.setCompanyID(userId, companyId);
    }

    @Override
    public Long getCompanyID(Long userId) {
        return usersRepository.getCompanyID(userId);
    }

    @Override
    public Optional<String> signIn(LoginForm loginForm) {

        Optional<User> userOptional = usersRepository.findOneByEmail(loginForm.getLogin());

        if (userOptional.isPresent()) {

            User user = userOptional.get();

            if (passwordEncoder.matches(loginForm.getPassword(), user.getHashPassword())) {
                String cookieValue = UUID.randomUUID().toString();

                Auth auth = Auth.builder()
                        .userId(user.getId())
                        .cookieValue(cookieValue)
                        .build();

                authRepository.save(auth);
                return Optional.of(cookieValue);

            } else {
                throw new IllegalArgumentException("Wrong password!");
            }

        } else if (usersRepository.findOneByLogin(loginForm.getLogin()).isPresent()) {
            userOptional = usersRepository.findOneByLogin(loginForm.getLogin());

            User user = userOptional.get();

            if (passwordEncoder.matches(loginForm.getPassword(), user.getHashPassword())) {
                String cookieValue = UUID.randomUUID().toString();

                Auth auth = Auth.builder()
                        .userId(user.getId())
                        .cookieValue(cookieValue)
                        .build();

                authRepository.save(auth);
                return Optional.of(cookieValue);

            } else {
                throw new IllegalArgumentException("Wrong password!");
            }
        } else {
            throw new IllegalArgumentException("Wrong email or login!");
        }
    }

    @Override
    public boolean isExistByCookie(String cookieValue) {
        if (authRepository.findByCookieValue(cookieValue).isPresent()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isExistByLogin(String login) {
        return usersRepository.findOneByLogin(login).isPresent();
    }

    @Override
    public User getUserByUUID(String uuid) {
        return usersRepository.getUserByCookieValue(uuid);
    }

    @Override
    public UserDto showUserByUUID(String uuid) {
        return UserDto.from(usersRepository.getUserByCookieValue(uuid));
    }

    @Override
    public User getUserById(Long user_id) {
        return usersRepository.getUserById(user_id);
    }

    @Override
    public UserDto showUserById(Long userId) {
        return UserDto.from(getUserById(userId));
    }

    @Override
    public void setProfileImage(String imgPath, Long userId) {
        usersRepository.setProfileImg(imgPath, userId);
    }

    @Override
    public Optional<User> createUser(User model) {
        return usersRepository.createUser(model);
    }

}
