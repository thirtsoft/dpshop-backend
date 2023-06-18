package com.dp.dpshopbackend.services;

import com.dp.dpshopbackend.dto.CountryDto;
import com.dp.dpshopbackend.dto.EmailDto;
import com.dp.dpshopbackend.dto.FournisseurDto;
import com.dp.dpshopbackend.dto.NewsletterDto;
import org.springframework.mail.MailException;

import java.math.BigDecimal;
import java.util.List;

public interface EmailService {

    void sendEmailToManager(EmailDto emailDto) throws MailException;

    void sendEmailToFournisseur(FournisseurDto fournisseurDto) throws MailException;

    void sendEmailToNewsletter(NewsletterDto newsletterDto) throws MailException;

    void sendMailToAllNewsletters(NewsletterDto newsletterDto);

    EmailDto findById(Long id);

    List<EmailDto> findAll();

    List<EmailDto> findByOrderByIdDesc();

    BigDecimal countNumberOfEmailInMonth();

    void delete(Long id);

    List<EmailDto> findAllActiveEmails();

    void deleteEmail(Long emailId);


}
