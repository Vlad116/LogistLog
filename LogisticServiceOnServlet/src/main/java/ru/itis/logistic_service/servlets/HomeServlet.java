package ru.itis.logistic_service.servlets;

import lombok.SneakyThrows;
import ru.itis.logistic_service.dto.ApplicationWithInfoAboutCustomerDto;
import ru.itis.logistic_service.dto.CarriersDto;
import ru.itis.logistic_service.services.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@WebServlet(name = "HomeServlet", value = "/home")
public class HomeServlet extends HttpServlet {

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

        // выводим все заявки
        List<ApplicationWithInfoAboutCustomerDto> applications = applicationService.showAllApplicationWithCompanyInfo();
//        System.out.println(applications);
        response.setContentType("application/json");
        request.setAttribute("applications", applications);

        // выводим всех Carriers

        List<CarriersDto> carriers = carrierService.showCarriers();
//        System.out.println(carriers);
        response.setContentType("application/json");
        request.setAttribute("carriers", carriers);

        request.getRequestDispatcher("/ftl/home.ftl").forward(request, response);

    }


}