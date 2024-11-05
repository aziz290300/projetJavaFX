/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshop.gui;

import esprit.entite.User;
import esprit.services.UserCrud;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class CreerController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField email;
    @FXML
    private TextField numtel;
    @FXML
    private ChoiceBox<String> typeUser;
     private String []type={"Admin","Client"};
         String path="";
     
    @FXML
    private PasswordField pwd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         typeUser.getItems().addAll(type);
        // TODO
    }    

    @FXML
   private void creer(ActionEvent event) {
   String nomValue = nom.getText();
    String prenomValue = prenom.getText();
    String pwdValue = pwd.getText();
    String emailValue = email.getText();
    String numtelValue = numtel.getText();
    String typeUserValue = typeUser.getValue();
    

    if (nomValue.isEmpty() || prenomValue.isEmpty() || pwdValue.isEmpty() || emailValue.isEmpty() || numtelValue.isEmpty() || typeUserValue == null ) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs du formulaire.");
        alert.showAndWait();
    } else if (!isValidEmail(emailValue)) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("L'adresse e-mail n'est pas au format valide.");
        alert.showAndWait();
    }
    UserCrud usercrud = new UserCrud();
 if (usercrud .isEmailUsed(email.getText())) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("L'adresse email est déjà utilisée par un autre utilisateur.");
        alert.showAndWait();
        return;
    }
    
    else if (!isNumeric(numtelValue) || numtelValue.length() != 8) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Le numéro de téléphone doit être un entier de 8 chiffres.");
        alert.showAndWait();
    }
    else {
        UserCrud uc = new UserCrud();
        User u = new User(nomValue, prenomValue, pwdValue, emailValue, numtelValue, typeUserValue);
        uc.creerUser(u);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("succes");
        alert.showAndWait();
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("user.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                  


     
            } catch (IOException ex) {
                Logger.getLogger(CreerController.class.getName()).log(Level.SEVERE, null, ex);
            }

        // Réinitialiser les champs après l'ajout
        nom.setText("");
        prenom.setText("");
        pwd.setText("");
        email.setText("");
        numtel.setText("");
        typeUser.getSelectionModel().clearSelection();
        
    }
}

private boolean isValidEmail(String email) {
    // Utiliser une expression régulière pour vérifier si l'e-mail est au bon format
    String emailPattern = "^[A-Za-z0-9+_.-]+@(.+)$";
    return email.matches(emailPattern);
}
private boolean isNumeric(String numtel) {
    // Utiliser une expression régulière pour vérifier si la chaîne est numérique
    return numtel.matches("\\d+");
}

    @FXML
    private void retour(ActionEvent event) {
         try {
                Parent page1 = FXMLLoader.load(getClass().getResource("user.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                  


     
            } catch (IOException ex) {
                Logger.getLogger(CreerController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
    }

}
