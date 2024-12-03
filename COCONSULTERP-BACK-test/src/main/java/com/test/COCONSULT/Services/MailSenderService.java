package com.test.COCONSULT.Services;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.test.COCONSULT.Entity.Candidat;
import com.test.COCONSULT.Entity.Entretien;
import com.test.COCONSULT.Interfaces.CandidatServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;


@Service
public class MailSenderService {
    @Autowired
    private JavaMailSender javaMailSender;

    public void send (String to, String subject, String body) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper;
        helper = new MimeMessageHelper(message, true); // true indicates
        helper.setSubject(subject);
        helper.setFrom("achrefhajsalem@gmail.com");

        helper.setTo(to);
        helper.setText(body, true); // true indicates html
        // continue using helper object for more functionalities like adding attachments, etc.

        javaMailSender.send(message);
    }


}
