package ru.itis.logistic_service.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.itis.logistic_service.models.Carrier;
import ru.itis.logistic_service.models.Driver;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

public class DriversRepositoryJdbcImpl implements DriversRepository {

    //language=SQL
    private static final String SQL_INSERT_DRIVER_QUERY =
            "INSERT INTO driver(name, surname, phone_number, age, driver_expirience, carrier_company_id) values (?, ?, ?, ?, ?, ?);";

    //language=SQL
    private static final String SQL_DELETE_DRIVER_QUERY = "DELETE FROM driver WHERE id = ?;";

    //language=SQL
    private static final String SQL_UPDATE_DRIVER_QUERY = " UPDATE driver " +
            "SET name = ?" + ", surname = ? " + ", phone_number = ?" +
            ", age = ?" + ", driver_expirience = ?" +
            "WHERE id = ?;";


    //language=SQL
    private static final String SQL_SELECT_ALL_DRIVER =
            "SELECT * FROM driver";

    //language=SQL
    private static final String SQL_SELECT_DRIVER_BY_ID =
            "SELECT * FROM driver WHERE id = ?";

    private JdbcTemplate jdbcTemplate;

    public DriversRepositoryJdbcImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<Driver> driverRowMapper = (resultSet, i) -> Driver.builder()
            .id(resultSet.getLong("id"))
            .name(resultSet.getString("name"))
            .surname(resultSet.getString("surname"))
            .phoneNumber(resultSet.getString("phone_number"))
            .age(resultSet.getShort("age"))
            .drivingExperience(resultSet.getShort("driver_expirience"))
            .carrierCompany(Carrier.builder().id(resultSet.getLong("carrier_company_id")).build())
            .build();


    @Override
    public Optional<Driver> findOneByID(Long id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_DRIVER_BY_ID, driverRowMapper, id));
    }

    @Override
    public void save(Driver model) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps =
                            connection.prepareStatement(SQL_INSERT_DRIVER_QUERY, new String[]{"id"});
                    ps.setString(1, model.getName());
                    ps.setString(2, model.getSurname());
                    ps.setString(3, model.getPhoneNumber());
                    ps.setShort(4, model.getAge());
                    ps.setShort(5, model.getDrivingExperience());
                    ps.setLong(6, model.getCarrierCompany().getId());
                    return ps;
                }, keyHolder);

        model.setId(keyHolder.getKey().longValue());
        System.out.println("Сохранен водитель с id = " + model.getId());
    }

    @Override
    public void update(Driver model) {
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps =
                            connection.prepareStatement(SQL_UPDATE_DRIVER_QUERY);
                    ps.setString(1, model.getName());
                    ps.setString(2, model.getSurname());
                    ps.setString(3, model.getPhoneNumber());
                    ps.setShort(4, model.getAge());
                    ps.setShort(5, model.getDrivingExperience());
                    ps.setLong(6, model.getId());
                    return ps;

                });

        System.out.println("Обновлен водитель с id = " + model.getId());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE_DRIVER_QUERY, id);
        System.out.println("Удален водитель с id = " + id);
    }

    @Override
    public List<Driver> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL_DRIVER, driverRowMapper);
    }
}
