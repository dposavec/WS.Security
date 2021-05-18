package org.foi.emp.carmanagement.controllers;

import org.foi.emp.carmanagement.services.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class SpringEmailClient {

    @Autowired
    private EmailSenderService emailSenderService;

    @EventListener(ApplicationReadyEvent.class)
    public void triggerMail() {
        emailSenderService.sendSimpleEmail("kingdoko@gmail.com", "OVO JE PORUKA", "Ovo je subjekt");
    }
}
