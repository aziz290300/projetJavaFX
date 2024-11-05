/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.services;

import esprit.entite.User;
import esprit.utils.Myconnection;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class UserCrud {
 public void ajouterUser(User u) {
    try {
        String requete = "INSERT INTO user (nomUser, prenomUser, numTel, email, pwd, typeUser, etat) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = new Myconnection().getcnx().prepareStatement(requete);
        ps.setString(1, u.getNomUser());
        ps.setString(2, u.getPrenomUser());
        ps.setString(3, u.getNumTel());
        ps.setString(4, u.getEmail());
        ps.setString(5, u.getPwd());
        ps.setString(6, u.getTypeUser());

        // Ajouter la valeur de l'attribut "etat" comme paramètre
        ps.setBoolean(7, u.isEtat());

        int rowsAffected = ps.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Utilisateur ajouté avec succès");
        } else {
            System.out.println("Erreur lors de l'ajout de l'utilisateur");
        }
    } catch (SQLException ex) {
        System.err.println("Erreur lors de l'ajout de l'utilisateur : " + ex.getMessage());
    }
}







 public void setPassword(int idUser,String nouveauMotDePasse) {
         String req="update user set pwd=? where idUser=?";
        try {
           
           PreparedStatement pst = new Myconnection().getcnx().prepareStatement(req);
            pst.setString(1, nouveauMotDePasse);
        pst.setInt(2, idUser);
        pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
 private boolean sendPasswordResetEmail(String recipient, String newPassword) {
    String username = "votre_adresse_email@gmail.com"; // Remplacez par votre adresse e-mail
    String password = "votre_mot_de_passe"; // Remplacez par votre mot de passe

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

 public User readByEmail(String e) {
         String requete = "select * from user where email=?";
    User u1 = null;
    try {
         PreparedStatement pst = new Myconnection().getcnx().prepareStatement(requete);
        pst.setString(1, e);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            int id = rs.getInt("idUser");
            String nom = rs.getString("nomUser");
            String prenom = rs.getString("prenomUser");
            String mdp = rs.getString("pwd");
            String numtel = rs.getString("numtel");
            String type = rs.getString("typeUser");
           
            u1 = new User(id, nom, prenom, mdp, e, numtel, type);
        }
    } catch (SQLException ex) {
        Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
    }
    return u1;}


   
    public List<User> afficherUser() {
    List<User> myList = new ArrayList<>();

    try {
        String requete2 ="SELECT *FROM USER";
        Statement st = new Myconnection().getcnx().createStatement();
        ResultSet rs=st.executeQuery(requete2);
        while (rs.next()) {
            User u = new User();
            u.setIdUser(rs.getInt(1)); 
            u.setNomUser(rs.getNString("NomUser"));
            u.setPrenomUser(rs.getNString("PrenomUser"));
          
            u.setNumTel(rs.getNString("NumTel"));
            u.setEmail(rs.getNString("email"));
            u.setPwd(rs.getNString("pwd"));
            
            u.setTypeUser(rs.getNString("TypeUser"));
            myList.add(u);
        }
    } catch (SQLException ex) {
        System.err.println("Erreur lors de l'exécution de la requête : " + ex.getMessage());
    }
    
    return myList;
}
   public void modifierUser(User p) {
    String requete = "UPDATE user SET nomUser = ?, prenomUser = ?, pwd = ?, email = ?, numTel = ?, typeUser = ?, etat = ? WHERE idUser = ?";
    try {
        PreparedStatement pst = new Myconnection().getcnx().prepareStatement(requete);
        pst.setString(1, p.getNomUser());
        pst.setString(2, p.getPrenomUser());
        pst.setString(3, p.getPwd());
        pst.setString(4, p.getEmail());
        pst.setString(5, p.getNumTel());
        pst.setString(6, p.getTypeUser());
        pst.setBoolean(7, p.isEtat()); // Assurez-vous que votre classe User a une méthode isEtat() pour obtenir l'état de l'utilisateur.
        pst.setInt(8, p.getIdUser()); // Assurez-vous que votre classe User a une méthode getIdUser() pour obtenir l'ID de l'utilisateur.

        pst.executeUpdate();
    } catch (SQLException ex) {
        Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
    }
}
 public void creerUser(User u) {
    try {
        String requete = "INSERT INTO user (nomUser, prenomUser, numTel, email, pwd, typeUser) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = new Myconnection().getcnx().prepareStatement(requete);
        ps.setString(1, u.getNomUser());
        ps.setString(2, u.getPrenomUser());
        ps.setString(3, u.getNumTel());
        ps.setString(4, u.getEmail());

        // Utiliser la fonction hashPassword pour hacher le mot de passe
        String passwordHash = hashPassword(u.getPwd());
        ps.setString(5, passwordHash);

        ps.setString(6, u.getTypeUser());

        int rowsAffected = ps.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Compte créé avec succès");
        } else {
            System.out.println("Erreur lors de l'ajout de l'utilisateur");
        }
    } catch (SQLException ex) {
        System.err.println("Erreur lors de l'ajout de l'utilisateur : " + ex.getMessage());
    }
}

 
 public String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));

            // Convertir le tableau de bytes en une chaîne hexadécimale
            StringBuilder hexString = new StringBuilder(2 * encodedhash.length);
            try (Formatter formatter = new Formatter(hexString)) {
                for (byte b : encodedhash) {
                    formatter.format("%02x", b);
                }
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Erreur lors de la génération du hachage : " + e.getMessage());
            return null;
        }
    }

public void supprimerUser(User t) {
     String requete="delete from user where idUser ="+t.getIdUser();
        try {
           
           Statement st = new Myconnection().getcnx().createStatement();
            st.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
}

public List<User> readAll() {
          String requete="select * from user";
        List<User> list=new ArrayList<>();
        
        try {
            Statement st = new Myconnection().getcnx().createStatement();
            ResultSet rs=st.executeQuery(requete);
            while(rs.next()){
                User p=new User(rs.getInt("idUser"),
                        rs.getString("nomUser"),
                        rs.getString("prenomUser"),
                        rs.getString("pwd"),
                        rs.getString("email"),
                      
                        rs.getString("numtel"),
                        rs.getString("TypeUser"));
                list.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

   
    public User chercherUser(int idUser) {
        User user = null;
        try {
            String requete = "SELECT * FROM user WHERE idUser=?";
            PreparedStatement ps = new Myconnection().getcnx().prepareStatement(requete);
            ps.setInt(1, idUser);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                user = new User();
                user.setIdUser(rs.getInt("idUser"));
                user.setNomUser(rs.getNString("NomUser"));
                user.setPrenomUser(rs.getNString("PrenomUser"));
                user.setNumTel(rs.getNString("NumTel"));
                
                user.setPwd(rs.getNString("pwd"));
                user.setEmail(rs.getNString("email"));
                user.setTypeUser(rs.getNString("TypeUser"));
            } else {
                System.out.println("Aucun utilisateur trouvé avec l'ID spécifié");
            }
        } catch (SQLException ex) {
            System.err.println("Erreur lors de la recherche de l'utilisateur : " + ex.getMessage());
        }
        
        return user;
    }
    


public boolean isEmailUsed(String email) {
         String req="SELECT COUNT(*) AS count FROM user WHERE email = ?";
          try {
           
            PreparedStatement pst = new Myconnection().getcnx().prepareStatement(req);
            pst.setString(1, email);
         ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            int count = rs.getInt("count");
            return count > 0;
        }
    } catch (SQLException ex) {
        Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false;
    }

// Méthode pour récupérer le numéro de téléphone en fonction de l'adresse e-mail
public String getPhoneNumberByEmail(String email) {
    String phoneNumber = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    try {
        // Établir la connexion à la base de données (assurez-vous d'avoir configuré votre connexion à la base de données)
        connection = new Myconnection().getcnx();

        // Requête SQL pour récupérer le numéro de téléphone en fonction de l'adresse e-mail
        String query = "SELECT numtel FROM user WHERE email = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, email);

        resultSet = preparedStatement.executeQuery();

        // Si un enregistrement est trouvé, obtenez le numéro de téléphone
        if (resultSet.next()) {
            phoneNumber = resultSet.getString("numtel");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Fermer les ressources (ResultSet, PreparedStatement, Connection)
        try {
            if (resultSet != null) resultSet.close();
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    return phoneNumber;
}






 public User getUserByEmail(String email) throws SQLException {

User t = null;
    String query = "SELECT * FROM user WHERE email = ?";
    try (PreparedStatement stmt = new Myconnection().getcnx().prepareStatement(query)) {
        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            t = new User(
             rs.getInt("idUser"),
            rs.getString("nomUser"),
             rs.getString("prenomUser"),
            rs.getString("pwd"),
             rs.getString("email"),
            
           rs.getString("numtel"),
            rs.getString("typeUser"),
                    rs.getBoolean("etat")
            );
        }
    } catch (SQLException ex) {
        Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
    }
    return t;
}
 

}

  