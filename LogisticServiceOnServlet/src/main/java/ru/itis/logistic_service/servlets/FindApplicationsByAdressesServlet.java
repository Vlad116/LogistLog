package ru.itis.logistic_service.servlets;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.itis.logistic_service.dto.AddedApplicationDto;
import ru.itis.logistic_service.dto.ApplicationWithInfoAboutCustomerDto;
import ru.itis.logistic_service.elements.JavaConfig;
import ru.itis.logistic_service.models.Application;
import ru.itis.logistic_service.services.ApplicationService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "FindApplicationBtAdresses", value = "/findApplicationByLocation")
public class FindApplicationsByAdressesServlet extends HttpServlet {

    private ApplicationService applicationService;

    @Override
    @SneakyThrows
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        applicationService = (ApplicationService) context.getAttribute("applicationsService");
    }

    @Override
    @SneakyThrows
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String loadingAddress = request.getParameter("loadingAddress");
        String unloadingAddress = request.getParameter("unloadingAddress");
        System.out.println(loadingAddress + "  " + unloadingAddress);
        ObjectMapper objectMapper = new ObjectMapper();
        List<ApplicationWithInfoAboutCustomerDto> applicationWithInfoAboutCustomerDto = applicationService.findByLoadingAndUnloadingAddress(loadingAddress, unloadingAddress);
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(applicationWithInfoAboutCustomerDto));
    }

}
