package com.test.COCONSULT.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailProject {
    @Autowired
    private JavaMailSender javaMailSender;
    public void send (String to, String subject, String body) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper;
        helper = new MimeMessageHelper(message, true); // true indicates
        helper.setSubject(subject);
        helper.setFrom("louaysghaier01@gmail.com");

        helper.setTo(to);
        helper.setText(body, true); // true indicates html
        // continue using helper object for more functionalities like adding attachments, etc.

        javaMailSender.send(message);
    }
    public void sendHtmlMailWithInlineImage(String to, String subject, String htmlBody, String imagePath) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlBody, true); // Le deuxième paramètre true indique que le texte est au format HTML

        // Charger l'image depuis le chemin spécifié
        ClassPathResource resource = new ClassPathResource(imagePath);
        helper.addInline("image", resource); // Ajouter l'image en tant que ressource en ligne (cid)

        javaMailSender.send(message);
    }

}
