package ru.itis.logistic_service.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.itis.logistic_service.models.Carrier;
import ru.itis.logistic_service.models.Tractor;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

public class TractorRepositoryImpl implements TractorsRepository {

    //language=SQL
    private static final String SQL_INSERT_TRACTOR_QUERY =
            "INSERT INTO tractor(registration_number, years_of_exploitation, mileage, model, mark, type, carrier_company_id) values (?, ?, ?, ?, ?, ?,?);";

    //language=SQL
    private static final String SQL_DELETE_TRACTOR_QUERY = "DELETE FROM tractor WHERE id = ?;";

    //language=SQL
    private static final String SQL_UPDATE_TRACTOR_QUERY = " UPDATE tractor SET registration_number = ?," +
            " years_of_exploitation = ? " + ", mileage = ?" +
//            ", model = ?" + ", mark = ?" + ", type = ?" +
            "WHERE id = ?;";


    //language=SQL
    private static final String SQL_SELECT_ALL_TRACTOR =
            "SELECT * FROM tractor";

    //language=SQL
    private static final String SQL_SELECT_TRACTOR_BY_ID =
            "SELECT * FROM tractor WHERE id = ?";

    private JdbcTemplate jdbcTemplate;

    public TractorRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<Tractor> tractorRowMapper = (resultSet, i) -> Tractor.builder()
            .id(resultSet.getLong("id"))
            .registrationNumber(resultSet.getString("registration_number"))
            .yearsOfExploitation(resultSet.getByte("years_of_exploitation"))
            .mileage(resultSet.getInt("mileage"))
            .model(resultSet.getString("model"))
            .mark(resultSet.getString("mark"))
            .type(resultSet.getString("type"))
//            .driver(resultSet.getString("driver_id"))
            .carrier(Carrier.builder().id(resultSet.getLong("carrier_company_id")).build())
            .build();


    @Override
    public Optional<Tractor> findOneByID(Long id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_TRACTOR_BY_ID, tractorRowMapper, id));
    }

    @Override
    public void save(Tractor model) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps =
                            connection.prepareStatement(SQL_INSERT_TRACTOR_QUERY, new String[]{"id"});
                    ps.setString(1, model.getRegistrationNumber());
                    ps.setShort(2, model.getYearsOfExploitation());
                    ps.setLong(3, model.getMileage());
                    ps.setString(4, model.getModel());
                    ps.setString(5, model.getMark());
                    ps.setString(6, model.getType());
                    ps.setLong(7, model.getCarrier().getId());
                    return ps;
                }, keyHolder);

        model.setId(keyHolder.getKey().longValue());

        System.out.println("Сохранен тягач с id = " + model.getId());
    }

    @Override
    public void update(Tractor model) {
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps =
                            connection.prepareStatement(SQL_UPDATE_TRACTOR_QUERY);
                    ps.setString(1, model.getRegistrationNumber());
                    ps.setShort(2, model.getYearsOfExploitation());
                    ps.setLong(3, model.getMileage());
                    ps.setLong(4, model.getId());
//                    ps.setString(4, model.getModel());
//                    ps.setString(5, model.getMark());
//                    ps.setString(6, model.getType());
                    return ps;

                });

        System.out.println("Обновлен тягач с id = " + model.getId());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE_TRACTOR_QUERY, id);
        System.out.println("Удален тягач с id = " + id);
    }

    @Override
    public List<Tractor> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL_TRACTOR, tractorRowMapper);
    }

    @Override
    public Optional<Tractor> findOneByRegistrationNumber(String registrationNumber) {
        return Optional.empty();
    }

}
