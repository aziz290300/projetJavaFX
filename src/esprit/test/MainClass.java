/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.test;


import esprit.entite.Portefeuille;
import esprit.entite.User;
import esprit.services.PortefeuilleCrud;
import esprit.services.UserCrud;
import esprit.utils.Myconnection;
import java.sql.Date;

/**
 *
 * @author MSI
 */
public class MainClass {
    public static void main(String[] args) {
       Myconnection mc= new Myconnection();
       UserCrud uc = new UserCrud();
       ////////// test login///////////
       
        /* User loggedInUser = uc.login("john@example.com", "kjgkerjgre");

        if (loggedInUser != null) {
            System.out.println("Utilisateur connecté avec succès : " + loggedInUser.getNom() + " " + loggedInUser.getPrenom());
        } else {
            System.out.println("La connexion a échoué. Vérifiez l'e-mail et le mot de passe.");
        }
    }*/
       
       /////////ajout user/////////
       
       
      //User newUser = new User();
       // newUser.setNomUser("mghhg");
       // newUser.setPrenomUser("aighdjisa");
       
       // newUser.setNumTel("90456123");
        //newUser.setEmail("mariem@example.com");
       // newUser.setPwd("mariem");
       // newUser.setTypeUser("admin"); 
      //  uc.ajouterUser(newUser);
        
        ////////////// afficheruser/////////////////
      
        /*System.out.println(uc.afficherUser());*/
        ////////////////////////////
        
        /////////////modifieruser//////////////////
       
      /* int idUserToModify = 15; 
        String nomUser = "NouveauNom";
        String prenomUser = "NouveauPrenom";
        String pwd = "NouveauMotDePasse";
        String email = "nouveau@email.com";
        String numTel = "NouveauNumTel";
        String typeUser = "admin"; 
        
         uc.modifierUser(idUserToModify, nomUser, prenomUser, pwd, email, numTel, typeUser);*/
        
        //////////////////////////////////////////////////
        
        //////////////////supprimeruser///////////////////////

        /* int userIdToDelete =19; 
       uc.supprimerUser(userIdToDelete);*/
       /////////////////////////////////////////////////////
       
       ///////////////////chercheruser////////////////////////
       
        /* int idUser = 16; // Remplacez 1 par l'ID de l'utilisateur que vous souhaitez rechercher
       User foundUser = uc.chercherUser(idUser);

         //Vérification si l'utilisateur a été trouvé
        if (foundUser != null) {
    System.out.println("Utilisateur trouvé : ");
    System.out.println("Nom : " + foundUser.getNomUser());
    System.out.println("Prénom : " + foundUser.getPrenomUser());
    System.out.println("Email : " + foundUser.getEmail());
    
    System.out.println("Numéro de téléphone : " + foundUser.getNumTel());
    System.out.println("Rôle : " + foundUser.getTypeUser());
        } else {
            System.out.println("Aucun utilisateur trouvé avec l'ID spécifié.");
        }
    }*/
       ////////////////////////////////////////////////////
       
       
       
       
       
       
         
       //////////////////////////portefeuille///////////////////////////////////
     // PortefeuilleCrud pc = new PortefeuilleCrud();
     
     
    //////////////////ajouterportefeuill/////////////////////:
       //pc.ajouterportefeuille();
       //////////////////////////////////////////
       
       
       ///////////////////afficherportefeuille//////////////////////
      //System.out.println(pc.afficherportefeuille());
      ////////////////////////////////////////////////////////
      
      
       //////////////////////////modifierportefeuille//////////////////////////////////
      // int idportefeuilleToModify =6; // Remplacez par l'ID du portefeuille que vous souhaitez modifier
    //int nouvelIdUser = 1; // Remplacez par le nouvel ID de l'utilisateur
    //double nouveauSolde = 45666; 
  // pc.modifierportefeuille(idportefeuilleToModify, nouvelIdUser, nouveauSolde);
  /////////////////////////////////////////////////////////////////////////
  
  ////////////////////////////supprimerportefeuille//////////////////////////////////////////////
      //int idPortefeuilleASupprimer = 5; // Remplacez par l'ID du portefeuille que vous souhaitez supprimer
//pc.supprimerportefeuille(idPortefeuilleASupprimer);
//////////////////////////////////////////////////////////////
    
   
///////////////////////////////chercherportefeuille///////////////////////////////////////////
       
       /* int idPortefeuilleToSearch = 6; // Remplacez 1 par l'ID du portefeuille que vous souhaitez rechercher
        Portefeuille foundPortefeuille = pc.chercherPortefeuille(idPortefeuilleToSearch);

        // Vérification si le portefeuille a été trouvé
        if (foundPortefeuille != null) {
            System.out.println("Portefeuille trouvé : ");
            System.out.println("ID : " + foundPortefeuille.getIdportefeuille());
            System.out.println("UserID : " + foundPortefeuille.getUserid());
            System.out.println("Solde : " + foundPortefeuille.getSolde());
        } else {
            System.out.println("Aucun portefeuille trouvé avec l'ID spécifié.");
        }
    }*/   
       ////////////////////////////////////////////////////////////////::
    }
}





