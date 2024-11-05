/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.entite;



/**
 *
 * @author MSI
 */
public class User {
    private int idUser;
    private String nomUser;
    private String prenomUser;
    private String pwd;
    private String email;
    private String numTel;
    private String typeUser;
    private Boolean etat;

   public User(String nomValue, String prenomValue, String pwdValue, String emailValue, String numtelValue, String typeUserValue, boolean etatValue) {
    this.nomUser = nomValue;
    this.prenomUser = prenomValue;
    this.pwd = pwdValue;
    this.email = emailValue;
    this.numTel = numtelValue;
    this.typeUser = typeUserValue;
    this.etat = etatValue;
}


    

   

    public void setEtat(Boolean etat) {
        this.etat = etat;
    }

    public Boolean getEtat() {
        return etat;
    }

    public User(Boolean etat) {
        this.etat = etat;
    }
    

    public User(int idUser) {
        this.idUser = idUser;
    }

    public User() {
    }
    

    public User(int idUser, String nomUser) {
        this.idUser = idUser;
        this.nomUser = nomUser;
    }

    public User(int idUser, String nomUser, String prenomUser) {
        this.idUser = idUser;
        this.nomUser = nomUser;
        this.prenomUser = prenomUser;
    }

    public User(int idUser, String nomUser, String prenomUser, String pwd, String email, String numTel, String typeUser, Boolean etat) {
        this.idUser = idUser;
        this.nomUser = nomUser;
        this.prenomUser = prenomUser;
        this.pwd = pwd;
        this.email = email;
        this.numTel = numTel;
        this.typeUser = typeUser;
        this.etat = etat;
    }

    public User(int idUser, String nomUser, String prenomUser, String pwd, String email, String numTel, String typeUser) {
        this.idUser = idUser;
        this.nomUser = nomUser;
        this.prenomUser = prenomUser;
        this.pwd = pwd;
        this.email = email;
        this.numTel = numTel;
        this.typeUser = typeUser;
        
    }
    public boolean isEtat() {
        return etat;
    }

    public User(String nomUser, String prenomUser, String pwd, String email, String numTel, String typeUser) {
        this.nomUser = nomUser;
        this.prenomUser = prenomUser;
        this.pwd = pwd;
        this.email = email;
        this.numTel = numTel;
        this.typeUser = typeUser;
        
    }

    

    public int getIdUser() {
        return idUser;
    }

    public String getNomUser() {
        return nomUser;
    }

    public String getPrenomUser() {
        return prenomUser;
    }

    public String getPwd() {
        return pwd;
    }

    public String getEmail() {
        return email;
    }

    public String getNumTel() {
        return numTel;
    }

    public String getTypeUser() {
        return typeUser;
    }

    

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setNomUser(String nomUser) {
        this.nomUser = nomUser;
    }

    public void setPrenomUser(String prenomUser) {
        this.prenomUser = prenomUser;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public void setTypeUser(String typeUser) {
        this.typeUser = typeUser;
    }

    @Override
    public String toString() {
        return "User{" + "idUser=" + idUser + ", nomUser=" + nomUser + ", prenomUser=" + prenomUser + ", pwd=" + pwd + ", email=" + email + ", numTel=" + numTel + ", typeUser=" + typeUser + ", etat=" + etat + '}';
    }

    

    
    
    
    
    
    
}