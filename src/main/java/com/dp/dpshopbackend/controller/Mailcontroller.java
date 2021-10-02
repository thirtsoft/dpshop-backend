package com.dp.dpshopbackend.controller;

import com.dp.dpshopbackend.controller.api.MailApi;
import com.dp.dpshopbackend.models.Mail;
import com.dp.dpshopbackend.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class Mailcontroller implements MailApi {

    private final EmailService emailService;

    @Autowired
    public Mailcontroller(EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public void save(Mail mail) {
        emailService.sendSimpleMessage(mail);
    }

    @Override
    public ResponseEntity<Mail> findById(Long id) {
        return new ResponseEntity<>(emailService.findById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Mail>> findAll() {
        List<Mail> mailList = emailService.findAll();
        return new ResponseEntity<>(mailList, HttpStatus.OK);
    }

    @Override
    public void delete(Long id) {
        emailService.delete(id);
    }
}
