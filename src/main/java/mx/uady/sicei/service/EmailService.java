package mx.uady.sicei.service;

import java.io.IOException;
import java.security.GeneralSecurityException;

import javax.mail.MessagingException;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import mx.uady.sicei.util.EmailSending;

@Service
public class EmailService {

    @Async
    public void sendEmail(String email, String subject, String body){
        try {
            NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            EmailSending emailSending = new EmailSending(HTTP_TRANSPORT);
            emailSending.setGmailCredentials();
            emailSending.sendMessage(email, subject, body);
        } catch (GeneralSecurityException | IOException | MessagingException e) {
            e.printStackTrace();
        }
    }
    
}
