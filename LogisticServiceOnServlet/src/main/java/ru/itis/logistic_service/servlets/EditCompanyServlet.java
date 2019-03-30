package ru.itis.logistic_service.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import ru.itis.logistic_service.dto.CompanyDto;
import ru.itis.logistic_service.forms.CompanyCreateForm;
import ru.itis.logistic_service.forms.CompanyEditForm;
import ru.itis.logistic_service.models.User;
import ru.itis.logistic_service.services.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/editcompany")

public class EditCompanyServlet extends HttpServlet {

    private CompanyService companyService;
    private UsersService usersService;

    @Override
    @SneakyThrows
    public void init(ServletConfig config) {
        ServletContext context = config.getServletContext();
        usersService = (UsersService) context.getAttribute("usersService");
        companyService = (CompanyService) context.getAttribute("companiesService");
    }

    @Override
    @SneakyThrows
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        Cookie[] cookies = request.getCookies();

        User profileUser = null;
        Long userId = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("auth")) {
                    if (usersService.isExistByCookie(cookie.getValue())) {
                        profileUser = usersService.getUserByUUID(cookie.getValue());
                        userId = profileUser.getId();
                    } else {
                        System.out.println("Если нет куки auth, значит не заходил");
                        response.sendRedirect("/signIn");
                        return;
                    }
                }
            }
        }

        String editCompanyName = request.getParameter("companyName");
        String editDirectorName = request.getParameter("directorName");
        String editPhoneNumber = request.getParameter("phoneNumber");

        Long companyId = usersService.getCompanyID(userId);

        CompanyEditForm companyEditForm = CompanyEditForm.builder()
                .id(companyId)
                .companyName(editCompanyName)
                .directorName(editDirectorName)
                .phoneNumber(editPhoneNumber)
                .build();

        companyService.editCompany(companyEditForm);

        ObjectMapper objectMapper = new ObjectMapper();
        CompanyDto companyDto = companyService.showCompany(companyId);
        System.out.println(companyDto + " " + companyId);
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(companyDto));

        //        response.sendRedirect("/profile");

    }
}
