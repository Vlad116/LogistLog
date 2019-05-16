package ru.itis.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.itis.models.Company;
import ru.itis.models.User;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;
import java.util.Optional;

//@Lazy
@Component
public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {

    //language=SQL
    private static final String SQL_INSERT_QUERY =
            "INSERT INTO logistic_service_user(email, login, hash_password, first_name, last_name, address, phone_number) values (?, ?, ?, ?, ?, ?, ?);";

    //language=SQL
    private static final String SQL_DELETE_QUERY = "DELETE FROM logistic_service_user WHERE id = ?;";

    //language=SQL
    private static final String SQL_UPDATE_QUERY = " UPDATE logistic_service_user " +
            "SET email = ?" + ", login = ? " +
            ", first_name = ?" + ", last_name = ?" +
            ", address = ?" + ", phone_number = ?" +
//            ", birhday_date = ? " +
            "WHERE id = ?;";

    //language=SQL
    private static final String SQL_SET_COMPANY_ID = "UPDATE logistic_service_user SET company_id = ? WHERE id = ?";

    //language=SQL
    private static final String SQL_SET_IMG = "UPDATE logistic_service_user SET profile_img = ? WHERE id = ?";

    private static final String SQL_SET_PASSWORD = "UPDATE logistic_service_user SET hash_password = ? WHERE id = ?";

    // Карта, которая хранит единственного пользователя
    // и список его компаний
    private Map<User, List<Company>> userWithCompaniesMap;

    // Карта, которая хранит id-шники всех
    // пользователей и объекты самих пользователей, внутри
    // которых уже хранятся их компании

    private Map<Long, User> userIdWithCompaniesMap;

    // временная переменная, которая хранит текущего пользователя
    private User theOnlyUser;

    //language=SQL
    private static final String SQL_SELECT_USER_WITH_COMPANIES_BY_ID =
            "select company.id as company_id, * " +
                    "from logistic_service_user " +
                    "       join company on logistic_service_user.id = company.user_id " +
                    "where logistic_service_user.id = ?;";

    //language=SQL
    private static final String SQL_SELECT_USERS_WITH_COMPANIES =
            "select company.id as company_id, * " +
                    "from logistic_service_user " +
                    "       join company on logistic_service_user.id = company.user_id " +
                    "order by logistic_service_user.id;";

    //    language=sql
    private static final String SQL_GET_USER_BY_COOKIE_VALUE =
            "SELECT * " +
                    "FROM logistic_service_user  " +
                    "JOIN auth_cookie a on logistic_service_user.id = a.user_id " +
                    "WHERE cookie_value = ?";

    //language=SQL
    private static final String SQL_SELECT_ALL =
            "SELECT * FROM logistic_service_user";

    //language=SQL
    private static final String SQL_SELECT_USER_BY_ID =
            "SELECT * FROM logistic_service_user WHERE id = ?";


    //language=SQL
    private static final String SQL_SELECT_USER_BY_EMAIL =
            "SELECT * FROM logistic_service_user WHERE email = ?";

    //language=SQL
    private static final String SQL_SELECT_USER_BY_LOGIN =
            "SELECT * FROM logistic_service_user WHERE login = ?";

    //language=SQL
    private static final String SQL_SELECT_COMPANY_ID = "SELECT * FROM logistic_service_user WHERE id = ?";

    private JdbcTemplate jdbcTemplate;

//    @Lazy
    @Autowired
    public UsersRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<User> userRowMapper = (resultSet, i) -> User.builder()
            .id(resultSet.getLong("id"))
            .email(resultSet.getString("email"))
            .login(resultSet.getString("login"))
            .company(Company.builder().id(resultSet.getLong("company_id")).build())
            .hashPassword(resultSet.getString("hash_password"))
            .firstName(resultSet.getString("first_name"))
            .lastName(resultSet.getString("last_name"))
            .address(resultSet.getString("address"))
            .imgPath(resultSet.getString("profile_img"))
            .phoneNumber(resultSet.getString("phone_number"))
            .build();

    @Override
    public void save(User model) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps =
                            connection.prepareStatement(SQL_INSERT_QUERY, new String[]{"id"});
                    ps.setString(1, model.getEmail());
                    ps.setString(2, model.getLogin());
                    ps.setString(3, model.getHashPassword());
                    ps.setString(4, model.getFirstName());
                    ps.setString(5, model.getLastName());
                    ps.setString(6, model.getAddress());
                    ps.setString(7, model.getPhoneNumber());
                    return ps;
                }, keyHolder);

        model.setId(keyHolder.getKey().longValue());
        System.out.println("Сохранен пользователь с id = " + model.getId());
    }


    @Override
    public Optional<User> createUser(User model) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps =
                            connection.prepareStatement(SQL_INSERT_QUERY, new String[]{"id"});
                    ps.setString(1, model.getEmail());
                    ps.setString(2, model.getLogin());
                    ps.setString(3, model.getHashPassword());
                    ps.setString(4, model.getFirstName());
                    ps.setString(5, model.getLastName());
                    ps.setString(6, model.getAddress());
                    ps.setString(7, model.getPhoneNumber());
                    return ps;
                }, keyHolder);

        model.setId(keyHolder.getKey().longValue());

        System.out.println("Сохранен пользователь с id = " + model.getId());

        return findOneByLogin(model.getLogin());

    }

    @Override
    public User getUserById(Long user_id) {
        return (jdbcTemplate.queryForObject(SQL_SELECT_USER_BY_ID, userRowMapper, user_id));
    }

    @Override
    public void setCompanyID(Long companyID, Long userId) {
        System.out.println(companyID);
        System.out.println(userId);
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps =
                            connection.prepareStatement(SQL_SET_COMPANY_ID);
                    ps.setLong(1, companyID);
                    ps.setLong(2, userId);
                    return ps;

                });
    }

    @Override
    public void setProfileImg(String imgPath, Long userId) {
        System.out.println(imgPath);
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps =
                            connection.prepareStatement(SQL_SET_IMG);
                    ps.setString(1, imgPath);
                    ps.setLong(2, userId);
                    return ps;
                }
        );
    }


    @Override
    public void setPassword(String password, Long userId) {
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps =
                            connection.prepareStatement(SQL_SET_PASSWORD);
                    ps.setString(1, password);
                    ps.setLong(2, userId);
                    return ps;
                }
        );
    }

    @Override
    public void update(User model) {
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps =
                            connection.prepareStatement(SQL_UPDATE_QUERY);
                    ps.setString(1, model.getEmail());
                    ps.setString(2, model.getLogin());
                    ps.setString(3, model.getFirstName());
                    ps.setString(4, model.getLastName());
                    ps.setString(5, model.getAddress());
                    ps.setString(6, model.getPhoneNumber());
                    ps.setLong(7, model.getId());
                    return ps;

                });

        System.out.println("Обновлен пользователь с id = " + model.getId());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE_QUERY, id);
        System.out.println("Удален пользователь с id = " + id);
    }

    @Override
    public Long getCompanyID(Long userId) {
        System.out.println(userId);
        return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_COMPANY_ID, userRowMapper, userId)).get().getCompany().getId();
    }

    @Override
    public Optional<User> findOneByID(Long id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_USER_BY_ID, userRowMapper, id));
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, userRowMapper);
    }


    @Override
    public Optional<User> findOneByEmail(String email) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_USER_BY_EMAIL, userRowMapper, email));
    }

    @Override
    public Optional<User> findOneByLogin(String login) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_USER_BY_LOGIN, userRowMapper, login));
    }

    @Override
    public User getUserByCookieValue(String uuid) {
        return (jdbcTemplate.queryForObject(SQL_GET_USER_BY_COOKIE_VALUE, userRowMapper, uuid));
    }

    @Override
    public Optional<User> findOneByPhoneNumber(String phoneNumber) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_USER_BY_EMAIL, userRowMapper, phoneNumber));
    }


}