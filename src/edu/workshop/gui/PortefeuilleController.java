/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshop.gui;

import esprit.entite.Portefeuille;
import esprit.services.PortefeuilleCrud;
import esprit.utils.Myconnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class PortefeuilleController implements Initializable {

    @FXML
    private TableView<Portefeuille> table;
   
    @FXML
    private TableColumn<Portefeuille, Integer> idp;
    @FXML
    private TableColumn<Portefeuille, Integer> idu;
    @FXML
    private TableColumn<Portefeuille, Double> sld;
    @FXML
    private TextField solde;
    @FXML
    private TextField iduser;

    /**
     * Initializes the controller class.
     */
    @Override
public void initialize(URL url, ResourceBundle rb) {
    // Ici, vous pouvez initialiser le formulaire, par exemple, en désactivant les champs
    // ou en les laissant vides.
    iduser.setText("");
    solde.setText("");

    // Ajoutez un écouteur à la table pour détecter la sélection d'une ligne
    table.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
        if (newValue != null) {
            // Récupérez les coordonnées de la ligne sélectionnée
            int idUser = newValue.getidUser();
            double soldeValue = newValue.getSolde();

            // Remplissez les champs avec les coordonnées de la ligne sélectionnée
            iduser.setText(Integer.toString(idUser));
            solde.setText(Double.toString(soldeValue));
        }
    });
}


 @FXML
private void ajoute(ActionEvent event) {
    String soldeValue = solde.getText();
    String iduserValue = iduser.getText();

    if (soldeValue.isEmpty() || iduserValue.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs du formulaire.");
        alert.showAndWait();
    } else {
        try {
            Double soldeDouble = Double.parseDouble(soldeValue);
            Integer iduserInteger = Integer.parseInt(iduserValue);

            // Vérifiez ici si l'ID d'utilisateur existe dans la table user
           if (userExists(iduserInteger)) {
                PortefeuilleCrud pc = new PortefeuilleCrud();
                Portefeuille por =new Portefeuille();
                por.setidUser(iduserInteger);
                por.setSolde(soldeDouble);
                
                pc.ajouterPortefeuille(por);

                // Réinitialisez les champs après l'ajout
                solde.clear();
                iduser.clear();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText(null);
                alert.setContentText("Portefeuille ajouté avec succès !");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("L'ID de l'utilisateur n'existe pas.");
               alert.showAndWait();
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez entrer des valeurs numériques valides pour le solde et l'ID de l'utilisateur.");
            alert.showAndWait();
        }
    }
}
private boolean userExists(int idUser) {
    // Effectuez une vérification pour savoir si l'`idUser` existe dans la table `user`
    // Renvoyez true si l'`idUser` existe, sinon renvoyez false
    // Vous pouvez utiliser une requête SQL pour vérifier l'existence
    System.out.println("Vérification d'`idUser` : " + idUser); // Débogage

    String query = "SELECT COUNT(*) FROM user WHERE idUser = ?";
    try (Connection conn = new Myconnection().getcnx();
         PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setInt(1, idUser);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        }
    } catch (SQLException ex) {
        System.err.println("Erreur lors de la vérification de l'existence de l'utilisateur : " + ex.getMessage());
    }
    return false;
}





    @FXML
    private void modifier(ActionEvent event) {
        Portefeuille selectedPortefeuille = table.getSelectionModel().getSelectedItem();

    if (selectedPortefeuille != null) {
        String idUserValue = iduser.getText();
        String soldeValue = solde.getText();

        if (idUserValue.isEmpty() || soldeValue.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs du formulaire.");
            alert.showAndWait();
        } else {
            try {
                Integer idUserInteger = Integer.parseInt(idUserValue);
                Double soldeDouble = Double.parseDouble(soldeValue);

                // Mettre à jour les données du portefeuille sélectionné
                selectedPortefeuille.setidUser(idUserInteger);
                selectedPortefeuille.setSolde(soldeDouble);
                 if (userExists(idUserInteger)) {
                PortefeuilleCrud pc = new PortefeuilleCrud();
                Portefeuille por =new Portefeuille();
                por.setidUser(idUserInteger);
                por.setSolde(soldeDouble);
                

                
                pc.modifierPortefeuille(selectedPortefeuille);

                // Mettre à jour la table visuellement
                table.refresh();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText(null);
                alert.setContentText("Portefeuille modifier avec succès !");
                alert.showAndWait();
                 }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("L'ID de l'utilisateur n'existe pas.");
               alert.showAndWait();
            } }catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez entrer des valeurs numériques valides pour l'ID de l'utilisateur et le solde.");
                alert.showAndWait();
            }
        }
    } else {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner un portefeuille à modifier.");
        alert.showAndWait();
    }
    }

    @FXML
    private void Supprimer(ActionEvent event) {
         if (table.getSelectionModel().getSelectedItem() != null) {
    // Récupérer les données de l'événement sélectionné
    Portefeuille selectedPortefeuille = table.getSelectionModel().getSelectedItem();
    PortefeuilleCrud pc = new PortefeuilleCrud();
    pc.supprimerportefeuille(selectedPortefeuille);
}

Alert alert = new Alert(Alert.AlertType.INFORMATION);
alert.setTitle("Succès");
alert.setHeaderText(null);
alert.setContentText("Portefeuille supprimé avec succès !");
alert.showAndWait();

Afficher(event);

    }

    @FXML
    private void Afficher(ActionEvent event) {
           PortefeuilleCrud pc = new PortefeuilleCrud();
    List<Portefeuille> portefeuilles = pc.readAllPortefeuille();

    ObservableList<Portefeuille> portefeuilleData = FXCollections.observableArrayList(portefeuilles);
//    for(   Portefeuille l :portefeuilles)
//    {
//       idp.setText(String.valueOf(l.getIdportefeuille()) );
//       idu.setText(String.valueOf(l.getidUser()) );
//       sld.setText(String.valueOf(l.getSolde()) );
//           table.setItems(portefeuilleData);
//
//       
//    }
  table.setItems(portefeuilleData);
    idp.setCellValueFactory(new PropertyValueFactory<>("idPortefeuille"));
    idu.setCellValueFactory(new PropertyValueFactory<>("iduser "));
   sld.setCellValueFactory(new PropertyValueFactory<>("solde"));
        
    }
    
}
