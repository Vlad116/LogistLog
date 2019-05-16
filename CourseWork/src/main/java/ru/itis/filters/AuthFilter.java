package ru.itis.filters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.models.Auth;
import ru.itis.models.User;
import ru.itis.repository.AuthRepository;
import ru.itis.repository.AuthRepositoryJdbcTemplateImpl;
import ru.itis.repository.UsersRepository;
import ru.itis.repository.UsersRepositoryJdbcTemplateImpl;
import ru.itis.services.AuthService;
import ru.itis.services.AuthServiceImpl;
import ru.itis.services.UsersService;
import ru.itis.services.UsersServiceImpl;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.Filter;

public class AuthFilter implements Filter {

    @Autowired
    private UsersService userService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setPassword("logistic");
        dataSource.setUsername("postgres");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/logistic_service");
        dataSource.setDriverClassName("org.postgresql.Driver");
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        UsersRepository usersRepository = new UsersRepositoryJdbcTemplateImpl(dataSource);
        AuthRepository authRepository = new AuthRepositoryJdbcTemplateImpl(dataSource);
        userService = new UsersServiceImpl(usersRepository,authRepository,passwordEncoder);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        Cookie[] cookies = request.getCookies();

        System.out.println(userService.getUserById(2L));

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("auth")) {
                    if (userService.isExistByCookie(cookie.getValue())) {
                        User user = userService.getUserByUUID(cookie.getValue());
//                        Long basketId;
//
//                        System.out.println(user.toString());
//
//                        basketId = basketService.getBasketIdByUserId(user.getId());
//                        System.out.println(basketId);
//
//                        if (basketId == null || basketId == 0) {
//                            basketId = basketService.createBasket(user.getId()).getId();
//                            System.out.println(basketId);
//                        }

//                        System.out.println(basketId);
//                        Cookie baskIdCookie = new Cookie("baskId", basketId.toString());
//                        response.addCookie(baskIdCookie);
                        filterChain.doFilter(request, response);

                    }
                }
            }
            response.sendRedirect("/signIn");
            return;
        }
        response.sendRedirect("/signIn");
    }

    @Override
    public void destroy() {

    }

}
