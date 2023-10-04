package com.dp.dpshopbackend.services.impl;

import com.dp.dpshopbackend.dto.NewsletterDto;
import com.dp.dpshopbackend.exceptions.ResourceNotFoundException;
import com.dp.dpshopbackend.models.Newsletter;
import com.dp.dpshopbackend.repository.NewsletterRepository;
import com.dp.dpshopbackend.services.NewsletterService;
import lombok.extern.slf4j.Slf4j;
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
public class NewsletterServiceImpl implements NewsletterService {

    private final NewsletterRepository newsletterRepository;

    public NewsletterServiceImpl(NewsletterRepository newsletterRepository) {
        this.newsletterRepository = newsletterRepository;
    }

    @Override
    public NewsletterDto save(NewsletterDto newsletterDto) {
        newsletterDto.setDateInscription(new Date());
        newsletterDto.setActif(true);
        return NewsletterDto.fromEntityToDto(
                newsletterRepository.save(
                        NewsletterDto.fromDtoToEntity(newsletterDto)
                )
        );
    }

    @Override
    public BigDecimal countNumberOfNewsletters() {
        return newsletterRepository.countNumberOfNewsletters();
    }

    @Override
    public List<NewsletterDto> findAllActiveNewsletters() {
        return newsletterRepository.findAll().stream()
                .map(NewsletterDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteNewsletter(Long newsletterId) {
        if (newsletterId == null) {
            log.error("Newsletter Id is null");
            return;
        }
        Optional<Newsletter> optionalNewsletter = newsletterRepository.findById(newsletterId);
        Newsletter newsletter = optionalNewsletter.get();
        newsletter.setActif(false);
        newsletterRepository.save(newsletter);
    }
}
