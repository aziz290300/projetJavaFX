/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshop.gui;

import esprit.entite.User;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class MenuController implements Initializable {

    /**
     * Initializes the controller class.
     */
     private Parent fxml;
    @FXML
    private AnchorPane root;
    User UserConnected;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void hotel(ActionEvent event) {
    }

    @FXML
    private void Users(ActionEvent event) {
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("admin.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    

    @FXML
    private void voayage(ActionEvent event) {
    }

    @FXML
    private void reclamations(ActionEvent event) {
    }

    @FXML
    private void paiement(ActionEvent event) {
    }

    @FXML
    private void vol(ActionEvent event) {
    }

    @FXML
    private void restaurant(ActionEvent event) {
    }
    
}
