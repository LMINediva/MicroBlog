package com.lc.model;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Date;
import java.util.Properties;

/**
 * @author DELL
 * @date 2021/12/31 21:33
 */
public class QQMailCarrier implements MailCarrier {
    private Properties props;

    public QQMailCarrier(Properties props) {
        this.props = props;
    }

    @Override
    public void sendTo(Account account, String subject, String content) {
        try {
            Session session = Session.getDefaultInstance(props,
                    new Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(
                                    props.getProperty("com.lc.username"),
                                    props.getProperty("com.lc.password"));
                        }
                    });
            Message message = new MimeMessage(session);
            message.setFrom(
                    new InternetAddress(
                            props.getProperty("com.lc.address")));
            message.setRecipient(Message.RecipientType.TO,
                    new InternetAddress(account.getEmail()));
            message.setSubject(subject);
            message.setSentDate(new Date());

            MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent(content, "text/html;charset=UTF-8");

            Multipart multiPart = new MimeMultipart();
            multiPart.addBodyPart(htmlPart);

            message.setContent(multiPart);
            Transport.send(message);
        } catch (AddressException e) {
            throw new RuntimeException(e);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
