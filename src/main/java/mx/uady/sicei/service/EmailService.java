package mx.uady.sicei.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class EmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Async
    public void sendWelcome(String recepient, String userName) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(recepient);
        msg.setSubject("Welcome to insecurity " + userName);
        msg.setText("Haha php goes brrrr <br>");

        javaMailSender.send(msg);
        LOGGER.info("Mail enviado!");
    }
    
}
