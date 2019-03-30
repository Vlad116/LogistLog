package ru.itis.logistic_service.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import ru.itis.logistic_service.dto.ApplicationWithInfoAboutCustomerDto;
import ru.itis.logistic_service.forms.ApplicationAddForm;
import ru.itis.logistic_service.forms.CompanyCreateForm;
import ru.itis.logistic_service.models.Company;
import ru.itis.logistic_service.models.Customer;
import ru.itis.logistic_service.models.User;
import ru.itis.logistic_service.services.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@WebServlet("/addapplication")
public class AddAplicationServlet extends HttpServlet {

    private CompanyService companyService;
    private CustomerService customerService;
    private ApplicationService applicationService;
    private UsersService userService;

    @Override
    @SneakyThrows
    public void init(ServletConfig config) {

        ServletContext context = config.getServletContext();

        applicationService = (ApplicationService) context.getAttribute("applicationsService");
        userService = (UsersService) context.getAttribute("usersService");
        companyService = (CompanyService) context.getAttribute("companiesService");
        customerService = (CustomerService) context.getAttribute("customersService");

    }

    @Override
    @SneakyThrows
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        Cookie[] cookies = request.getCookies();
        ObjectMapper objectMapper = new ObjectMapper();
        User profileUser = null;
        Long userId = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("auth")) {
                    if (userService.isExistByCookie(cookie.getValue())) {

                        profileUser = userService.getUserByUUID(cookie.getValue());
                        userId = profileUser.getId();

                    } else {
                        System.out.println("Если нет куки auth, значит не заходил");
                        response.sendRedirect("/signIn");
                        return;
                    }
                }
            }
        }

        assert profileUser != null;
        Long companyId = profileUser.getCompany().getId();
        Optional<Company> company;
        company = companyService.getCompanyById(companyId);

        if (company.isPresent()) {

            if (company.get().getCompany_role().equals("Customer")) {

                Customer customer = Customer.builder().id(0L).build();
                if (companyId != null && companyId != 0) {

                    customer = company.get().getCustomer();
                    System.out.println(customer);

                }

                if (customer != null && customer.getId() != 0) {
                    String loadingAddress = request.getParameter("loadingAddress");
                    String unloadingAddress = request.getParameter("unloadingAddress");
                    String loadingType = request.getParameter("loadingType"); // перечисление!?

                    Integer weight = 0;
                    if (!request.getParameter("weight").equals("")) {
                        weight = Integer.valueOf(request.getParameter("weight"));
                    }

                    Integer payment = 0;

                    if (!request.getParameter("payment").equals("")) {
                        payment = Integer.valueOf(request.getParameter("payment"));
                    }

                    ApplicationAddForm applicationAddForm = ApplicationAddForm.builder()
                            .loadingAddress(loadingAddress)
                            .unloadingAddress(unloadingAddress)
                            .loadingType(loadingType)
                            .weight(weight)
                            .payment(payment)
                            .customer(customer)
                            .build();

                    System.out.println(applicationAddForm.getLoadingType().toString());
                    customerService.addApplication(applicationAddForm);
                    List<ApplicationWithInfoAboutCustomerDto> applicationWithInfoAboutCustomerDto = applicationService.findByLoadingAndUnloadingAddress(loadingAddress, unloadingAddress);

                   response.setContentType("application/json");
                    response.getWriter().write(objectMapper.writeValueAsString(applicationWithInfoAboutCustomerDto));

                } else {
                    System.out.println("Вначале нужно создать компанию являющуюся заказчиком");
                }
                response.sendRedirect("/profile");
                return;
            } else {
                response.sendRedirect("/profile");
                return;
            }
        }
    }
}
