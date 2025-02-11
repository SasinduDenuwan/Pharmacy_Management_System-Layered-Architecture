package com.assignment.final_course_work.util;


import javafx.scene.control.Alert;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendMail {
    public void sendEmailWithSendgrid(String to, String subject, String body) {
        final String FROM = "skwmorawaka1970@gmail.com";
        final String USER_NAME = "apikey";
        final String PASSWORD = "vqag hgfs odmi majg";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.sendgrid.net");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.sendgrid.net");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USER_NAME, PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);
            Transport.send(message);

            new Alert(Alert.AlertType.INFORMATION, "Email sent successfully!").show();
        } catch (MessagingException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to send email.").show();
        }
    }

    public void sendEmailWithGmail(String to, String subject, String messageBody) {
        final String FROM = "skwmorawaka1970@gmail.com";
        final String PASSWORD = "vqag hgfs odmi majg";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM, PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(messageBody);
            Transport.send(message);

            new Alert(Alert.AlertType.INFORMATION, "Email sent successfully!").show();
        } catch (MessagingException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to send email.").show();
        }
    }
}
