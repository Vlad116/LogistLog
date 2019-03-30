package ru.itis.logistic_service.servlets;

import lombok.SneakyThrows;
import ru.itis.logistic_service.dto.ApplicationWithInfoAboutCustomerDto;
import ru.itis.logistic_service.models.Application;
import ru.itis.logistic_service.models.Carrier;
import ru.itis.logistic_service.services.ApplicationService;
import ru.itis.logistic_service.services.CarrierService;
import ru.itis.logistic_service.services.UsersService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(name = "DistenceCalculatorServlet", value = "/calculator")
public class DistenceCalculatorServlet extends HttpServlet {
    private UsersService userService;
    private CarrierService carrierService;
    private ApplicationService applicationService;


    @Override
    @SneakyThrows
    public void init(ServletConfig config) {

        ServletContext context = config.getServletContext();

        applicationService = (ApplicationService) context.getAttribute("applicationsService");
        carrierService = (CarrierService) context.getAttribute("carriersService");
        userService = (UsersService) context.getAttribute("usersService");
    }

    @Override
    @SneakyThrows
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        // часть для хедера (приветствие)
        String cookie = "";
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals("auth")) {
                    cookie = cookies[i].getValue();
                }
            }
        }

        String greetingMessage = "Hello, anonymous!";

        if (!cookie.equals("")) {
            greetingMessage = "Welcome, " + userService.getUserByUUID(cookie).getLogin();
        }
        request.setAttribute("greetingMessage", greetingMessage);

//        // выводим все заявки
//        List<ApplicationWithInfoAboutCustomerDto> applications = applicationService.showAllApplicationWithCompanyInfo();
//        response.setContentType("application/json");
//        request.setAttribute("applications", applications);

        request.getRequestDispatcher("/ftl/calculatorAti.ftl").forward(request, response);

    }
}
