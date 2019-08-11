package com.griddynamics.dms.service.impl;

import com.griddynamics.dms.model.employee.Employee;
import com.griddynamics.dms.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.nio.charset.Charset;
import java.util.List;
import java.util.ResourceBundle;

import static org.apache.commons.codec.CharEncoding.ISO_8859_1;
import static org.apache.commons.codec.CharEncoding.UTF_8;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;
    private ResourceBundle resourceBundle;


    @Value("${spring.mail.username}")
    private String dmsAssistantUsername;

    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender ) {
        this.javaMailSender = javaMailSender;
        this.resourceBundle = ResourceBundle.getBundle("strings");
    }

    @Override
    public void notifyAllEmployees(List<Employee> employees) {
        sendEmails(getEmails(employees),
                resourceBundle.getString("email_message_for_notify"),
                resourceBundle.getString("subjectNotify"));
    }

    @Override
    public void remindEmployeesWithoutInsurance(List<Employee> employees) {
        sendEmails(getEmails(employees),
                resourceBundle.getString("email_message_for_remind"),
                resourceBundle.getString("subjectRemind"));
    }

    private void sendEmails(String[] emails, String messageText, String subject) {
        if (emails.length != 0) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(dmsAssistantUsername);
            message.setSubject(encodeToUTF8(subject));
            message.setTo(emails);
            message.setText(encodeToUTF8(messageText));
            javaMailSender.send(message);
        }
    }

    private String encodeToUTF8(String str) {
        return new String(str.getBytes(Charset.forName(ISO_8859_1)), Charset.forName(UTF_8));
    }

    private String[] getEmails(List<Employee> employees)
    {
        String[] emails = new String[employees.size()];
        for (int i = 0; i < employees.size(); i++) {
            emails[i] = employees.get(i).getEmail();
        }
        return emails;
    }
}