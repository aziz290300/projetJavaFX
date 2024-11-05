/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshop.gui;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author MSI
 */
public class mailling {
    private boolean sendPasswordResetEmail(String recipient, String newPassword) {
    String username = "aziz.abidi@esprit.tn"; // Remplacez par votre adresse e-mail
    String password = "223JMT4659"; // Remplacez par votre mot de passe

    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", "smtp.gmail.com"); // Serveur SMTP de Gmail (à adapter si nécessaire)
    props.put("mail.smtp.port", "587"); // Port SMTP de Gmail (à adapter si nécessaire)

   Session session = Session.getInstance(props, new Authenticator() {
    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
    }
});


    try {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
        message.setSubject("Réinitialisation du mot de passe");
        message.setText("Votre nouveau mot de passe est : " + newPassword);

        Transport.send(message);
        return true;
    } catch (MessagingException e) {
        return false;
    }
}
    
}
