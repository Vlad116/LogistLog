package ru.itis.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.itis.dto.ApplicationWithInfoAboutCarrierDto;
import ru.itis.dto.ApplicationWithInfoAboutCustomerDto;
import ru.itis.models.*;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

//@Lazy
@Component
public class ApplicationsRepositoryJdbcImpl implements ApplicationsRepository {


    //language=SQL
    private static final String SQL_SELECT_ALL =
            "SELECT * FROM application";


    //language=SQL
    private static final String SQL_SELECT_APPLICATION_BY_ID =
            "SELECT * FROM application WHERE id = ?";

    //language=SQL
    private static final String SQL_SET_CARRIER_ID = "UPDATE application SET carrier_id = ? WHERE id = ?";

    //language=SQL
    private static final String SQL_SHOW_ALL_ADDED_APLICATION_BY_CUSTOMER_ID =
            "SELECT application.id as application_id,\n" +
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
                    "       company.company_name AS customer_company,\n" +
                    "       company.director_name,\n" +
                    "       company.phone_number,\n" +
                    "       lsu.email,\n" +
                    "       lsu.first_name,\n" +
                    "       lsu.last_name,\n" +
                    "       company.id   as company_id\n" +
                    "FROM application\n" +
                    "            join customer_company as customer on application.customer_id = customer.id\n" +
                    "            join company on customer.company_id = company.id\n" +
                    "            join logistic_service_user lsu on company.user_id = lsu.id\n" +
                    "    --        join cargo on application.id = cargo.application_id\n" +
                    "WHERE application.customer_id = ? AND application.carrier_id ISNULL;";

    private static final String SQL_SHOW_ALL_APLY_APPLICATION_BY_CUSTOMER_ID =
            "SELECT application.id as application_id,\n" +
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
                    "       company.company_name as carrier_company,\n" +
                    "       company.director_name,\n" +
                    "       company.phone_number,\n" +
                    "       lsu.email,\n" +
                    "       lsu.first_name,\n" +
                    "       lsu.last_name,\n" +
                    "       company.id as company_id\n" +
                    "FROM application\n" +
                    "       join carrier_company as carrier on application.carrier_id = carrier.id\n" +
                    "       join company on carrier.company_id = company.id\n" +
                    "       join logistic_service_user lsu on company.user_id = lsu.id\n" +
//                    "    --        join cargo on application.id = cargo.application_id\n" +
                    "WHERE application.customer_id = ? AND application.carrier_id IS NOT NULL;";


    //language=SQL
    private static final String SQL_SHOW_ALL_APLY_APPLICATION_BY_CARRIER_ID =
            "SELECT application.id as application_id,\n" +
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
                    "       lsu.first_name,\n" +
                    "       lsu.last_name,\n" +
                    "       company.id as company_id\n" +
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
            "WHERE application.carrier_id ISNULL AND loading_address ILIKE ? AND unloading_address ILIKE ? ";


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

//    @Lazy
    @Autowired
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
    public void setCarrierID(Long carrierID, Long applicationID) {
        System.out.println(carrierID + "  " + applicationID);
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps =
                            connection.prepareStatement(SQL_SET_CARRIER_ID);
                    ps.setLong(1, carrierID);
                    ps.setLong(2, applicationID);
                    return ps;
                });
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE_QUERY, id);
        System.out.println("Удалена заявка с id = " + id);
    }


    @Override
    public Optional<Application> findOneByID(Long id) {
        return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_APPLICATION_BY_ID, applicationRowMapper, id));
    }

    @Override
    public List<Application> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, applicationRowMapper);
    }


    private RowMapper<ApplicationWithInfoAboutCustomerDto> applicationWithInfoAboutCustomerRowMapper = (resultSet, i) -> ApplicationWithInfoAboutCustomerDto.builder()
            .id(resultSet.getLong("application_id"))
            .date(resultSet.getDate("date"))
            .loadingAddress(resultSet.getString("loading_address"))
            .unloadingAddress(resultSet.getString("unloading_address"))
            .weight(resultSet.getInt("weight"))
            .payment(resultSet.getInt("payment"))
            .loadingType(resultSet.getString("loading_type"))
            .phoneNumber(resultSet.getString("phone_number"))
            .directorName(resultSet.getString("director_name"))
            .companyName(resultSet.getString("customer_company"))
            .firstName(resultSet.getString("first_name"))
            .lastName(resultSet.getString("last_name"))
            .email(resultSet.getString("email"))
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

    private RowMapper<ApplicationWithInfoAboutCarrierDto> applicationWithInfoAboutCarrierRowMapper = (resultSet, i) -> ApplicationWithInfoAboutCarrierDto.builder()
            .id(resultSet.getLong("application_id"))
            .date(resultSet.getDate("date"))
            .loadingAddress(resultSet.getString("loading_address"))
            .unloadingAddress(resultSet.getString("unloading_address"))
            .weight(resultSet.getInt("weight"))
            .payment(resultSet.getInt("payment"))
            .loadingType(resultSet.getString("loading_type"))
            .phoneNumber(resultSet.getString("phone_number"))
            .directorName(resultSet.getString("director_name"))
            .companyName(resultSet.getString("carrier_company"))
            .firstName(resultSet.getString("first_name"))
            .lastName(resultSet.getString("last_name"))
            .email(resultSet.getString("email"))
            .carrier(Carrier.builder()
//                    .id(resultSet.getLong("carrier_id"))
                    .company(Company.builder()
                            .id(resultSet.getLong("company_id"))
                            .phoneNumber(resultSet.getString("phone_number"))
                            .directorName(resultSet.getString("director_name"))
                            .companyName(resultSet.getString("carrier_company"))
                            .user(User.builder()
                                    .email(resultSet.getString("email"))
                                    .firstName(resultSet.getString("first_name"))
                                    .lastName(resultSet.getString("last_name"))
                                    .build())
                            .build())
                    .build())
            .build();

    @Override
    public List<ApplicationWithInfoAboutCustomerDto> showAllApplicationWithCompaniesInfo() {
        return jdbcTemplate.query(SQL_SHOW_ALL_APPLICATION_WITH_INFO, applicationWithInfoAboutCustomerRowMapper);
    }

    @Override
    public List<ApplicationWithInfoAboutCustomerDto> findByLoadingAndUnloadingAddress(String loadingAddress, String unloadingAddress) {
        return jdbcTemplate.query(SQL_FIND_ALL_APPLICATION_BY_ADDRESS, applicationWithInfoAboutCustomerRowMapper, loadingAddress, unloadingAddress);
    }

    @Override
    public List<ApplicationWithInfoAboutCarrierDto> showAllApplyApplicationByCustomerId(Long customerId) {
        return jdbcTemplate.query(SQL_SHOW_ALL_APLY_APPLICATION_BY_CUSTOMER_ID, applicationWithInfoAboutCarrierRowMapper, customerId);
    }

    @Override
    public List<ApplicationWithInfoAboutCustomerDto> showAllAddedApplicationByCustomerId(Long customerId) {
        return jdbcTemplate.query(SQL_SHOW_ALL_ADDED_APLICATION_BY_CUSTOMER_ID, applicationWithInfoAboutCustomerRowMapper, customerId);
    }

    @Override
    public List<ApplicationWithInfoAboutCustomerDto> showAllApplyApplicationByCarrierId(Long carrierId) {
        return jdbcTemplate.query(SQL_SHOW_ALL_APLY_APPLICATION_BY_CARRIER_ID, applicationWithInfoAboutCustomerRowMapper, carrierId);
    }
}
