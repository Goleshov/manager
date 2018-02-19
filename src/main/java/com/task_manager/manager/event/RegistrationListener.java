package com.task_manager.manager.event;

import com.task_manager.manager.entity.User;
import com.task_manager.manager.service.VerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {
    private static final String SUB_URL = "/registrationConfirm/?token=";
    private static final String LOCALHOST_URL = "http://localhost:8080";

    @Autowired
    private VerificationTokenService tokenService;
    @Autowired
    private JavaMailSender mailSender;


    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        tokenService.createVerificationToken(user, token);
        String recipientAddress = user.getEmail();
        String subject = "RegistrationConfirmation";
        String confirmUrl = event.getAppUrl() + SUB_URL + token;
        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom("maksim.haliashou@gmail.com");
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText("Please, go to " + LOCALHOST_URL + confirmUrl + " to complete registration");
        mailSender.send(email);
    }
}
