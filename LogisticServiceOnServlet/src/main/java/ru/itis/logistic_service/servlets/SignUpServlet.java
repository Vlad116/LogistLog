package ru.itis.logistic_service.servlets;

import lombok.SneakyThrows;
import ru.itis.logistic_service.forms.UserSignUpForm;
import ru.itis.logistic_service.services.UsersService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SignUpServlet", value = "/signUp")
public class SignUpServlet extends HttpServlet {

    private UsersService usersService;

    @Override
    @SneakyThrows
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        usersService = (UsersService) context.getAttribute("usersService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
            greetingMessage = "Welcome, " + usersService.getUserByUUID(cookie).getLogin();
        }

        request.setAttribute("greetingMessage", greetingMessage);

        request.getRequestDispatcher("/ftl/signUp.ftl").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");

        UserSignUpForm userSignUpForm = UserSignUpForm.builder()
                .email(email)
                .login(login)
                .password(password)
                .firstName(firstName)
                .lastName(lastName)
                .address(address)
                .phoneNumber(phoneNumber)
                .build();

        System.out.println(userSignUpForm.toString());

        usersService.signUp(userSignUpForm);

        response.sendRedirect("/signIn");

    }
}
