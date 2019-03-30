package ru.itis.logistic_service.repositories;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.logistic_service.models.Auth;
import ru.itis.logistic_service.models.User;


import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class AuthRepositoryJdbcTemplateImpl implements AuthRepository {

    private JdbcTemplate jdbcTemplate;

    //language=SQL
    private static final String SQL_INSERT =
            "insert into auth_cookie(cookie_value , user_id) values (?, ?)";

    //language=SQL
    private static final String SQL_DELETE_QUERY = "DELETE FROM auth_cookie WHERE id = ?";

    //language=SQL
    private static final String SQL_SELECT_BY_COOKIE_VALUE =
            "select * from auth_cookie where cookie_value = ?";

    public AuthRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    RowMapper<Auth> authRowMapper = ((resultSet, i) -> Auth.builder()
            .cookieValue(resultSet.getString("cookie_value"))
            .userId(resultSet.getLong("user_id"))
            .build());


    @Override
    public void save(Auth model) {
        jdbcTemplate.update(SQL_INSERT, model.getCookieValue(), model.getUserId());
    }

    @Override
    public Auth createSession(User user) {
        String uuid = UUID.randomUUID().toString();
        jdbcTemplate.update(SQL_INSERT, uuid, user.getId());
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_BY_COOKIE_VALUE, authRowMapper, uuid);
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public Optional<Auth> findByCookieValue(String cookieValue) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_BY_COOKIE_VALUE, authRowMapper, cookieValue));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Auth> findAll() {
        return null;
    }

    @Override
    public Optional<Auth> findOneByID(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE_QUERY, id);
        System.out.println("Удален cookie с id = " + id);
    }

    @Override
    public void update(Auth model) {

    }
}
