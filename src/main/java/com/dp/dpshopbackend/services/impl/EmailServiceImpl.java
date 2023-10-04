package com.dp.dpshopbackend.services.impl;

import com.dp.dpshopbackend.dto.EmailDto;
import com.dp.dpshopbackend.dto.FournisseurDto;
import com.dp.dpshopbackend.dto.NewsletterDto;
import com.dp.dpshopbackend.exceptions.ResourceNotFoundException;
import com.dp.dpshopbackend.models.Country;
import com.dp.dpshopbackend.models.Email;
import com.dp.dpshopbackend.models.Fournisseur;
import com.dp.dpshopbackend.repository.EmailRepository;
import com.dp.dpshopbackend.services.EmailService;
import com.dp.dpshopbackend.services.FournisseurService;
import com.dp.dpshopbackend.services.NewsletterService;
import com.dp.dpshopbackend.utils.EmailConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class EmailServiceImpl implements EmailService {

    private final EmailRepository emailRepository;

    private final JavaMailSender javaMailSender;

    private final FournisseurService fournisseurService;

    private final NewsletterService newsletterService;

    public EmailServiceImpl(EmailRepository emailRepository,
                            JavaMailSender javaMailSender,
                            FournisseurService fournisseurService,
                            NewsletterService newsletterService) {
        this.emailRepository = emailRepository;
        this.javaMailSender = javaMailSender;
        this.fournisseurService = fournisseurService;
        this.newsletterService = newsletterService;
    }

    @Override
    public void sendEmailToManager(EmailDto emailDto) throws MailException {
        StringBuilder sb = new StringBuilder();
        sb.append("Nom : " + emailDto.getCustomerName()).append(System.lineSeparator());
        sb.append("\n Subject : " + emailDto.getSubject());
        sb.append("\n Message : " + emailDto.getMessage());

        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setTo(EmailConstants.to);
        mail.setFrom(emailDto.getRecipient());
        mail.setSubject(emailDto.getSubject());
        mail.setText(emailDto.getMessage());

        emailDto.setCreateDate(new Date());
        emailDto.setCustomerName(emailDto.getCustomerName());
        emailDto.setActif(true);

        System.out.println(emailDto);

        javaMailSender.send(mail);

        EmailDto.fromEntityToDto(
                emailRepository.save(
                        EmailDto.fromDtoToEntity(emailDto)
                )
        );
    }

    @Override
    public void sendEmailToFournisseur(FournisseurDto fournisseurDto) throws MailException {

        StringBuilder sb = new StringBuilder();
        sb.append("Nom : " + EmailConstants.managerName).append(System.lineSeparator());
        sb.append("\n Subject : " + fournisseurDto.getSubject());
        sb.append("\n Message : " + fournisseurDto.getMessage());

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(fournisseurDto.getEmail());
        mail.setFrom(EmailConstants.from);
        mail.setSubject(fournisseurDto.getSubject());
        mail.setText(fournisseurDto.getMessage());


        javaMailSender.send(mail);


    }

    @Override
    public void sendEmailToNewsletter(NewsletterDto newsletterDto) throws MailException {

        StringBuilder sb = new StringBuilder();
        sb.append("Nom : " + EmailConstants.managerName).append(System.lineSeparator());
        sb.append("\n Subject : " + newsletterDto.getSubject());
        sb.append("\n Message : " + newsletterDto.getMessage());

        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setTo(newsletterDto.getCustomerEmail());
        mail.setFrom(EmailConstants.from);
        mail.setSubject(newsletterDto.getSubject());
        mail.setText(newsletterDto.getMessage());

        javaMailSender.send(mail);


    }

    @Override
    public void sendMailToAllNewsletters(NewsletterDto newsletterDto) {

        StringBuilder sb = new StringBuilder();
        sb.append("Nom : " + EmailConstants.managerName).append(System.lineSeparator());
        sb.append("\n Subject : " + newsletterDto.getSubject());
        sb.append("\n Message : " + newsletterDto.getMessage());

        List<NewsletterDto> newsletterDtos = newsletterService.findAllActiveNewsletters();

        SimpleMailMessage mail = new SimpleMailMessage();

        for (int i = 0; i < newsletterDtos.size(); i++) {
            NewsletterDto newsletterDtoResult = newsletterDtos.get(i);
            mail.setTo(newsletterDtoResult.getCustomerEmail());
            mail.setSubject(newsletterDtoResult.getSubject());
            mail.setText(newsletterDtoResult.getMessage());
            mail.setFrom(EmailConstants.from);
        }

        javaMailSender.send(mail);
    }

    @Override
    public EmailDto findById(Long id) {
        if (id == null) {
            log.error("Notification Id is null");
            return null;
        }

        Optional<Email> optionalEmail = emailRepository.findById(id);

        return Optional.of(EmailDto.fromEntityToDto(optionalEmail.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun Email avec l'Id = " + id + "n'a été trouvé")
        );
    }

    @Override
    public List<EmailDto> findAll() {
        return emailRepository.findAll().stream()
                .map(EmailDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmailDto> findByOrderByIdDesc() {
        return emailRepository.findByOrderByIdDesc().stream()
                .map(EmailDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public BigDecimal countNumberOfEmailInMonth() {
        return emailRepository.countNumberOfEmail();
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Email Id is null");
            return;
        }

        emailRepository.deleteById(id);
    }

    @Override
    public List<EmailDto> findAllActiveEmails() {
        return emailRepository.findAll().stream()
                .map(EmailDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteEmail(Long emailId) {
        if (emailId == null) {
            log.error("Country Id is null");
            return;
        }
        Optional<Email> optionalEmail = emailRepository.findById(emailId);
        Email email = optionalEmail.get();
        email.setActif(false);
        emailRepository.save(email);
    }
}
