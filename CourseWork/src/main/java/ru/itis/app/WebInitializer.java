package ru.itis.app;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import ru.itis.config.AppConfig;
import ru.itis.filters.AuthFilter;
import ru.itis.filters.LogFilter;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext container) throws ServletException {
        AnnotationConfigWebApplicationContext context
                = new AnnotationConfigWebApplicationContext();
        context.register(AppConfig.class);
        context.setServletContext(container);
        DispatcherServlet dispatcherServlet = new DispatcherServlet(context);
        ServletRegistration.Dynamic servlet = container.addServlet("dispatcher", dispatcherServlet);
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");

        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceEncoding(true);

        container.addFilter("authFilter", AuthFilter.class).addMappingForUrlPatterns(null, false, "/", "/home", "/profile", "/edituser", "/editpassword", "/findApplicationByLocation", "/addapplication", "/addcompany", "/editcompany");
        container.addFilter("encodingFilter", encodingFilter).addMappingForUrlPatterns(null,false,"/*");
        container.addFilter("logFilter", LogFilter.class).addMappingForUrlPatterns(null, false, "/*");

    }
}
