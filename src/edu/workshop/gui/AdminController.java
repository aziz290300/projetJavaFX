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
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class AdminController implements Initializable {

    @FXML
     private ChoiceBox<String> typeUser;
    private String []type={"Admin","Client"};
    @FXML
    private TextField prenom;
    @FXML
    private TextField numtel;
    @FXML
    private TextField email;
    @FXML
    private TextField nom;
     
    @FXML
    private TableView<User> table;
    @FXML
    private TableColumn<User, Integer> Iduser;
    @FXML
    private TableColumn<User, String> nomU;
    @FXML
    private TableColumn<User, String> prenomU;
    @FXML
    private TableColumn<User, String> numtelU;
    @FXML
    private TableColumn<User, String> emailU;
    
    @FXML
    private PasswordField pwd;
    @FXML
    private TextField idchercher;
    private TextField etat;
    @FXML
    private CheckBox etatCheckBox;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          typeUser.getItems().addAll(type);
          table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<User>() {
            @Override
            public void changed(ObservableValue<? extends User> observable, User oldValue, User newValue) {
                if (newValue != null) {
                    // Lorsqu'une ligne est sélectionnée, mettez à jour les champs de texte
                    nom.setText(newValue.getNomUser());
                    prenom.setText(newValue.getPrenomUser());
                    pwd.setText(newValue.getPwd());
                    email.setText(newValue.getEmail());
                    numtel.setText(newValue.getNumTel());
                    typeUser.setValue(newValue.getTypeUser());
                }
            }

             
        });
           UserCrud uc = new UserCrud();
             List<User> users=uc.readAll();
          ObservableList<User> userData = FXCollections.observableArrayList(users);
         table.setItems(userData);
          Iduser.setCellValueFactory(new PropertyValueFactory<>("idUser"));
        nomU.setCellValueFactory(new PropertyValueFactory<>("nomUser"));
        prenomU.setCellValueFactory(new PropertyValueFactory<>("prenomUser"));
        numtelU.setCellValueFactory(new PropertyValueFactory<>("numTel"));
        emailU.setCellValueFactory(new PropertyValueFactory<>("email"));
    }    

    @FXML
    private void ajoute(ActionEvent event) {
    String nomValue = nom.getText();
    String prenomValue = prenom.getText();
    String pwdValue = pwd.getText();
    String emailValue = email.getText();
    String numtelValue = numtel.getText();
    String typeUserValue = typeUser.getValue();
    
    // Récupérer la valeur de l'état à partir du CheckBox
    boolean etatValue = etatCheckBox.isSelected() ? true : false;

    if (nomValue.isEmpty() || prenomValue.isEmpty() || pwdValue.isEmpty() || emailValue.isEmpty() || numtelValue.isEmpty() || typeUserValue == null) {
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
    } else if (!isNumeric(numtelValue) || numtelValue.length() != 8) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Le numéro de téléphone doit être un entier de 8 chiffres.");
        alert.showAndWait();
    } else {
        UserCrud uc = new UserCrud();
        User u = new User(nomValue, prenomValue, pwdValue, emailValue, numtelValue, typeUserValue, etatValue);
        uc.ajouterUser(u);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Utilisateur ajouté avec succès !");
        alert.showAndWait();

        // Réinitialiser les champs après l'ajout
        nom.setText("");
        prenom.setText("");
        pwd.setText("");
        email.setText("");
        numtel.setText("");
        typeUser.getSelectionModel().clearSelection();
        etatCheckBox.setSelected(false);
    }
}

private boolean isValidEmail(String email) {
    // Utiliser une expression régulière pour vérifier si l'e-mail est au bon format
    String emailPattern = "^[A-Za-z0-9+_.-]+@(.+)$";
    return email.matches(emailPattern);
}

    

  @FXML
private void modifier(ActionEvent event) {
   User selectedUser = table.getSelectionModel().getSelectedItem();
if (selectedUser != null) {
    String nomUser = nom.getText();
    String prenomUser = prenom.getText();
    String pwdUser = pwd.getText();
    String emailUser = email.getText();
    String numTelUser = numtel.getText();
    String typeUserValue = typeUser.getValue();
    
    // Récupérer la valeur de l'état à partir du CheckBox
    boolean etatValue = etatCheckBox.isSelected();

    if (nomUser.isEmpty() || prenomUser.isEmpty() || pwdUser.isEmpty() || emailUser.isEmpty() || numTelUser.isEmpty() || typeUserValue == null) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs du formulaire.");
        alert.showAndWait();
    } else if (!isValidEmail(emailUser)) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("L'adresse e-mail n'est pas au format valide.");
        alert.showAndWait();
    } else if (!isNumeric(numTelUser) || numTelUser.length() != 8) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Le numéro de téléphone doit être un entier de 8 chiffres.");
        alert.showAndWait();
    } else {
        // Mettre à jour les données de l'utilisateur sélectionné
        selectedUser.setNomUser(nomUser);
        selectedUser.setPrenomUser(prenomUser);
        selectedUser.setPwd(pwdUser);
        selectedUser.setEmail(emailUser);
        selectedUser.setNumTel(numTelUser);
        selectedUser.setTypeUser(typeUserValue);
        selectedUser.setEtat(etatValue); // Mettre à jour l'état

        UserCrud us = new UserCrud();
        us.modifierUser(selectedUser);

        // Mettre à jour la table visuellement
        table.refresh();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Utilisateur modifié avec succès !");
        alert.showAndWait();
    }
} else {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur");
    alert.setHeaderText(null);
    alert.setContentText("Veuillez sélectionner un utilisateur à modifier.");
    alert.showAndWait();
}

}



private boolean isNumeric(String numtel) {
    // Utiliser une expression régulière pour vérifier si la chaîne est numérique
    return numtel.matches("\\d+");
}







    @FXML
    private void Supprimer(ActionEvent event) {
        
          if (table.getSelectionModel().getSelectedItem() != null) {
        // Récupérer les données de l'événement sélectionné
        User selectedUser = table.getSelectionModel().getSelectedItem();
        UserCrud uc = new UserCrud();
        uc.supprimerUser(selectedUser);
        
          }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succés");
        alert.setHeaderText(null);
        alert.setContentText("Utilisateur supprimé avec succès !");
        alert.showAndWait();
        
        Afficher(event);
    }

    @FXML
    private void Afficher(ActionEvent event) {
        UserCrud uc = new UserCrud();
         List<User> users=uc.readAll();
          ObservableList<User> userData = FXCollections.observableArrayList(users);
         table.setItems(userData);
          Iduser.setCellValueFactory(new PropertyValueFactory<>("idUser"));
        nomU.setCellValueFactory(new PropertyValueFactory<>("nomUser"));
        prenomU.setCellValueFactory(new PropertyValueFactory<>("prenomUser"));
        numtelU.setCellValueFactory(new PropertyValueFactory<>("numTel"));
        emailU.setCellValueFactory(new PropertyValueFactory<>("email"));
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
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

 @FXML
private void chercher(ActionEvent event) {
    // Récupérez l'ID entré dans le champ idchercher
    int idUserToSearch = Integer.parseInt(idchercher.getText());

    // Créez une instance de UserCrud pour effectuer la recherche
    UserCrud userCrud = new UserCrud();

    // Appelez la méthode chercherUser avec l'ID spécifié
    User user = userCrud.chercherUser(idUserToSearch);

    if (user != null) {
        // Si un utilisateur correspondant a été trouvé, affichez ses informations
        nom.setText(user.getNomUser());
        prenom.setText(user.getPrenomUser());
        pwd.setText(user.getPwd());
        email.setText(user.getEmail());
        numtel.setText(user.getNumTel());
        typeUser.setValue(user.getTypeUser());
    } else {
        // Si aucun utilisateur correspondant n'a été trouvé, affichez une boîte de dialogue d'alerte
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Aucun utilisateur trouvé avec l'ID spécifié.");
        alert.showAndWait();
    }
}


    
}
