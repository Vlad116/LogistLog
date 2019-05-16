package ru.itis.config;

import jdk.nashorn.internal.objects.annotations.Property;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import javax.activation.MailcapCommandMap;
import javax.servlet.MultipartConfigElement;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Objects;
import java.util.Properties;

/**
 * 04.03.2019
 * AppConfig
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */

@Configuration
@EnableWebMvc
@ComponentScan("ru.itis")
@PropertySource("classpath:ru.itis//application.properties")
public class AppConfig extends WebMvcConfigurerAdapter {

//    @Lazy
    @Autowired
    private Environment environment;

//    @Lazy
    @Bean
    public FreeMarkerConfigurer freemarkerConfig() {
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        freeMarkerConfigurer.setTemplateLoaderPath("/WEB-INF/views/");
        freeMarkerConfigurer.setDefaultEncoding("UTF-8");
        return freeMarkerConfigurer;
    }

//    @Lazy
    @Bean(name = "freeMarkerViewResolver")
    public ViewResolver viewResolver() {
        FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();
        viewResolver.setCache(true);
        viewResolver.setPrefix("");
        viewResolver.setSuffix(".ftl");
        viewResolver.setContentType("text/html; charset=UTF-8");
        return viewResolver;
    }


    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(2000000);
        return multipartResolver;
    }

    @Bean
    public JavaMailSenderImpl mailSender(){
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setUsername(environment.getProperty("java.mail.username"));
        javaMailSender.setPassword(environment.getProperty("java.mail.password"));
//        javaMailSender.setPort(Integer.parseInt(Objects.requireNonNull(environment.getProperty("java.mail.port"))));
        javaMailSender.setHost(environment.getProperty("java.mail.host"));
        javaMailSender.setPort(Integer.parseInt(Objects.requireNonNull(environment.getProperty("java.mail.port"))));
        Properties emailProperties;
        emailProperties = System.getProperties();
        emailProperties.put("mail.smtp.port",environment.getProperty("java.mail.port"));
        emailProperties.put("mail.smtp.auth", "true");
        emailProperties.put("mail.smtp.starttls.enable", "true");
        emailProperties.put("mail.smtp.starttls.required","true");
        emailProperties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        javaMailSender.setJavaMailProperties(emailProperties);
        return javaMailSender;
    }

    @Bean
    public VelocityEngineFactoryBean velocityEngine(){
        VelocityEngineFactoryBean velocityEngineFactoryBean = new VelocityEngineFactoryBean();
        velocityEngineFactoryBean.setResourceLoaderPath("/WEB-INF/email-templates/");
        return velocityEngineFactoryBean;
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("/WEB-INF/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("/WEB-INF/js/");
        registry.addResourceHandler("/img/**").addResourceLocations("/WEB-INF/img/");
        registry.addResourceHandler("/email-templates/**").addResourceLocations("/WEB-INF/email-templates/");
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setPassword(environment.getProperty("db.password"));
        dataSource.setUsername(environment.getProperty("db.username"));
        dataSource.setUrl(environment.getProperty("db.url"));
        dataSource.setDriverClassName(environment.getProperty("db.driverClassName"));
        return dataSource;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


//    @Lazy
//    @Bean
//    public LocalSessionFactoryBean sessionFactory() {
//        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//        sessionFactory.setDataSource(dataSource());
//        sessionFactory.setPackagesToScan("ru.itis.models");
//        sessionFactory.setHibernateProperties(hibernateProperties());
//        return sessionFactory;
//    }
//
//    private Properties hibernateProperties() {
//        Properties hibernateProperties = new Properties();
//        String[] propsNames = {"hibernate.hbm2ddl.auto", "hibernate.dialect",
//                "hibernate.show_sql"};
//        Arrays.stream(propsNames).forEach(propName ->
//                hibernateProperties.setProperty(propName,
//                        environment.getProperty(propName)));
//        return hibernateProperties;
//    }
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//        LocalContainerEntityManagerFactoryBean em
//                = new LocalContainerEntityManagerFactoryBean();
//        em.setDataSource(dataSource());
//        em.setPackagesToScan("ru.itis.models");
//
//        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        em.setJpaVendorAdapter(vendorAdapter);
//        em.setJpaProperties(hibernateProperties());
//        return em;
//    }

}
