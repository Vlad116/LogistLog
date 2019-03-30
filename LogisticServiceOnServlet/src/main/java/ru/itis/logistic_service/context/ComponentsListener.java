package ru.itis.logistic_service.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.itis.logistic_service.elements.JavaConfig;
import ru.itis.logistic_service.services.*;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ComponentsListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
//        ApplicationContext context =
//                new ClassPathXmlApplicationContext("ru.itis/context.xml");
//

        ApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
        UsersService usersService = context.getBean("usersService",UsersService.class);
        ApplicationService applicationService = context.getBean("applicationService",ApplicationService.class);
        CarrierService carrierService = context.getBean("carrierService",CarrierService.class);
        CustomerService customerService = context.getBean("customerService",CustomerService.class);
        CompanyService companyService = context.getBean("companyService",CompanyService.class);

        sce.getServletContext().setAttribute("usersService", usersService);
        sce.getServletContext().setAttribute("applicationsService", applicationService);
        sce.getServletContext().setAttribute("carriersService", carrierService);
        sce.getServletContext().setAttribute("customersService", customerService);
        sce.getServletContext().setAttribute("companiesService", companyService);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

}