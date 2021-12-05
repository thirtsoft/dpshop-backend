package com.dp.dpshopbackend.services;

import com.dp.dpshopbackend.dto.EmailDto;
import org.springframework.mail.MailException;

import java.math.BigDecimal;
import java.util.List;

public interface EmailService {

    void sendEmailToManager(EmailDto emailDto) throws MailException;

    void sendEmailToFournisseur(EmailDto emailDto) throws MailException;

    void sendEmailToNewsletter(EmailDto emailDto) throws MailException;

    void sendMailToAllNewsletters(EmailDto emailDto);

    EmailDto findById(Long id);

    List<EmailDto> findAll();

    List<EmailDto> findByOrderByIdDesc();

    BigDecimal countNumberOfEmailInMonth();

    void delete(Long id);


}
