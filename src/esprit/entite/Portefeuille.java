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
public class Portefeuille {
   private int idportefeuille ;
   private int idUser ;
   private double solde ;

    public Portefeuille(int idportefeuille, int idUser, double solde) {
        this.idportefeuille = idportefeuille;
        this.idUser = idUser;
        this.solde = solde;
    }
    public Portefeuille(){
    }
    

    public Portefeuille(int idportefeuille, int idUser) {
        this.idportefeuille = idportefeuille;
        this.idUser = idUser;
    }

    public Portefeuille(double solde) {
        this.solde = solde;
    }

    public Portefeuille(int aInt, String string, String string0, String string1, String string2, String string3, String string4, String string5) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

    public int getIdportefeuille() {
        return idportefeuille;
    }

    public int getidUser() {
        return idUser;
    }

    public double getSolde() {
        return solde;
    }

    public void setIdportefeuille(int idportefeuille) {
        this.idportefeuille = idportefeuille;
    }

    public void setidUser(int userid) {
        this.idUser = userid;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    @Override
    public String toString() {
        return "Portefeuille{" + "idportefeuille=" + idportefeuille + ", userid=" + idUser + ", solde=" + solde + '}';
    }
   
    
}
