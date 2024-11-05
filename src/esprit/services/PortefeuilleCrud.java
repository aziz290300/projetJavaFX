package esprit.services;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import esprit.entite.Portefeuille;
import esprit.utils.Myconnection;

import esprit.utils.Myconnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MSI
 */
public class PortefeuilleCrud {
 public void ajouterPortefeuille(Portefeuille portefeuille) {
    try {
        // Récupérez la liste des `idUser` de la table `user` à des fins de débogage
        List<Integer> userIds = getUsersIdList();
        System.out.println("Liste des `idUser` dans la table `user`: " + userIds);

        // Vérifiez d'abord si l'`idUser` existe dans la table `user`
//        if (userExists(portefeuille.getidUser())) {
            String requete = "INSERT INTO portefeuille (idUser, solde) VALUES (?, ?)";
            PreparedStatement ps = new Myconnection().getcnx().prepareStatement(requete);
            ps.setInt(1, portefeuille.getidUser());
            ps.setDouble(2, portefeuille.getSolde());

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Portefeuille ajouté avec succès");
            } else {
                System.out.println("Erreur lors de l'ajout du portefeuille");
            }
        
    } catch (SQLException ex) {
        System.err.println("Erreur lors de l'ajout du portefeuille : " + ex.getMessage());
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

private List<Integer> getUsersIdList() {
    List<Integer> userIds = new ArrayList<>();
    String query = "SELECT idUser FROM user";
    try (Connection conn = new Myconnection().getcnx();
         PreparedStatement stmt = conn.prepareStatement(query);
         ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {
            int userId = rs.getInt("idUser");
            userIds.add(userId);
        }
    } catch (SQLException ex) {
        System.err.println("Erreur lors de la récupération de la liste des `idUser` : " + ex.getMessage());
    }
    return userIds;
}
public List<Portefeuille> readAllPortefeuille() {
    String requete = "SELECT * FROM portefeuille";
    List<Portefeuille> list = new ArrayList<>();

    try (Statement st = new Myconnection().getcnx().createStatement();
         ResultSet rs = st.executeQuery(requete)) {

        while (rs.next()) {
            Portefeuille portefeuille = new Portefeuille(
                rs.getInt("idPortefeuille"),
                rs.getInt("idUser"),
                rs.getDouble("solde")
            );
            list.add(portefeuille);
        }
    } catch (SQLException ex) {
        Logger.getLogger(PortefeuilleCrud.class.getName()).log(Level.SEVERE, null, ex);
    }

    return list;
}




    
    public List<Portefeuille> afficherportefeuille() {
    List<Portefeuille> myList = new ArrayList<>();

    try {
        String requete2 ="SELECT *FROM portefeuille";
        Statement st = new Myconnection().getcnx().createStatement();
        ResultSet re=st.executeQuery(requete2);
        while (re.next()) {
            Portefeuille p = new Portefeuille();
            
            p.setidUser(re.getInt("idUser"));
            p.setSolde(re.getInt("solde"));
            p.setIdportefeuille(re.getInt(1));
             myList.add(p);
        }
    } catch (SQLException ex) {
        System.err.println("Erreur lors de l'exécution de la requête : " + ex.getMessage());
    }
    
    return myList;
}
  
    
    public void modifierPortefeuille(Portefeuille p) {
    String requete = "UPDATE portefeuille SET idUser = ?, solde = ? WHERE idPortefeuille = ?";
    try {
        PreparedStatement pst = new Myconnection().getcnx().prepareStatement(requete);
        pst.setInt(1, p.getidUser());
        pst.setDouble(2, p.getSolde());
        pst.setInt(3, p.getIdportefeuille());

        pst.executeUpdate();
    } catch (SQLException ex) {
        Logger.getLogger(PortefeuilleCrud.class.getName()).log(Level.SEVERE, null, ex);
    }
}


    
    public void supprimerportefeuille(Portefeuille p) {
   String requete="delete from portefeuille where idPortefeuille ="+p.getIdportefeuille();
        try {
           
           Statement st = new Myconnection().getcnx().createStatement();
            st.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
}
    public Portefeuille chercherPortefeuille(int idportefeuille) {
        Portefeuille portefeuille = null;
        try {
            String requete = "SELECT * FROM portefeuille WHERE idportefeuille=?";
            PreparedStatement ps = new Myconnection().getcnx().prepareStatement(requete);
            ps.setInt(1, idportefeuille);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                portefeuille = new Portefeuille();
                portefeuille.setIdportefeuille(rs.getInt("idportefeuille"));
                portefeuille.setidUser(rs.getInt("idUser"));
                portefeuille.setSolde(rs.getDouble("solde"));
            } else {
                System.out.println("Aucun portefeuille trouvé avec l'ID spécifié");
            }
        } catch (SQLException ex) {
            System.err.println("Erreur lors de la recherche du portefeuille : " + ex.getMessage());
        }
        
        return portefeuille;
    
}
}
