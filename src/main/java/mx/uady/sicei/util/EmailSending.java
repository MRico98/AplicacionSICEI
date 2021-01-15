package mx.uady.sicei.util;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.Base64;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Properties;

public class EmailSending {
    private static final String APPLICATION_NAME = "YOUR_APP_NAME";

    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    private final HttpTransport httpTransport;
    private GmailCredentials gmailCredentials;

    public EmailSending(HttpTransport httpTransport) {
        this.httpTransport = httpTransport;
    }

    public void setGmailCredentials() {
        this.gmailCredentials = GmailCredentials.builder()
                .userEmail("manuelmartinrico1998@gmail.com")
                .clientId("927783108655-5drrv493gtktk9eab221fgf18lvjiaph.apps.googleusercontent.com")
                .clientSecret("WfUdk8Am2K-8vuQs4GZmeb-w")
                .accessToken("ya29.a0AfH6SMCIGAEW0wa2mx1fBP-1Lqz3JrnUTtCENXepP4Eq50XyJ2pcWBWiqwSQa95uJUcIjFX43IRJ_-39X3myK5kSIG7aL3GL0b1BX7M6Kad6bEy0BYpFqGKizY_t7BLvzC3m5tKd8WFtsj30HsMWw_mzNNRyZ2DIBo5MYdwn_BU")
                .refreshToken("1//0f5fgtucy1sN7CgYIARAAGA8SNwF-L9Irm3V-rPqXLQOun4gX5FxsZgu47X11t5v3CPkrTqN7yC8_80InkeuxeW8v4Zoc3jrDbVk")
                .build();
    }


    public boolean sendMessage(String recipientAddress, String subject, String body) throws MessagingException,
            IOException {
        Message message = createMessageWithEmail(
                createEmail(recipientAddress, gmailCredentials.getUserEmail(), subject, body));

        return createGmail().users()
                .messages()
                .send(gmailCredentials.getUserEmail(), message)
                .execute()
                .getLabelIds().contains("SENT");
    }

    private Gmail createGmail() {
        Credential credential = authorize();
        return new Gmail.Builder(httpTransport, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    private MimeMessage createEmail(String to, String from, String subject, String bodyText) throws MessagingException {
        MimeMessage email = new MimeMessage(Session.getDefaultInstance(new Properties(), null));
        email.setFrom(new InternetAddress(from));
        email.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(to));
        email.setSubject(subject);
        email.setText(bodyText);
        return email;
    }

    private Message createMessageWithEmail(MimeMessage emailContent) throws MessagingException, IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        emailContent.writeTo(buffer);

        return new Message()
                .setRaw(Base64.encodeBase64URLSafeString(buffer.toByteArray()));
    }

    private Credential authorize() {
        return new GoogleCredential.Builder()
                .setTransport(httpTransport)
                .setJsonFactory(JSON_FACTORY)
                .setClientSecrets(gmailCredentials.getClientId(), gmailCredentials.getClientSecret())
                .build()
                .setAccessToken(gmailCredentials.getAccessToken())
                .setRefreshToken(gmailCredentials.getRefreshToken());
    }
}