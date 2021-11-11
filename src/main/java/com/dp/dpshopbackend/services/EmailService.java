package com.dp.dpshopbackend.services;

import com.dp.dpshopbackend.dto.CountryDto;
import com.dp.dpshopbackend.models.EmailMessage;
import com.dp.dpshopbackend.models.Mail;

import java.util.List;

public interface EmailService {

    void sendEmail(String from, String to);

    void sendEmailMessage(EmailMessage emailMessage);

    void sendSimpleMessage(Mail mail);

    Mail findById(Long id);

    List<Mail> findAll();

    List<Mail> findByOrderByIdDesc();

    void delete(Long id);


}
