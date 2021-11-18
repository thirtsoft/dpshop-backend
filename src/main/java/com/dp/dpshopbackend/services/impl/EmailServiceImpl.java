package com.dp.dpshopbackend.services.impl;

import com.dp.dpshopbackend.dto.ClientDto;
import com.dp.dpshopbackend.dto.EmailDto;
import com.dp.dpshopbackend.dto.FournisseurDto;
import com.dp.dpshopbackend.exceptions.ResourceNotFoundException;
import com.dp.dpshopbackend.models.EmailMessage;
import com.dp.dpshopbackend.models.Mail;
import com.dp.dpshopbackend.repository.EmailMessageRepository;
import com.dp.dpshopbackend.repository.EmailRepository;
import com.dp.dpshopbackend.repository.MailRepository;
import com.dp.dpshopbackend.services.EmailService;
import com.dp.dpshopbackend.utils.EmailConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
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

    private final EmailRepository emailRepository;

    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailServiceImpl(EmailMessageRepository emailMessageRepository,
                            MailRepository mailRepository,
                            JavaMailSender javaMailSender,
                            EmailRepository emailRepository) {
        this.emailMessageRepository = emailMessageRepository;
        this.mailRepository = mailRepository;
        this.emailRepository = emailRepository;
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendEmail(EmailDto emailDto) throws MailException {
        StringBuilder sb = new StringBuilder();
        sb.append("Nom : " + EmailConstants.name).append(System.lineSeparator());
        sb.append("\n Message: " + emailDto.getMessage());

        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setTo(emailDto.getFournisseurDto().getEmail());
        mail.setFrom(EmailConstants.from);
        mail.setSubject(emailDto.getSubject());
        mail.setText(emailDto.getMessage());


        System.out.println(emailDto);

        javaMailSender.send(mail);

        EmailDto.fromEntityToDto(
                emailRepository.save(
                        EmailDto.fromDtoToEntity(emailDto)
                )
        );

    }

    @Override
    public void sendMail(FournisseurDto fournisseurDto) throws MailException {
        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setTo(fournisseurDto.getEmail());
        mail.setFrom(EmailConstants.from);
        mail.setSubject(fournisseurDto.getSubject());
        mail.setText(fournisseurDto.getMessage());


        System.out.println(fournisseurDto);

        javaMailSender.send(mail);
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
    public List<Mail> findByOrderByIdDesc() {
        return mailRepository.findByOrderByIdDesc();
    }

    @Override
    public void delete(Long id) {
        mailRepository.deleteById(id);
    }
}
