package ru.itis.logistic_service.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import ru.itis.logistic_service.dto.AddedApplicationDto;
import ru.itis.logistic_service.dto.CompanyDto;
import ru.itis.logistic_service.dto.UserDto;
import ru.itis.logistic_service.forms.UserEditForm;
import ru.itis.logistic_service.models.*;
import ru.itis.logistic_service.services.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

// профиль пользователя (его заявки, принятые заявки, доб. компании, и.т.п.)

@WebServlet(name = "ProfileServlet", value = "/profile")
public class ProfileServlet extends HttpServlet {


    private ApplicationService applicationService;
    private CarrierService carrierService;
    private UsersService userService;
    private CompanyService companyService;
    private CustomerService customerService;


    @Override
    @SneakyThrows
    public void init(ServletConfig config) {

        ServletContext context = config.getServletContext();
        userService = (UsersService) context.getAttribute("usersService");
        applicationService = (ApplicationService) context.getAttribute("applicationsService");
        customerService = (CustomerService) context.getAttribute("customersService");
        carrierService = (CarrierService) context.getAttribute("carriersService");
        companyService = (CompanyService) context.getAttribute("companiesService");

    }

    @Override
    @SneakyThrows
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        String email = request.getParameter("email");
        String login = request.getParameter("login");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");


        User profileUser = null;
        Cookie[] cookies = request.getCookies();
        String cookieValue = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("auth")) {
                    if (userService.isExistByCookie(cookie.getValue())) {
                        cookieValue = cookie.getValue();
                        profileUser = userService.getUserByUUID(cookieValue);
                        break;
                    } else {
                        System.out.println("Если нет куки auth, значит не заходил");
                    }
                }
            }
        }

        assert profileUser != null;

        UserEditForm userEditForm = UserEditForm.builder()
                .id(profileUser.getId())
                .email(email)
                .login(login)
                .firstName(firstName)
                .lastName(lastName)
                .address(address)
                .phoneNumber(phoneNumber)
                .build();

        System.out.println(userEditForm.toString());

        userService.editUser(userEditForm);

        UserDto profileUserDto = userService.showUserByUUID(cookieValue);

        ObjectMapper objectMapper = new ObjectMapper();
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(profileUserDto));
//        response.sendRedirect("/profile");

    }

    @Override
    @SneakyThrows
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        UserDto profileUser = null;

        String cookie = "";

        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals("auth")) {
                    if (userService.isExistByCookie(cookies[i].getValue())) {

                        cookie = cookies[i].getValue();
                        profileUser = userService.showUserByUUID(cookie);
                        break;

                    } else {

                        System.out.println("Если нет куки auth, значит не заходил");
                        response.sendRedirect("/signIn");
                        return;

                    }
                }
            }
        }

        String greetingMessage = "Hello, anonymous!";

        if (!cookie.equals("")) {
            greetingMessage = "Welcome, " + profileUser.getLogin();
        }

        request.setAttribute("greetingMessage", greetingMessage);
        System.out.println("profile = " + (profileUser != null ? profileUser.toString() : null));

        response.setContentType("application/json");
        request.setAttribute("profile", profileUser);

        // передаем компанию

        assert profileUser != null;
        Long companyId = profileUser.getCompany().getId();
        CompanyDto company;
        if (companyId != null && companyId != 0) {

            company = companyService.showCompany(companyId);
            boolean companyIsPresent = (company != null);

            if (companyIsPresent) {
                response.setContentType("application/json");
                request.setAttribute("company", company);
            } else {
                String messageCompany = "Этот пользователь еще не добавил компанию";
                company = CompanyDto.builder().company_role(" ")
                        .directorName(" ")
                        .phoneNumber(" ")
                        .companyName(" ")
                        .customer(Customer.builder().build())
                        .carrier(Carrier.builder().build())
                        .build();
                response.setContentType("application/json");
                request.setAttribute("company", company);
                response.setContentType("application/json");
                request.setAttribute("messageCompany", messageCompany);
            }

            // передаем добавл. заявки

            String messageCustomer = null;

            if (companyIsPresent) {

                Long customerId = company.getCustomer().getId();

                if (customerId != null && customerId != 0) {

                    // выводим все заявки по Customer
                    List<AddedApplicationDto> addedApplications = customerService.showAllAddedApplication(customerId);
                    messageCustomer = "Все заявки оставленные компанией";
                    response.setContentType("application/json");
                    request.setAttribute("customerId", customerId);
                    response.setContentType("application/json");
                    request.setAttribute("messageCustomer", messageCustomer);
                    response.setContentType("application/json");
                    request.setAttribute("addedApplications", addedApplications);

                } else {
                    messageCustomer = "Компания не является заказчиком";
                    response.setContentType("application/json");
                    request.setAttribute("messageCustomer", messageCustomer);
                }

            } else {
                messageCustomer = "Этот пользователь еще не добавил компанию-заказчик, a значит не может совершать действия с заявками";
                response.setContentType("application/json");
                request.setAttribute("messageCustomer", messageCustomer);
            }

            // передаем принятые заявки
            String messageCarrier = null;

            if (companyIsPresent) {

                Long carrierId = company.getCarrier().getId();

                if (carrierId != null && carrierId != 0) {

                    // выводим все заявки по Customer
                    List<Application> applyApplication = carrierService.showAllAplyAplication(carrierId);
                    response.setContentType("application/json");
                    request.setAttribute("applyApplication", applyApplication);

                } else {

                    messageCarrier = "Компания не является перевозчиком";
                    response.setContentType("application/json");
                    request.setAttribute("messageCarrier", messageCarrier);

                }

            } else {
                messageCarrier = "Этот пользователь еще не добавил компанию-перевозчика, a значит не может принимать заявки";
                response.setContentType("application/json");
                request.setAttribute("messageCarrier", messageCarrier);
            }


        }

        // передаем типы в форму для добавления заявки

        List<String> loadingTypes = Arrays.asList("top", "back", "with full removable curtains", "not specified", "without gates", "side", "with sides", "tail lift", "with the removal of transverse crossbars", "side with 2 sides ", " ramp ", " with the removal of racks");
//                ("верхняя", "задняя", "с полной растентовкой", "не указан", "без ворот", "боковая", "с бортами", "гидроборт", "со снятием поперечных перекладин", "боковая с 2-х сторон", "аппарели", "со снятием стоек");
        response.setContentType("application/json");
        request.setAttribute("loadingTypes", loadingTypes);
        request.getRequestDispatcher("/ftl/profile.ftl").forward(request, response);
    }

}
