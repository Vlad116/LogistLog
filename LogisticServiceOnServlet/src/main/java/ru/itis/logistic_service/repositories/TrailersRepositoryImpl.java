package ru.itis.logistic_service.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.itis.logistic_service.models.Carrier;
import ru.itis.logistic_service.models.Trailer;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

public class TrailersRepositoryImpl implements TrailersRepository {


    //language=SQL
    private static final String SQL_INSERT_TRAILER_QUERY =
            "INSERT INTO trailer(registration_number, years_of_exploitation, mileage,tonnage,mark, type, volume_in_cubic_meters,inner_length, inner_width, inner_height, carrier_company_id) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    //language=SQL
    private static final String SQL_DELETE_TRACTOR_QUERY = "DELETE FROM tractor WHERE id = ?;";

    //language=SQL
    private static final String SQL_UPDATE_TRAILER_QUERY = " UPDATE trailer SET registration_number = ?," +
            " years_of_exploitation = ? " + ", mileage = ?" +
//            ", model = ?" + ", mark = ?" + ", type = ?" +
            "WHERE id = ?;";


    //language=SQL
    private static final String SQL_SELECT_ALL_TRAILERS =
            "SELECT * FROM trailer";

    //language=SQL
    private static final String SQL_SELECT_TRAILER_BY_ID =
            "SELECT * FROM trailer WHERE id = ?";

    private JdbcTemplate jdbcTemplate;

    public TrailersRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<Trailer> trailerRowMapper = (resultSet, i) -> Trailer.builder()
            .id(resultSet.getLong("id"))
            .registrationNumber(resultSet.getString("registration_number"))
            .yearsOfExploitation(resultSet.getByte("years_of_exploitation"))
            .mileage(resultSet.getInt("mileage"))
            .tonnage(resultSet.getInt("tonnage"))
            .volume_in_cubic_meters(resultSet.getInt("volume_in_cubic_meters"))
            .inner_length(resultSet.getInt("inner_length"))
            .inner_height(resultSet.getInt("inner_height"))
            .inner_width(resultSet.getInt("inner_width"))
            .mark(resultSet.getString("mark"))
            .type(resultSet.getString("type"))
//            .driver(resultSet.getString("driver_id"))
            .carrier(Carrier.builder().id(resultSet.getLong("carrier_company_id")).build())
            .build();


    @Override
    public Optional<Trailer> findOneByID(Long id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_TRAILER_BY_ID, trailerRowMapper, id));
    }

    @Override
    public void save(Trailer model) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
//                    "INSERT INTO trailer(registration_number, years_of_exploitation, mileage,tonnage,mark, type, volume_in_cubic_meters,inner_length, inner_width, inner_height, carrier_company_id) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

                    PreparedStatement ps =
                            connection.prepareStatement(SQL_INSERT_TRAILER_QUERY, new String[]{"id"});
                    ps.setString(1, model.getRegistrationNumber());
                    ps.setShort(2, model.getYearsOfExploitation());
                    ps.setLong(3, model.getMileage());
                    ps.setInt(4, model.getTonnage());
                    ps.setString(5, model.getMark());
                    ps.setString(6, model.getType());
                    ps.setInt(7, model.getVolume_in_cubic_meters());
                    ps.setInt(8, model.getInner_length());
                    ps.setInt(9, model.getInner_width());
                    ps.setInt(10, model.getInner_height());
                    ps.setLong(11, model.getCarrier().getId());
                    return ps;
                }, keyHolder);

        model.setId(keyHolder.getKey().longValue());

        System.out.println("Сохранен прицеп с id = " + model.getId());
    }

    @Override
    public void update(Trailer model) {
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps =
                            connection.prepareStatement(SQL_UPDATE_TRAILER_QUERY);
                    ps.setString(1, model.getRegistrationNumber());
                    ps.setShort(2, model.getYearsOfExploitation());
                    ps.setLong(3, model.getMileage());
                    ps.setLong(4, model.getId());
//                    ps.setString(4, model.getModel());
//                    ps.setString(5, model.getMark());
//                    ps.setString(6, model.getType());
                    return ps;

                });

        System.out.println("Обновлен прицеп с id = " + model.getId());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE_TRACTOR_QUERY, id);
        System.out.println("Удален прицеп с id = " + id);
    }

    @Override
    public List<Trailer> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL_TRAILERS, trailerRowMapper);
    }

//    @Override
//    public Optional<Tractor> findOneByRegistrationNumber(String registrationNumber) {
//        return Optional.empty();
//    }
//
}
