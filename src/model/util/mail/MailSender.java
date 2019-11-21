package model.util.mail;

import com.sun.mail.smtp.SMTPTransport;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class MailSender {

    public static boolean send(final String title, final String text, final String toAddress) {
        boolean isSuccess;
        final String fromAddress = "no.reply.mydrive2@gmail.com";
        final String password = "2evirdym";
        try {
            final Properties props = System.getProperties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            final Session session = Session.getInstance(props);
            final Message msg = new MimeMessage(session);

            msg.setFrom(new InternetAddress(fromAddress));
            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(toAddress, false));
            msg.setSubject(title);
            msg.setText(text);
            msg.setSentDate(new Date());

            try (final SMTPTransport t = (SMTPTransport) session.getTransport("smtp")) {
                t.connect("smtp.gmail.com", fromAddress, password);
                t.sendMessage(msg, msg.getAllRecipients());
            }
            isSuccess = true;
        } catch (final MessagingException e) {
            isSuccess = false;
            e.printStackTrace();
        }
        return isSuccess;
    }
}
