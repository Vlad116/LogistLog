package ru.itis.logistic_service.filters;

import lombok.SneakyThrows;
import ru.itis.logistic_service.models.User;
import ru.itis.logistic_service.services.UsersService;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter(urlPatterns = {"/home", "/profile"})

public class AuthFilter implements Filter {
    private UsersService userService;

    @Override
    @SneakyThrows
    public void init(FilterConfig filterConfig) throws ServletException {

        ServletContext context = filterConfig.getServletContext();
        userService = (UsersService) context.getAttribute("usersService");

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        Cookie cookies[] = request.getCookies();

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
                        chain.doFilter(request, response);

                    }
                }
            }
            response.sendRedirect("/signIn");
            return;
        }
        response.sendRedirect("/signIn");
        return;
    }

    @Override
    public void destroy() {

    }
}
