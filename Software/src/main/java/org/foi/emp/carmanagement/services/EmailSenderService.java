package org.foi.emp.carmanagement.services;

import lombok.extern.slf4j.Slf4j;
import org.foi.emp.carmanagement.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Slf4j
@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender javaMailSender;
    private final String link = "<a href='http://localhost:8080/api/v1/users/%s/%s'>Aktiviraj </a>";

    public void sendSimpleEmail(final String toEmail, final String body, final String subject) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mailMessage = new MimeMessageHelper(mimeMessage, "utf-8");
            mailMessage.setText(link, true);
            mailMessage.setTo(toEmail);
            mailMessage.setFrom("kingdoko@gmail.com");
            mailMessage.setSubject(subject);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            log.error("Neuspjesno slanje!", e);
        }

    }

    public void sendActivationEmail(final String toEmail, final UserModel model, final String subject) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mailMessage = new MimeMessageHelper(mimeMessage, "utf-8");
            mailMessage.setText(String.format(link,model.getId(),model.getActivationCode()), true);
            mailMessage.setTo(toEmail);
            mailMessage.setFrom("kingdoko@gmail.com");
            mailMessage.setSubject(subject);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            log.error("Neuspjesno slanje!", e);
        }

    }
}
