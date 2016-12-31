package sheduler;


import events.Eventable;
import org.apache.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


/**
 * Created by Сергей on 01.12.2016.
 */
public class MessageJob {
    private static final Logger log = Logger.getLogger(MessageJob.class);

    private final static String username = "sergey.zherdev.94@gmail.com";
    private final static String password = "6303367google";
    private static Properties props;

    static void work(Eventable event) {
        props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        String to = event.getAddress();
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("Event manager");
            message.setText(event.getDescription());

            Transport.send(message);
            log.info("message to " + to + " sent");
        } catch (MessagingException e) {
            log.error("error in sending message to " + to);
        }
    }

}


