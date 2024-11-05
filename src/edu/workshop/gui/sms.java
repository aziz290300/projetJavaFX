/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshop.gui;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 *
 * @author MSI
 */
public class sms {
     public static final String ACCOUNT_SID = "AC43c4fd25383218041521686963b52ebe";
  public static final String AUTH_TOKEN = "2dcda0db5f5911181e2b1967e3ff82bf";

public static void sendSMS(String phoneNumber, String message) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        // Remplacez le numéro par celui vers lequel vous voulez envoyer le SMS
        PhoneNumber to = new PhoneNumber(phoneNumber);

        // Remplacez le numéro Twilio par votre numéro Twilio
        PhoneNumber from = new PhoneNumber("+17073354075\n" +
"");

        Message sms = Message.creator(to, from, message).create();

        // Vérifiez si l'envoi du SMS a réussi
        if (sms.getStatus() == Message.Status.SENT) {
            System.out.println("SMS envoyé avec succès.");
        } else {
            System.err.println("Échec de l'envoi du SMS.");
        }
    }

   
}
    
    

