package com.dp.dpshopbackend.services.impl;

import com.dp.dpshopbackend.exceptions.ResourceNotFoundException;
import com.dp.dpshopbackend.models.EmailMessage;
import com.dp.dpshopbackend.models.Mail;
import com.dp.dpshopbackend.repository.EmailMessageRepository;
import com.dp.dpshopbackend.repository.MailRepository;
import com.dp.dpshopbackend.services.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class EmailServiceImpl implements EmailService {

    private final EmailMessageRepository emailMessageRepository;

    private final MailRepository mailRepository;

    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailServiceImpl(EmailMessageRepository emailMessageRepository,
                            MailRepository mailRepository,
                            JavaMailSender javaMailSender) {
        this.emailMessageRepository = emailMessageRepository;
        this.mailRepository = mailRepository;
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendEmail(String from, String to) {

    }

    @Override
    public void sendEmailMessage(EmailMessage emailMessage) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(emailMessage.getSubject());
        message.setText(emailMessage.getTo_address());
        message.setTo(emailMessage.getSubject());
        message.setFrom(emailMessage.getFrom());

        javaMailSender.send(message);

    }

    @Override
    public void sendSimpleMessage(Mail mail) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(mail.getSubject());
        message.setText(mail.getContent());
        message.setTo(mail.getTo());
        message.setFrom(mail.getFrom());

        javaMailSender.send(message);

        mailRepository.save(mail);

    }

    @Override
    public Mail findById(Long id) {
        if (id == null) {
            log.error("AddressLivraison Id is null");
            return null;
        }

        Optional<Mail> optionalMail = mailRepository.findById(id);

        return Optional.of(optionalMail.get()).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun Mail avec l'Id = " + id + "n'a été trouvé")
        );
    }

    @Override
    public List<Mail> findAll() {
        return mailRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        mailRepository.deleteById(id);
    }
}
