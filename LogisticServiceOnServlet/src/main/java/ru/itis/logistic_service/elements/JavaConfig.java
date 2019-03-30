package ru.itis.logistic_service.elements;

import jdk.nashorn.internal.ir.Terminal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.logistic_service.repositories.*;
import ru.itis.logistic_service.services.*;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:application.properties")
public class JavaConfig {

    @Value("${dataSource.username}")
    private String username;

    @Value("${dataSource.password}")
    private String password;

    @Value("${dataSource.driverClassName}")
    private String driverClassName;

    @Value("${dataSource.url}")
    private String url;

    public JavaConfig() {
    }

    @Bean
    public DriverManagerDataSource dataSource() {

        DriverManagerDataSource dataSource =
                new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthRepository authRepository() {
        return new AuthRepositoryJdbcTemplateImpl(dataSource());
    }

    @Bean
    UsersRepository usersRepository() {
        return new UsersRepositoryJdbcTemplateImpl(dataSource());
    }

    @Bean
    public CargosRepository cargosRepository() {
        return new CargosRepositoryJdbcImpl(dataSource());
    }

    @Bean
    public CompaniesRepository companiesRepository() {
        return new CompaniesRepositoryJdbcImpl(dataSource());
    }


    @Bean
    public CarriersRepository carriersRepository() {
        return new CarriersRepositoryJdbcImpl(dataSource());
    }


    @Bean
    public CustomersRepository customersRepository() {
        return new CustomersRepositoryJdbcImpl(dataSource());
    }

    @Bean
    public ApplicationsRepository applicationsRepository() {
        return new ApplicationsRepositoryJdbcImpl(dataSource());
    }

    @Bean
    public DriversRepository driversRepository() {
        return new DriversRepositoryJdbcImpl(dataSource());
    }

    @Bean
    public TractorsRepository tractorsRepository() {
        return new TractorRepositoryImpl(dataSource());
    }

    @Bean
    public TrailersRepository trailersRepository() {
        return new TrailersRepositoryImpl(dataSource());
    }

    @Bean
    public UsersService usersService() {
        return new UsersServiceImpl(usersRepository(), authRepository(), passwordEncoder());
    }

    @Bean
    public ApplicationService applicationService() {
        return new ApplicationServiceImpl(applicationsRepository());
    }

    @Bean
    public AuthService authService() {
        return new AuthServiceImpl(authRepository());
    }

    @Bean
    public CarrierService carrierService() {
        return new CarrierServiceImpl(carriersRepository(), driversRepository(), tractorsRepository(), trailersRepository(), applicationsRepository());
    }

    @Bean
    public CompanyService companyService() {
        return new CompanyServiceImpl(companiesRepository(), carriersRepository(), customersRepository(), usersService());
    }

    @Bean
    public CustomerService customerService() {
        return new CustomerServiceImpl(applicationsRepository());
    }

}