package ru.itis.logistic_service.repositories;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.itis.logistic_service.models.Company;
import ru.itis.logistic_service.models.Customer;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

public class CustomersRepositoryJdbcImpl implements CustomersRepository {

    //language=SQL
    private static final String SQL_ADD_CUSTOMER = "INSERT INTO customer_company(company_id) values (?)";

    //language=SQL
    private static final String SQL_FIND_ONE_BY_CUSTOMER_ID = "SELECT * FROM customer_company WHERE company_id = ?";

    private JdbcTemplate jdbcTemplate;

    public CustomersRepositoryJdbcImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<Customer> customerRowMapper = (resultSet, i) -> Customer.builder()
            .id(resultSet.getLong("id"))
            .company(Company.builder().id(resultSet.getLong("company_id")).build())
            .build();


    @Override
    public Optional<Customer> findOneByID(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(Customer model) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps =
                            connection.prepareStatement(SQL_ADD_CUSTOMER, new String[]{"id"});
                    ps.setLong(1, model.getCompany().getId());
                    return ps;
                }, keyHolder);

        model.setId(keyHolder.getKey().longValue());

        System.out.println("Сохраненa компания-заказчик с id = " + model.getId());
    }

    @Override
    public Optional<Customer> createCustomer(Customer model) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps =
                            connection.prepareStatement(SQL_ADD_CUSTOMER, new String[]{"id"});
                    ps.setLong(1, model.getCompany().getId());
                    return ps;
                }, keyHolder);

        model.setId(keyHolder.getKey().longValue());

        System.out.println("Сохраненa компания-заказчик с id = " + model.getId());
        return findOneByID(model.getId());
    }

    @Override
    public Customer findByCompanyID(Long companyId) {
        try {
            return jdbcTemplate.queryForObject(SQL_FIND_ONE_BY_CUSTOMER_ID, customerRowMapper, companyId);
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public void update(Customer model) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Customer> findAll() {
        return null;
    }

}
