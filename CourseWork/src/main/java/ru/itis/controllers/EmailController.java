package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.services.EmailService;
import ru.itis.models.User;
import ru.itis.services.UsersService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@Controller
public class EmailController {

    @Autowired
    EmailService emailService;

    @Autowired
    UsersService usersService;

    @PostMapping(value = "/sendEmailToCustomer")
    public ModelAndView sendEmailToCustomer(@CookieValue("auth") String authCookie, @RequestBody @RequestParam("to") String to, @RequestParam("body") String body) {
        System.out.println("EmailController email is called");
        User profileUser = null;

        if (authCookie != null) {
            if (usersService.isExistByCookie(authCookie)) {
                profileUser = usersService.getUserByUUID(authCookie);
            } else {
                System.out.println("Если нет куки auth, значит не заходил");
            }
        }
        assert profileUser != null;

        Map<String, Object> model = new HashMap<>();
        model.put(EmailService.FROM, "javastudy@mvc.app");
        model.put("subject", "Hello from " + profileUser.getFirstName() + " " + profileUser.getLastName() + "! This user respond you application, on LogistLog service");
        model.put("to", to);
        model.put("ccList", new ArrayList<>());
        model.put("bccList", new ArrayList<>());
        model.put("userName", profileUser.getFirstName() + " " + profileUser.getLastName());
        model.put("userEmail", profileUser.getEmail());
        model.put("userPhoneNumber", profileUser.getPhoneNumber());
//        model.put("urljavastudy", "javastudy.ru");
        model.put("message", body);
        boolean result = emailService.sendEmail("contact.vm", model);
        //use redirect or you will send email after every refresh page.
        return new ModelAndView("redirect:/home", "resultSending", result);
    }

    @PostMapping(value = "/sendEmailToCarrier")
    public ModelAndView sendEmailToCarrier(@CookieValue("auth") String authCookie, @RequestBody @RequestParam("to") String to, @RequestParam("subject") String subject, @RequestParam("body") String body) {
        System.out.println("EmailController email is called");

        User profileUser = null;

        if (authCookie != null) {
            if (usersService.isExistByCookie(authCookie)) {
                profileUser = usersService.getUserByUUID(authCookie);
            } else {
                System.out.println("Если нет куки auth, значит не заходил");
            }
        }
        assert profileUser != null;

        Map<String, Object> model = new HashMap<>();
        model.put(EmailService.FROM, "javastudy@mvc.app");
        model.put("subject", subject);
        model.put("to", to);
        model.put("ccList", new ArrayList<>());
        model.put("bccList", new ArrayList<>());
        model.put("userName", profileUser.getFirstName() + " " + profileUser.getLastName());
        model.put("userEmail", profileUser.getEmail());
        model.put("userPhoneNumber", profileUser.getPhoneNumber());
//        model.put("urljavastudy", "javastudy.ru");
        model.put("message", body);
        boolean result = emailService.sendEmail("contact.vm", model);
        //use redirect or you will send email after every refresh page.
        return new ModelAndView("redirect:/home", "resultSending", result);
    }

}
