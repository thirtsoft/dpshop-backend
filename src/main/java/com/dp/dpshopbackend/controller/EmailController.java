package com.dp.dpshopbackend.controller;

import com.dp.dpshopbackend.controller.api.EmailApi;
import com.dp.dpshopbackend.dto.EmailDto;
import com.dp.dpshopbackend.dto.FournisseurDto;
import com.dp.dpshopbackend.dto.NewsletterDto;
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

@CrossOrigin(origins = "https://soulbusinesse.com")
@RestController
public class EmailController implements EmailApi {

    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }


    @Override
    public ResponseEntity<EmailDto> sendEmail(EmailDto emailDto) throws MailException {
        emailDto.setCreateDate(new Date());
        emailService.sendEmailToManager(emailDto);
        return new ResponseEntity<>(emailDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<FournisseurDto> sendMailToFournisseur(FournisseurDto fournisseurDto) throws MailException {
        emailService.sendEmailToFournisseur(fournisseurDto);
        return new ResponseEntity<>(fournisseurDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<NewsletterDto> sendMailToCustomer(NewsletterDto newsletterDto) throws MailException {
        emailService.sendEmailToNewsletter(newsletterDto);
        return new ResponseEntity<>(newsletterDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<NewsletterDto> sendMailToAllCustomers(NewsletterDto newsletterDto) throws MailException {
        emailService.sendMailToAllNewsletters(newsletterDto);
        return new ResponseEntity<>(newsletterDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<EmailDto> sendEmailToManager(EmailDto emailDto) throws MailException {
        emailService.sendEmailToManager(emailDto);
        return new ResponseEntity<>(emailDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<EmailDto> getEmailById(Long id) {
        return new ResponseEntity<>(emailService.findById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<EmailDto>> getAll() {
        List<EmailDto> emailDtoList = emailService.findAll();
        return new ResponseEntity<>(emailDtoList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<EmailDto>> getAllNewsletterOrderByIdDesc() {
        List<EmailDto> emailDtoList = emailService.findByOrderByIdDesc();
        return new ResponseEntity<>(emailDtoList, HttpStatus.OK);
    }

    @Override
    public BigDecimal countNumberOfEmail() {
        return emailService.countNumberOfEmailInMonth();
    }

    @Override
    public void delete(Long id) {
        emailService.delete(id);
    }

    @Override
    public ResponseEntity<List<EmailDto>> getAllActiveEmails() {
        List<EmailDto> emailDtoList = emailService.findAllActiveEmails();
        return new ResponseEntity<>(emailDtoList, HttpStatus.OK);
    }

    @Override
    public void deleteEmail(Long idEmail) {
        emailService.deleteEmail(idEmail);
    }
}
