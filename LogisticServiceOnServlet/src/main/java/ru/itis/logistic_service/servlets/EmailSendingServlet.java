package ru.itis.logistic_service.servlets;

import ru.itis.logistic_service.elements.JavaEmail;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/sendEmail")
public class EmailSendingServlet extends HttpServlet {

    private String host;
    private String port;
    private String user;
    private String pass;
    private JavaEmail javaEmail;

    public void init() {

        // reads SMTP server setting from web.xml file
        ServletContext context = getServletContext();
        port = context.getInitParameter("port");
        host = context.getInitParameter("host");
        user = context.getInitParameter("user");
        pass = context.getInitParameter("pass");
        javaEmail = new JavaEmail();
        javaEmail.setMailServerProperties(port);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // reads form fields
        String recipient = request.getParameter("toEmail");
        String subject = request.getParameter("subject");
        String body = request.getParameter("body");

        String resultMessage = "";

        try {
            javaEmail.createEmailMessage(recipient, subject,
                    body);
            javaEmail.sendEmail(host,user,pass);
            resultMessage = "The e-mail was sent successfully";
        } catch (Exception ex) {
            ex.printStackTrace();
            resultMessage = "There were an error: " + ex.getMessage();
        } finally {
            request.setAttribute("Message", resultMessage);
            response.sendRedirect("/home");
    }

    }
}