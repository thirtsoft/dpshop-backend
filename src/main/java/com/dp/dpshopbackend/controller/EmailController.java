package com.dp.dpshopbackend.controller;

import com.dp.dpshopbackend.controller.api.EmailApi;
import com.dp.dpshopbackend.dto.EmailDto;
import com.dp.dpshopbackend.dto.FournisseurDto;
import com.dp.dpshopbackend.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class EmailController implements EmailApi {

    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }


    @Override
    public ResponseEntity<EmailDto> sendEmail(EmailDto emailDto) {
        try {
            emailService.sendEmail(emailDto);
            return new ResponseEntity<>(emailDto, HttpStatus.OK);
        } catch (MailException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<FournisseurDto> sendMail(FournisseurDto fournisseurDto) {
        try {
            emailService.sendMail(fournisseurDto);
            return new ResponseEntity<>(fournisseurDto, HttpStatus.OK);
        } catch (MailException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<EmailDto> sendEmailToManager(EmailDto emailDto) {
        try {
            emailService.sendEmailToManager(emailDto);
            return new ResponseEntity<EmailDto>(emailDto, HttpStatus.OK);
        } catch (MailException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<EmailDto> findById(Long id) {
        return ResponseEntity.ok(emailService.findById(id));
    }

    @Override
    public ResponseEntity<List<EmailDto>> findAll() {
        List<EmailDto> emailDtoList = emailService.findAll();
        return new ResponseEntity<>(emailDtoList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<EmailDto>> getAllClientsOrderByIdDesc() {
        List<EmailDto> emailDtoList = emailService.findByOrderByIdDesc();
        return new ResponseEntity<>(emailDtoList, HttpStatus.OK);
    }
}
