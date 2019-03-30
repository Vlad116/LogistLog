package ru.itis.logistic_service.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.itis.logistic_service.dto.CarriersDto;
import ru.itis.logistic_service.dto.CompanyDto;
import ru.itis.logistic_service.forms.CompanyCreateForm;
import ru.itis.logistic_service.models.Company;
import ru.itis.logistic_service.models.User;
import ru.itis.logistic_service.repositories.*;
import ru.itis.logistic_service.services.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/addcompany")
public class AddCompanyServlet extends HttpServlet {

    private CompanyService companyService;
    private UsersService userService;

    @Override
    @SneakyThrows
    public void init(ServletConfig config) {

        ServletContext context = config.getServletContext();

        userService = (UsersService) context.getAttribute("usersService");
        companyService = (CompanyService) context.getAttribute("companiesService");


    }

    @Override
    @SneakyThrows
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        String companyName = request.getParameter("companyName");
        String directorName = request.getParameter("directorName");
        String companyRole = request.getParameter("companyRole");
        String phoneNumber = request.getParameter("phoneNumber");

        CompanyCreateForm companyCreateForm = CompanyCreateForm.builder()
                .companyName(companyName)
                .directorName(directorName)
                .company_role(companyRole)
                .phoneNumber(phoneNumber)
                .build();


        Cookie[] cookies = request.getCookies();

        User profileUser = null;
        Long userId = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("auth")) {
                    if (userService.isExistByCookie(cookie.getValue())) {

                        profileUser = userService.getUserByUUID(cookie.getValue());
                        userId = profileUser.getId();
                        Long companyId = userService.getCompanyID(userId);
                        ObjectMapper objectMapper = new ObjectMapper();
                        response.setContentType("application/json");
                        CompanyDto company;
                        if (companyId == null || companyId == 0) {

                            if (companyCreateForm.getCompany_role().equals("Carrier")) {
                                companyService.addCompanyAsCarrier(companyCreateForm, userId);
                                company = companyService.showCompany(companyId);
                                response.getWriter().write(objectMapper.writeValueAsString(company));
                                break;
                            } else if (companyCreateForm.getCompany_role().equals("Customer")) {
                                companyService.addCompanyAsCustomer(companyCreateForm, userId);
                                company = companyService.showCompany(companyId);
                                response.getWriter().write(objectMapper.writeValueAsString(company));
                                break;
                            } else {
                                companyService.createCompany(companyCreateForm, userId);
                                company = companyService.showCompany(companyId);
                                response.getWriter().write(objectMapper.writeValueAsString(company));
                            }

                            break;

                        } else {
                            System.out.println("Пользователь с " + userId + "уже завел компанию");
                            response.sendRedirect("/profile");
                            return;
                        }

                    } else {

                        System.out.println("Если нет куки auth, значит не заходил");
                        response.sendRedirect("/signIn");
                        return;
                    }
                }
            }
        }

        response.sendRedirect("/profile");
    }
}
