package ru.itis.logistic_service.elements;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class JavaEmail {

    private Properties emailProperties;
    private Session mailSession;
    private MimeMessage emailMessage;

    public static void main(String args[]) throws AddressException,
            MessagingException {

        JavaEmail javaEmail = new JavaEmail();

        javaEmail.setMailServerProperties("587");
        javaEmail.createEmailMessage("vladalekseev9@yandex.ru", "Another Java Email test", "This is an email sent by JavaMail api");
        javaEmail.sendEmail("", "", "");
    }

    public void setMailServerProperties(String emailPort) {

//        String emailPort = "587";//gmail's smtp port

        emailProperties = System.getProperties();
        emailProperties.put("mail.smtp.port", emailPort);
        emailProperties.put("mail.smtp.auth", "true");
        emailProperties.put("mail.smtp.starttls.enable", "true");

    }

    public void createEmailMessage(String toEmail, String emailSubject, String emailBody) throws AddressException,
            MessagingException {

//        String emailSubject = "Java Email test";
//        String emailBody = "This is an email sent by JavaMail api.";

        mailSession = Session.getDefaultInstance(emailProperties, null);

        emailMessage = new MimeMessage(mailSession);
        emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));

        //        for (int i = 0; i < toEmails.length; i++) {
//            emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmails[i]));
//        }

        emailMessage.setSubject(emailSubject);
        emailMessage.setContent(emailBody, "text/html");//for a html email

        //emailMessage.setText(emailBody);// for a text email

    }

    public void sendEmail(String emailHost, String fromUser, String fromUserEmailPassword) throws AddressException, MessagingException {

        Transport transport = mailSession.getTransport("smtp");

        transport.connect(emailHost, fromUser, fromUserEmailPassword);
        transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
        transport.close();

        System.out.println("Email sent successfully.");
    }

}