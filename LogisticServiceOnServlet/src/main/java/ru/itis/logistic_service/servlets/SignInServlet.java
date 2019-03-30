package ru.itis.logistic_service.servlets;

import lombok.SneakyThrows;
import ru.itis.logistic_service.forms.LoginForm;
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
import java.util.Optional;

@WebServlet(name = "SignInServlet", value = "/signIn")
public class SignInServlet extends HttpServlet {

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

        request.getRequestDispatcher("/ftl/signIn.ftl").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        LoginForm loginForm = LoginForm.builder()
                .login(request.getParameter("email"))
                .password(request.getParameter("password"))
                .build();

        Optional<String> cookieValueCandidate = usersService.signIn(loginForm);
        if (cookieValueCandidate.isPresent()) {
            Cookie auth = new Cookie("auth", cookieValueCandidate.get());
            auth.setMaxAge(60 * 60 * 24);
            response.addCookie(auth);
            response.sendRedirect("/home");
        } else {
            response.sendRedirect("/signIn");
        }

    }
}
