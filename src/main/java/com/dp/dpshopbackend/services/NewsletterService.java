package com.dp.dpshopbackend.services;

import com.dp.dpshopbackend.dto.NewsletterDto;

import java.math.BigDecimal;
import java.util.List;

public interface NewsletterService {

    NewsletterDto save(NewsletterDto newsletterDto);

    BigDecimal countNumberOfNewsletters();

    List<NewsletterDto> findAllActiveNewsletters();

    void deleteNewsletter(Long newsletterId);

}
