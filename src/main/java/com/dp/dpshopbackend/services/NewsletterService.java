package com.dp.dpshopbackend.services;

import com.dp.dpshopbackend.dto.LigneCommandeDto;
import com.dp.dpshopbackend.dto.NewsletterDto;

import java.math.BigDecimal;
import java.util.List;

public interface NewsletterService {

    NewsletterDto save(NewsletterDto newsletterDto);

    NewsletterDto update(Long id, NewsletterDto newsletterDto);

    NewsletterDto findById(Long id);

    List<NewsletterDto> findAll();

    List<NewsletterDto> findByOrderByIdDesc();

    BigDecimal countNumberOfNewsletters();

    void delete(Long id);

    List<NewsletterDto> findAllActiveNewsletters();

    void deleteNewsletter(Long newsletterId);

}
