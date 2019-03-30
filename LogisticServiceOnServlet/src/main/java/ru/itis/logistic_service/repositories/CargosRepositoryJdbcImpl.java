package ru.itis.logistic_service.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.logistic_service.models.Cargo;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class CargosRepositoryJdbcImpl implements CargosRepository {

    //language=SQL
    private static final String SQL_SELECT_ALL =
            "SELECT * FROM application";

    //language=SQL
    private static final String SQL_SELECT_USER_BY_ID =
            "SELECT * FROM application WHERE id = ?";


    //language=SQL
    private static final String SQL_INSERT_QUERY =
            "INSERT INTO cargo (id, date, loading_address, unloading_address, weight, payment, loading_type) values (?, ?, ?, ?, ?, ?, ?);";

    //language=SQL
    private static final String SQL_DELETE_QUERY = "DELETE FROM application WHERE id = ?;";

    //language=SQL
    private static final String SQL_UPDATE_QUERY = " UPDATE application " +
            "SET date = ?" + ", loading_address = ?" +
            ", unloading_address = ?" + ", weight = ?" +
            ", payment = ?" + ", loading_type = ?" +
            "WHERE id = ?;";

    //language=SQL
    private static final String SQL_SET_APP_ID = "UPDATE cargo SET cargo.application_id = ? WHERE cargo.id = ?";

    private JdbcTemplate jdbcTemplate;

    public CargosRepositoryJdbcImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<Cargo> applicationRowMapper = (resultSet, i) -> Cargo.builder()
            .id(resultSet.getLong("id"))
            .cargoName(resultSet.getString("cargo_name"))
            .type(resultSet.getString("type"))
            .weight(resultSet.getInt("weight"))
            .length(resultSet.getInt("length"))
            .width(resultSet.getInt("width"))
            .height(resultSet.getInt("height"))
            .build();


    @Override
    public List<Cargo> findAllByType(String type) {
        return null;
    }

    @Override
    public Optional<Cargo> findOneByID(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(Cargo model) {

    }

    @Override
    public void update(Cargo model) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Cargo> findAll() {
        return null;
    }
}
