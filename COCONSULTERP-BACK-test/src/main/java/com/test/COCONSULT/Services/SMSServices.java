package com.test.COCONSULT.Services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SMSServices {


   // @Value("${twilio.account.sid}")
    public static final String ACCOUNT_SID = "AC7b5c0d5b77cf55b16e60bad668cc7ac9";
    public static final String AUTH_TOKEN = "9b30025990b631b52d3ddfdc430359f0";


    //@Value("${twilio.auth.token}")
    //private String AUTH_TOKEN;

    public void sendSms(String numeroDestinataire, String messageBody) {
        try {
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            Message message = Message.creator(
                    new PhoneNumber("+21623524206"),
                    new PhoneNumber("+12532184720"), // Votre numéro Twilio
                    "this is insane").create();

            System.out.println("Message SID: " + message.getSid());
        } catch (Exception e) {
            System.out.println("Erreur lors de l'envoi du SMS : " + e.getMessage());

        }
    }
}