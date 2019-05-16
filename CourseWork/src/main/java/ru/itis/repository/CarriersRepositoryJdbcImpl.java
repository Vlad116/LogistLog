package ru.itis.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.itis.models.Carrier;
import ru.itis.models.Company;
import ru.itis.models.User;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

//@Lazy
@Component
public class CarriersRepositoryJdbcImpl implements CarriersRepository {

    //language=SQL
    private static final String SQL_ADD_CARRIER = "INSERT INTO carrier_company(company_id) values (?)";

    //language=SQL
    private static final String SQL_FIND_ONE_BY_COMPANY_ID = "SELECT * FROM carrier_company WHERE company_id = ?";

    //language=SQL
    private static final String SQL_SHOW_ALL_CARRIERS_WITH_COMPANY_INFO = "SELECT carrier_company.id, carrier_company.company_id, the_number_of_trucks, company_name,first_name,last_name, company.phone_number,email\n" +
            "FROM carrier_company" +
            "       join company on carrier_company.company_id = company.id" +
            "       join logistic_service_user lsu on company.user_id = lsu.id;";

    //language=SQL
    private static final String SQL_COUNT_NUMBER_OF_TRUCKS = "";

    //language=SQL
    private static final String SQL_GET_CARRIER_APPLY_APP = "SELECT * " +
            "FROM carrier_company " +
            "       join application app on carrier_company.id = app.carrier_id " +
            "WHERE carrier_company.company_id = ? " +
            "ORDER BY app.id;";

    private JdbcTemplate jdbcTemplate;

//    @Lazy
    @Autowired
    public CarriersRepositoryJdbcImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<Carrier> carrierRowMapper = (resultSet, i) -> Carrier.builder()
            .id(resultSet.getLong("id"))
            .theNumberOfTrucks(resultSet.getInt("the_number_of_trucks"))
            .company(Company.builder().id(resultSet.getLong("company_id")).build())
            .build();


    @Override
    public void save(Carrier model) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps =
                            connection.prepareStatement(SQL_ADD_CARRIER, new String[]{"id"});
                    ps.setLong(1, model.getCompany().getId());
                    return ps;
                }, keyHolder);

        model.setId(keyHolder.getKey().longValue());
        System.out.println("Сохраненa компания-перевозчик с id = " + model.getId());
    }

    @Override
    public Optional<Carrier> createCarrier(Carrier model) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps =
                            connection.prepareStatement(SQL_ADD_CARRIER, new String[]{"id"});
                    ps.setLong(1, model.getCompany().getId());
                    return ps;
                }, keyHolder);

        model.setId(keyHolder.getKey().longValue());
        System.out.println("Сохраненa компания-перевозчик с id = " + model.getId());
        return findOneByID(model.getId());
    }

    @Override
    public Carrier findByCompanyID(Long companyId) {
        try {
            return jdbcTemplate.queryForObject(SQL_FIND_ONE_BY_COMPANY_ID, carrierRowMapper, companyId);
        } catch (DataAccessException e) {
            return null;
        }
    }


    private RowMapper<Carrier> carrierWithCompanyRowMapper = (resultSet, i) -> Carrier.builder()
            .id(resultSet.getLong("id"))
            .theNumberOfTrucks(resultSet.getInt("the_number_of_trucks"))
            .company(Company.builder()
                    .id(resultSet.getLong("company_id"))
                    .companyName(resultSet.getString("company_name"))
                    .phoneNumber(resultSet.getString("phone_number"))
                    .user(User.builder()
                            .firstName(resultSet.getString("first_name"))
                            .lastName(resultSet.getString("last_name"))
                            .email(resultSet.getString("email"))
                            .build())
                    .build())
            .build();

    @Override
    public List<Carrier> showAllCarriersCompany() {
        return jdbcTemplate.query(SQL_SHOW_ALL_CARRIERS_WITH_COMPANY_INFO, carrierWithCompanyRowMapper);
    }

    @Override
    public void update(Carrier model) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Carrier> findAll() {
        return null;
    }

    @Override
    public Optional<Carrier> findOneByID(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Carrier> findOneByCompanyName(String companyName) {
        return Optional.empty();
    }

}
