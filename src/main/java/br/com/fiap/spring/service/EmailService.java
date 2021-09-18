package br.com.fiap.spring.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class EmailService {

    @Value("${email.gmail.smtp}")
    private String GMAIL_SMTP;

    @Value("${email.gmail.port}")
    private Integer GMAIL_PORT;

    @Value("${email.gmail.username}")
    private String GMAIL_USERNAME;

    @Value("${email.gmail.password}")
    private String GMAIL_PASSWD;

    public JavaMailSender getGmailJavaMailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(GMAIL_SMTP);
        mailSender.setPort(GMAIL_PORT);
        mailSender.setUsername(GMAIL_USERNAME);
        mailSender.setPassword(GMAIL_PASSWD);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

    public void sendGmailSimpleMail(String para, String assunto, String conteudo){
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(GMAIL_USERNAME);
        message.setTo(para);
        message.setSubject(assunto);
        message.setText(conteudo);

        getGmailJavaMailSender().send(message);
    }

}
