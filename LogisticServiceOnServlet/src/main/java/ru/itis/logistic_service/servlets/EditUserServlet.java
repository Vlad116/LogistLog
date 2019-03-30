package ru.itis.logistic_service.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import ru.itis.logistic_service.dto.UserDto;
import ru.itis.logistic_service.forms.UserEditForm;
import ru.itis.logistic_service.models.User;
import ru.itis.logistic_service.services.UsersService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("edituser")
public class EditUserServlet extends HttpServlet {

    private UsersService usersService;

    @Override
    @SneakyThrows
    public void init(ServletConfig config) {
        ServletContext context = config.getServletContext();
        usersService = (UsersService) context.getAttribute("usersService");
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
                    if (usersService.isExistByCookie(cookie.getValue())) {
                        cookieValue = cookie.getValue();
                        profileUser = usersService.getUserByUUID(cookieValue);
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

        usersService.editUser(userEditForm);

        UserDto profileUserDto = usersService.showUserByUUID(cookieValue);

        ObjectMapper objectMapper = new ObjectMapper();
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(profileUserDto));
//        response.sendRedirect("/profile");

    }
}
