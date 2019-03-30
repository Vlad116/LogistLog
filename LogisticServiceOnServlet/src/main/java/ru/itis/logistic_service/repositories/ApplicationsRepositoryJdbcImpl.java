package ru.itis.logistic_service.repositories;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.itis.logistic_service.models.*;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

public class ApplicationsRepositoryJdbcImpl implements ApplicationsRepository {


    //language=SQL
    private static final String SQL_SELECT_ALL =
            "SELECT * FROM application";

    //language=SQL
    private static final String SQL_SELECT_USER_BY_ID =
            "SELECT * FROM application WHERE id = ?";

    //language=SQL
    private static final String SQL_SHOW_ALL_ADDED_APLICATION_BY_CUSTOMER_ID =
            "SELECT application.id       as application_id,\n" +
                    "       date,\n" +
                    "       loading_address,\n" +
                    "       unloading_address,\n" +
                    "       loading_type,\n" +
                    "       application.weight,\n" +
//                    "--        cargo_name,\n" +
//                    "--        cargo.type,\n" +
//                    "--        cargo.length,\n" +
//                    "--        cargo.width,\n" +
//                    "--        cargo.type,\n" +
                    "       payment,\n" +
//                    "       company.company_name AS customer_company,\n" +
//                    "       company.director_name,\n" +
//                    "       company.phone_number,\n" +
                    "       lsu.email,\n" +
                    "       company.id           as company_id\n" +
                    "FROM application\n" +
                    "            join customer_company as customer on application.customer_id = customer.id\n" +
                    "            join company on customer.company_id = company.id\n" +
                    "            join logistic_service_user lsu on company.user_id = lsu.id\n" +
                    "    --        join cargo on application.id = cargo.application_id\n" +
                    "WHERE application.customer_id = ?;";

    //language=SQL
    private static final String SQL_SHOW_ALL_APLY_APPLICATION_BY_CARRIER_ID =
            "SELECT application.id,\n" +
                    "       date,\n" +
                    "       loading_address,\n" +
                    "       unloading_address,\n" +
                    "       loading_type,\n" +
                    "       application.weight,\n" +
//                    "--        cargo_name,\n" +
//                    "--        cargo.length,\n" +
//                    "--        cargo.width,\n" +
//                    "--        cargo.type,\n" +
                    "       application.payment,\n" +
                    "       company.company_name as customer_company,\n" +
                    "       company.director_name,\n" +
                    "       company.phone_number,\n" +
                    "       lsu.email,\n" +
                    "       company.id\n" +
                    "FROM application\n" +
                    "       join customer_company as customer on application.customer_id = customer.id\n" +
                    "       join company on customer.company_id = company.id\n" +
                    "       join logistic_service_user lsu on company.user_id = lsu.id\n" +
//                    "    --        join cargo on application.id = cargo.application_id\n" +
                    "WHERE application.carrier_id = ?;";

    //language=SQL
    private static final String SQL_SHOW_ALL_APPLICATION_WITH_INFO = "SELECT application.id as application_id,\n" +
            "       date,\n" +
            "       loading_address,\n" +
            "       unloading_address,\n" +
            "       loading_type,\n" +
            "       application.weight,\n" +
//            "--        cargo_name,\n" +
//            "--        cargo.type,\n" +
//            "--        cargo.length,\n" +
//            "--        cargo.width,\n" +
//            "--        cargo.type,\n" +
            "       payment,\n" +
            "       company.company_name AS customer_company,\n" +
            "       company.director_name,\n" +
            "       lsu.first_name," +
            "       lsu.last_name," +
            "       company.phone_number,\n" +
            "       lsu.email,\n" +
            "       company.id as company_id\n" +
            "\n" +
            "FROM application\n" +
            "       join customer_company as customer on application.customer_id = customer.id\n" +
            "       join company on customer.company_id = company.id\n" +
            "       join logistic_service_user lsu on company.user_id = lsu.id\n" +
//            "    --        join cargo on application.id = cargo.application_id\n" +
            "WHERE application.carrier_id ISNULL;\n";

    private static final String SQL_FIND_ALL_APPLICATION_BY_ADDRESS = "SELECT application.id as application_id,\n" +
            "       date,\n" +
            "       loading_address,\n" +
            "       unloading_address,\n" +
            "       loading_type,\n" +
            "       application.weight,\n" +
//            "--        cargo_name,\n" +
//            "--        cargo.type,\n" +
//            "--        cargo.length,\n" +
//            "--        cargo.width,\n" +
//            "--        cargo.type,\n" +
            "       payment,\n" +
            "       company.company_name AS customer_company,\n" +
            "       company.director_name,\n" +
            "       lsu.first_name,\n" +
            "       lsu.last_name,\n" +
            "       company.phone_number,\n" +
            "       lsu.email,\n" +
            "       company.id as company_id\n" +
            "\n" +
            "FROM application\n" +
            "       join customer_company as customer on application.customer_id = customer.id\n" +
            "       join company on customer.company_id = company.id\n" +
            "       join logistic_service_user lsu on company.user_id = lsu.id\n" +
//            "    --        join cargo on application.id = cargo.application_id\n" +
            "WHERE application.carrier_id ISNULL AND loading_address ILIKE ? AND unloading_address LIKE ? ";


    //language=SQL
    private static final String SQL_INSERT_QUERY =
            "INSERT INTO application ( loading_address, unloading_address, weight, payment, loading_type, customer_id) values (?, ?, ?, ?, ?, ?);";

    //language=SQL
    private static final String SQL_DELETE_QUERY = "DELETE FROM application WHERE id = ?;";

    //language=SQL
    private static final String SQL_UPDATE_QUERY = " UPDATE application " +
            "SET "
//            + "date = ? "
            + " loading_address = ?" +
            ", unloading_address = ?" + ", weight = ?" +
            ", payment = ?" + ", loading_type = ?" +
            "WHERE id = ?;";

    private JdbcTemplate jdbcTemplate;

    public ApplicationsRepositoryJdbcImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<Application> applicationRowMapper = (resultSet, i) -> Application.builder()
            .id(resultSet.getLong("id"))
            .date(resultSet.getDate("date"))
            .loadingAddress(resultSet.getString("loading_address"))
            .unloadingAddress(resultSet.getString("unloading_address"))
            .weight(resultSet.getInt("weight"))
            .payment(resultSet.getInt("payment"))
            .loadingType(resultSet.getString("loading_type"))
            .customer(Customer.builder().id(resultSet.getLong("customer_id")).build())
            .build();

    @Override
    public void save(Application model) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps =
                            connection.prepareStatement(SQL_INSERT_QUERY, new String[]{"id"});
//                    ps.setDate(1, model.getDate());
                    ps.setString(1, model.getLoadingAddress());
                    ps.setString(2, model.getUnloadingAddress());
                    ps.setInt(3, model.getWeight());
                    ps.setInt(4, model.getPayment());
                    ps.setString(5, model.getLoadingType());
                    ps.setLong(6, model.getCustomer().getId());
                    return ps;
                }, keyHolder);

        model.setId(keyHolder.getKey().longValue());
        System.out.println("Сохранена заявка с id = " + model.getId());
    }

    @Override
    public void update(Application model) {

        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps =
                            connection.prepareStatement(SQL_UPDATE_QUERY, new String[]{"id"});
//                    ps.setDate(1, model.getDate());
                    ps.setString(1, model.getLoadingAddress());
                    ps.setString(2, model.getUnloadingAddress());
                    ps.setInt(3, model.getWeight());
                    ps.setInt(4, model.getPayment());
                    ps.setString(5, model.getLoadingType());
                    ps.setLong(6, model.getId());
                    return ps;
                });

        System.out.println("Обновлена заявка с id = " + model.getId());

    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE_QUERY, id);
        System.out.println("Удалена заявка с id = " + id);
    }


    @Override
    public Optional<Application> findOneByID(Long id) {
        return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_USER_BY_ID, applicationRowMapper, id));
    }

    @Override
    public List<Application> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, applicationRowMapper);
    }


    private RowMapper<Application> applicationWithInfoAboutCustomerRowMapper = (resultSet, i) -> Application.builder()
            .id(resultSet.getLong("application_id"))
            .date(resultSet.getDate("date"))
            .loadingAddress(resultSet.getString("loading_address"))
            .unloadingAddress(resultSet.getString("unloading_address"))
            .weight(resultSet.getInt("weight"))
            .payment(resultSet.getInt("payment"))
            .loadingType(resultSet.getString("loading_type"))
            .customer(Customer.builder()
//                    .id(resultSet.getLong("customer_id"))
                    .company(Company.builder()
                            .id(resultSet.getLong("company_id"))
                            .phoneNumber(resultSet.getString("phone_number"))
                            .directorName(resultSet.getString("director_name"))
                            .companyName(resultSet.getString("customer_company"))
                            .user(User.builder()
                                    .firstName(resultSet.getString("first_name"))
                                    .lastName(resultSet.getString("last_name"))
                                    .email(resultSet.getString("email"))
                                    .build())
                            .build())
                    .build())
            .build();

    private RowMapper<Application> applicationWithInfoAboutCarrierRowMapper = (resultSet, i) -> Application.builder()
            .id(resultSet.getLong("application_id"))
            .date(resultSet.getDate("date"))
            .loadingAddress(resultSet.getString("loading_address"))
            .unloadingAddress(resultSet.getString("unloading_address"))
            .weight(resultSet.getInt("weight"))
            .payment(resultSet.getInt("payment"))
            .loadingType(resultSet.getString("loading_type"))
//            .carrier(Carrier.builder()
////                    .id(resultSet.getLong("customer_id"))
//                    .company(Company.builder()
//                            .id(resultSet.getLong("company_id"))
////                            .phoneNumber(resultSet.getString("phone_number"))
////                            .directorName(resultSet.getString("director_name"))
////                            .companyName(resultSet.getString("carrier_company"))
//                            .user(User.builder()
//                                    .email(resultSet.getString("email"))
//                                    .build())
//                            .build())
//                    .build())
            .build();

    @Override
    public List<Application> showAllApplicationWithCompaniesInfo() {
        return jdbcTemplate.query(SQL_SHOW_ALL_APPLICATION_WITH_INFO, applicationWithInfoAboutCustomerRowMapper);
    }

    @Override
    public List<Application> findByLoadingAndUnloadingAddress(String loadingAddress, String unloadingAddress) {
        return jdbcTemplate.query(SQL_FIND_ALL_APPLICATION_BY_ADDRESS, applicationWithInfoAboutCustomerRowMapper, loadingAddress, unloadingAddress);
    }

//


    @Override
    public List<Application> showAllApplicationByCustomerId(Long customerId) {
        return jdbcTemplate.query(SQL_SHOW_ALL_ADDED_APLICATION_BY_CUSTOMER_ID, applicationWithInfoAboutCarrierRowMapper, customerId);
    }

    @Override
    public List<Application> showAllApplicationByCarrierId(Long carrierId) {
        return jdbcTemplate.query(SQL_SHOW_ALL_APLY_APPLICATION_BY_CARRIER_ID, applicationWithInfoAboutCustomerRowMapper, carrierId);
    }
}
