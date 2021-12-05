package com.dp.dpshopbackend.controller;

import com.dp.dpshopbackend.controller.api.EmailApi;
import com.dp.dpshopbackend.dto.EmailDto;
import com.dp.dpshopbackend.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;
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
            emailDto.setCreateDate(new Date());
            emailService.sendEmailToManager(emailDto);
            return new ResponseEntity<>(emailDto, HttpStatus.OK);
        } catch (MailException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<EmailDto> sendMailToFournisseur(Long id, EmailDto emailDto) {
        try {
            emailDto.setCreateDate(new Date());
            emailService.sendEmailToFournisseur(id, emailDto);
            return new ResponseEntity<>(emailDto, HttpStatus.OK);
        } catch (MailException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<EmailDto> sendMailToCustomer(Long id, EmailDto emailDto) {
        try {
            emailDto.setCreateDate(new Date());
            emailService.sendEmailToNewsletter(id, emailDto);
            return new ResponseEntity<>(emailDto, HttpStatus.OK);
        } catch (MailException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<EmailDto> sendMailToAllCustomers(EmailDto emailDto) {
        try {
            emailService.sendMailToAllNewsletters(emailDto);
            return new ResponseEntity<>(emailDto, HttpStatus.OK);
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
    public ResponseEntity<EmailDto> getEmailById(Long id) {
        return ResponseEntity.ok(emailService.findById(id));
    }

    @Override
    public ResponseEntity<List<EmailDto>> getAll() {
        List<EmailDto> emailDtoList = emailService.findAll();
        return new ResponseEntity(emailDtoList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<EmailDto>> getAllNewsletterOrderByIdDesc() {
        List<EmailDto> emailDtoList = emailService.findByOrderByIdDesc();
        return new ResponseEntity(emailDtoList, HttpStatus.OK);
    }

    @Override
    public BigDecimal countNumberOfEmail() {
        return emailService.countNumberOfEmailInMonth();
    }

    @Override
    public void delete(Long id) {
        emailService.delete(id);
    }

}
