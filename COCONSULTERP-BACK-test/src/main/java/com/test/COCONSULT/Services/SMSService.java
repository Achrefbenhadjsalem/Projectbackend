package com.test.COCONSULT.Services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;

@Service
public class SMSService {

    public static final String ACCOUNT_SID = "AC2cda61789a8672d673f7581c7a16e914";
    public static final String AUTH_TOKEN = "7f78f5afff70529ed87753900e88083c";

    public void sendSms(String numeroDestinataire, String messageBody) {
        try {
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            Message message = Message.creator(
                            new PhoneNumber("+21699025355"),
                            new PhoneNumber("+12512902845"), // Votre numéro Twilio
                            messageBody).create();

            System.out.println("Message SID: " + message.getSid());
        } catch (Exception e) {
            System.out.println("Erreur lors de l'envoi du SMS : " + e.getMessage());

        }
    }
   /* public static final String ACCOUNT_SID = "AC2cda61789a8672d673f7581c7a16e914";
    public static final String AUTH_TOKEN = "";

    public void sendSms(String numeroDestinataire, String messageBody) {
        try {
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            Message message = Message.creator(
                    new PhoneNumber("+21699025355"),
                    new PhoneNumber("+12512902845"), // Votre numéro Twilio
                    messageBody).create();

            System.out.println("Message SID: " + message.getSid());
        } catch (Exception e) {
            System.out.println("Erreur lors de l'envoi du SMS : " + e.getMessage());

        }
    }*/
}
