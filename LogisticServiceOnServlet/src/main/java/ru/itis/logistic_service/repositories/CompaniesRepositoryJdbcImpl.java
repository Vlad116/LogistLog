package ru.itis.logistic_service.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.itis.logistic_service.models.Carrier;
import ru.itis.logistic_service.models.Company;
import ru.itis.logistic_service.models.Customer;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

public class CompaniesRepositoryJdbcImpl implements CompaniesRepository {

    //language=SQL
    private static final String SQL_INSERT_QUERY =
            "INSERT INTO company (company_name, director_name, phone_number, company_role) values (?, ?, ?, ?);";

    //language=SQL
    private static final String SQL_DELETE_QUERY = "DELETE FROM company WHERE id = ?;";


    //language=SQL
    private static final String SQL_UPDATE_QUERY = " UPDATE company " +
            "SET company_name = ?" + ", director_name = ?" +
            ", phone_number = ?" +
//            ", company_role = ?" +
            "WHERE id = ?;";

    //language=SQL
    private static final String SQL_SET_CARRIER_ID = "UPDATE company SET carrier_id = ? WHERE id = ?";

    //language=SQL
    private static final String SQL_SET_CUSTOMER_ID = "UPDATE company SET customer_id = ? WHERE id = ?";

    //language=SQL
    private static final String SQL_SET_USER_ID = "UPDATE company SET user_id = ? WHERE id = ?";

    //language=SQL
    private static final String SQL_SELECT_ALL =
            "SELECT * FROM company";

    //language=SQL
    private static final String SQL_SELECT_COMPANY_BY_ID =
            "SELECT * FROM company WHERE id = ?";

    //language=SQL
    private static final String SQL_FIND_COMPANY_BY_COMPANY_NAME =
            "SELECT * FROM company WHERE company_name = ?";

    private JdbcTemplate jdbcTemplate;

    public CompaniesRepositoryJdbcImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<Company> companyRowMapper = (resultSet, i) -> Company.builder()
            .id(resultSet.getLong("id"))
            .companyName(resultSet.getString("company_name"))
            .directorName(resultSet.getString("director_name"))
            .phoneNumber(resultSet.getString("phone_number"))
            .company_role(resultSet.getString("company_role"))
            .carrier(Carrier.builder().id(resultSet.getLong("carrier_id")).build())
            .customer(Customer.builder().id(resultSet.getLong("customer_id")).build())
            .build();


    @Override
    public void save(Company model) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps =
                            connection.prepareStatement(SQL_INSERT_QUERY, new String[]{"id"});
                    ps.setString(1, model.getCompanyName());
                    ps.setString(2, model.getDirectorName());
                    ps.setString(3, model.getPhoneNumber());
                    ps.setString(4, model.getCompany_role());

                    return ps;
                }, keyHolder);

        model.setId(keyHolder.getKey().longValue());
        System.out.println("Сохраненa компания с id = " + model.getId());
    }

    @Override
    public Optional<Company> createCompany(Company model) {

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps =
                            connection.prepareStatement(SQL_INSERT_QUERY, new String[]{"id"});
                    ps.setString(1, model.getCompanyName());
                    ps.setString(2, model.getDirectorName());
                    ps.setString(3, model.getPhoneNumber());
                    ps.setString(4, model.getCompany_role());
                    return ps;
                }, keyHolder);

        model.setId(keyHolder.getKey().longValue());
        System.out.println("Сохраненa компания с id = " + model.getId());

        return findOneByID(model.getId());

    }

    @Override
    public void update(Company model) {
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps =
                            connection.prepareStatement(SQL_UPDATE_QUERY);
                    ps.setString(1, model.getCompanyName());
                    ps.setString(2, model.getDirectorName());
                    ps.setString(3, model.getPhoneNumber());
//                    ps.setString(4, model.getCompany_role());
                    ps.setLong(4, model.getId());
                    return ps;

                });

        System.out.println("Обновлена компания с id = " + model.getId());
    }

    @Override
    public void setCarrierID(Long carrierID, Long companyID) {
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps =
                            connection.prepareStatement(SQL_SET_CARRIER_ID);
                    ps.setLong(1, carrierID);
                    ps.setLong(2, companyID);
                    return ps;

                });
    }

    @Override
    public void setCustomerID(Long customerID, Long companyID) {
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps =
                            connection.prepareStatement(SQL_SET_CUSTOMER_ID);
                    ps.setLong(1, customerID);
                    ps.setLong(2, companyID);
                    return ps;

                });

    }

    @Override
    public void setUserID(Long UserID, Long companyID) {
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps =
                            connection.prepareStatement(SQL_SET_USER_ID);
                    ps.setLong(1, UserID);
                    ps.setLong(2, companyID);
                    return ps;
                });
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE_QUERY, id);
        System.out.println("Удалена компания с id = " + id);
    }

    @Override
    public List<Company> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, companyRowMapper);
    }


    @Override
    public Optional<Company> findOneByID(Long id) {
        return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_COMPANY_BY_ID, companyRowMapper, id));
    }

    @Override
    public Optional<Company> findOneByPhoneNumber(String phoneNumber) {
        return Optional.empty();
    }

    @Override
    public Optional<Company> findOneByCompanyName(String companyName) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_FIND_COMPANY_BY_COMPANY_NAME, companyRowMapper, companyName));
    }

    @Override
    public Optional<Company> findOneByDirectorName(String directorName) {
        return Optional.empty();
    }

}
