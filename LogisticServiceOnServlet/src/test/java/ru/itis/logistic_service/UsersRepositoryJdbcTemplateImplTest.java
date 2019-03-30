package ru.itis.logistic_service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.itis.logistic_service.models.User;
import ru.itis.logistic_service.repositories.UsersRepositoryJdbcTemplateImpl;

import static org.junit.Assert.*;

public class UsersRepositoryJdbcTemplateImplTest {

    UsersRepositoryJdbcTemplateImpl usersRepository;

    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "logistic";
    private static final String URL = "jdbc:postgresql://localhost:5432/logistic_service";

    @Before
    public void setUp() throws Exception {
        DriverManagerDataSource dataSource =
                new DriverManagerDataSource();

        dataSource.setUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);

        usersRepository = new UsersRepositoryJdbcTemplateImpl(dataSource);
    }

    @Test
    public void findOne() {
        System.out.println(usersRepository.findOneByID(1L));
    }

    @Test
    public void save() {
        User beforeSave = User.builder()
                .firstName("Слава")
                .lastName("Сервлетам ")
                .email("watnikam@net.net")
                .hashPassword("UA")
//                .birhdayDate()
                .build();

        usersRepository.save(beforeSave);
        System.out.println(beforeSave);
    }

    @Test
    public void delete() {
        usersRepository.delete(13L);
    }

    @Test
    public void findAll() {
        System.out.println(usersRepository.findAll());
    }

}
