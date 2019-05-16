package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.itis.forms.LoginForm;
import ru.itis.forms.UserSignUpForm;
import ru.itis.services.UsersService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    private UsersService usersService;

    @RequestMapping(value = "/signIn", method = RequestMethod.GET)
    public String getLoginPage (ModelMap modelMap, HttpServletRequest httpServletRequest) {
        String cookie = "";
        Cookie[] cookies = httpServletRequest.getCookies();

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
        modelMap.addAttribute("greetingMessage", greetingMessage);

        return "signIn";
    }

    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    public String login (LoginForm loginForm,  HttpServletResponse response) {

        Optional<String> cookieValueCandidate = usersService.signIn(loginForm);
        if (cookieValueCandidate.isPresent()) {
            Cookie auth = new Cookie("auth", cookieValueCandidate.get());
            auth.setMaxAge(60 * 60 * 24);
            response.addCookie(auth);
            return "redirect:/home";
        } else {
            return "redirect:/signIn";
        }

    }


    @RequestMapping(value = "/signUp", method = RequestMethod.GET)
    public String getSignUpPage(ModelMap modelMap, HttpServletRequest httpServletRequest) {
        // часть для хедера (приветствие)
        String cookie = "";
        Cookie[] cookies = httpServletRequest.getCookies();

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

        modelMap.addAttribute("greetingMessage", greetingMessage);

        return "signUp";
    }

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public String signUp(UserSignUpForm userSignUpForm) {
        usersService.signUp(userSignUpForm);
        return "redirect:/home";
    }

}
