package com.dp.dpshopbackend.services;

import com.dp.dpshopbackend.dto.EmailDto;
import com.dp.dpshopbackend.dto.FournisseurDto;
import com.dp.dpshopbackend.models.EmailMessage;
import com.dp.dpshopbackend.models.Mail;
import org.springframework.mail.MailException;

import java.util.List;

public interface EmailService {

    void sendEmail(EmailDto emailDto) throws MailException;

    void sendMail(FournisseurDto fournisseurDto) throws MailException;

    void sendEmail(String from, String to);

    void sendEmailMessage(EmailMessage emailMessage);

    void sendSimpleMessage(Mail mail);

    Mail findById(Long id);

    List<Mail> findAll();

    List<Mail> findByOrderByIdDesc();

    void delete(Long id);


}
