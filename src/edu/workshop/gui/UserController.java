/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshop.gui;




import static edu.workshop.gui.sms.sendSMS;

import esprit.entite.User;
import esprit.services.UserCrud;
import java.io.IOException;
import java.net.URL;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Authenticator;  // Ajout de cette ligne pour corriger l'erreur d'incompatibilité de types
import javax.mail.PasswordAuthentication;  // Ajout de cette ligne pour corriger l'erreur d'incompatibilité de types
import javax.mail.Transport;




/**
 * FXML Controller class
 *
 * @author MSI
 */
public class UserController implements Initializable {

    @FXML
    private TextField email;
    @FXML
    private PasswordField pwd;
    private Connection connection;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
  
 private void login(ActionEvent event) throws SQLException, IOException {
        String e = email.getText();
        String mdp = pwd.getText();

        if (e == null || e.isEmpty() || mdp == null || mdp.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez saisir l'email et le mot de passe.");
            alert.showAndWait();
            return;
        }

        UserCrud userCrud = new UserCrud();
        User utilisateur = userCrud.getUserByEmail(e);

        if (utilisateur == null || !mdp.equals(utilisateur.getPwd())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Email ou mot de passe incorrect.");
            alert.showAndWait();
            return;
        }

        if (!utilisateur.isEtat()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Utilisateur bloqué");
            alert.setHeaderText(null);
            alert.setContentText("Votre compte est bloqué. Veuillez contacter l'administrateur.");
            alert.showAndWait();
            return;
        }

        if ("Admin".equals(utilisateur.getTypeUser())) {
            // Utilisez la fonction getPhoneNumberByEmail pour obtenir le numéro de téléphone de l'administrateur
            String adminPhoneNumber = userCrud.getPhoneNumberByEmail(e);

            if (adminPhoneNumber != null) {
                // Envoi d'un SMS à l'administrateur
                String message = "Admin, vous êtes maintenant connecté!";
                sms.sendSMS(adminPhoneNumber, message);
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText("Bienvenue Admin");
            alert.setContentText("Vous êtes maintenant connecté!Un SMS a été envoyé.");
            alert.showAndWait();

            // Chargement de la page admin
            Parent page1 = FXMLLoader.load(getClass().getResource("menu.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } else { // CLIENT
            // Obtenez le numéro de téléphone depuis la base de données
            String phoneNumber = userCrud.getPhoneNumberByEmail(e);

            if (phoneNumber != null) {
                // Envoyez le message SMS au client
                String message = "Bonjour Mr/Mme, votre compte est ouvert maintenant.";
                sms.sendSMS(phoneNumber, message);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succès");
                alert.setHeaderText(null);
                alert.setContentText("Vous êtes maintenant connecté! Un SMS a été envoyé.");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Le numéro de téléphone n'est pas disponible.");
                alert.showAndWait();
            }

            // Chargement de la page client
            Parent page1 = FXMLLoader.load(getClass().getResource("frontclient.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }



    @FXML
   

private void pwd(ActionEvent event) throws SQLException {
    String e = email.getText();
    if (e.isEmpty()) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Attention");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez saisir votre adresse e-mail.");
        alert.showAndWait();
        return;
    }

    UserCrud usercrud = new UserCrud();
    User utilisateur = usercrud.getUserByEmail(e);
    if (utilisateur == null) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Attention");
        alert.setHeaderText(null);
        alert.setContentText("Cette adresse e-mail n'existe pas.");
        alert.showAndWait();
        return;
    }

    // Génération d'un nouveau mot de passe
    String newPass = generateRandomPassword(8); // Vous pouvez personnaliser la longueur du mot de passe

    // Envoi du nouveau mot de passe par e-mail
    boolean emailSent =sendPasswordResetEmail(utilisateur.getEmail(), newPass);

    if (emailSent) {
        // Mise à jour du mot de passe dans la base de données (vous devrez implémenter cette fonctionnalité)
         usercrud.setPassword(utilisateur.getIdUser(), newPass);

        // Affichage d'un message de succès
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setHeaderText(null);
        alert.setContentText("Nouveau mot de passe envoyé sur votre e-mail. Merci de vérifier votre boîte de réception.");
        alert.showAndWait();
    } else {
        // En cas d'échec de l'envoi de l'e-mail, afficher un message d'erreur
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Une erreur s'est produite lors de l'envoi du nouveau mot de passe. Veuillez réessayer plus tard.");
        alert.showAndWait();
    }
}
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

// Méthode pour envoyer un e-mail

private String generateRandomPassword(int length) {
    // Caractères possibles pour le mot de passe
    String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+-=[]{}|;:,.<>?";

    SecureRandom random = new SecureRandom();
    StringBuilder password = new StringBuilder();

    for (int i = 0; i < length; i++) {
        int randomIndex = random.nextInt(characters.length());
        password.append(characters.charAt(randomIndex));
    }

    return password.toString();
}

    

    @FXML
    private void Gotoacoount(ActionEvent event) {
         try {
            Parent page1 = FXMLLoader.load(getClass().getResource("creer.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

   
    
}
