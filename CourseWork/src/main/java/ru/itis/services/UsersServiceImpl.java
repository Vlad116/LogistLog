package ru.itis.services;

import lombok.SneakyThrows;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.dto.UserDto;
import ru.itis.forms.LoginForm;
import ru.itis.forms.UserEditForm;
import ru.itis.forms.UserSignUpForm;
import ru.itis.models.Auth;
import ru.itis.models.User;
import ru.itis.repository.AuthRepository;
import ru.itis.repository.UsersRepository;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Optional;
import java.util.UUID;

//@Lazy
@Component
public class UsersServiceImpl implements UsersService {

//    @Lazy
    @Autowired
    private Environment environment;

    private UsersRepository usersRepository;
    private AuthRepository authRepository;

    private PasswordEncoder passwordEncoder;

    @Lazy
    @Autowired
    public UsersServiceImpl(UsersRepository usersRepository, AuthRepository authRepository,
                            PasswordEncoder passwordEncoder
    ) {
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
    public void setProfileImage(String imgPath, Long userId) {
        usersRepository.setProfileImg(imgPath, userId);
    }

    @Override
    @SneakyThrows
    public String uploadUserPhoto(MultipartFile multipartFile) {
        return uploadPhotoCrop(environment.getProperty("static-files.userPhotos.prefix"), multipartFile);
    }

    @SneakyThrows
    private String uploadPhotoCrop(String dir, MultipartFile multipartFile) {
        HttpPost post = new HttpPost(environment.getProperty("static-files.host") + ":" + environment.getProperty("nodejs.port") + "/uploadPhoto");
        post.addHeader("dir", dir);
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        byte[] bytes = multipartFile.getBytes();
        File file = new File(multipartFile.getName());
        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file));
        stream.write(bytes);
        stream.close();
        BufferedImage source = ImageIO.read(file);
        BufferedImage image200;
        if (source.getHeight() > source.getWidth())
            image200 = Scalr.resize(source, Scalr.Mode.FIT_TO_WIDTH, 200);
        else
            image200 = Scalr.resize(source, Scalr.Mode.FIT_TO_HEIGHT, 200);
        if (source.getHeight() != source.getWidth())
            image200 = Scalr.crop(image200, 200, 200);

        File image = createImage(image200);

        builder.addPart("files", new FileBody(image, ContentType.DEFAULT_BINARY));
        post.setEntity(builder.build());
        new DefaultHttpClient().execute(post);
        return "/" + dir + "/" + image.getName();
    }

    @SneakyThrows
    private File createImage(BufferedImage image200){
        BufferedImage image200Buff = new BufferedImage(image200.getWidth(),
                image200.getHeight(), BufferedImage.TYPE_INT_RGB);
        image200Buff.createGraphics().drawImage(image200, 0, 0, Color.WHITE, null);
        String filename = UUID.randomUUID().toString().replace("-", "").substring(0, 25) + ".jpg";
        File file = new File(filename);
        ImageIO.write(image200Buff, "jpg", file);
        return file;
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
        return (UserDto) UserDto.from(usersRepository.getUserByCookieValue(uuid));
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
    public Optional<User> createUser(User model) {
        return usersRepository.createUser(model);
    }

}
