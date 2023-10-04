package com.dp.dpshopbackend.controller;

import com.dp.dpshopbackend.controller.api.NewsletterApi;
import com.dp.dpshopbackend.dto.NewsletterDto;
import com.dp.dpshopbackend.services.NewsletterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class NewsletterController implements NewsletterApi {

    private final NewsletterService newsletterService;

    @Autowired
    public NewsletterController(NewsletterService newsletterService) {
        this.newsletterService = newsletterService;
    }

    @Override
    public ResponseEntity<NewsletterDto> save(NewsletterDto newsletterDto) {
        return ResponseEntity.ok(newsletterService.save(newsletterDto));
    }

    @Override
    public BigDecimal countNumberOfNewsletters() {
        return newsletterService.countNumberOfNewsletters();
    }

    @Override
    public ResponseEntity<List<NewsletterDto>> getAllActiveNewsletters() {
        List<NewsletterDto> newsletterDtoList = newsletterService.findAllActiveNewsletters();
        return new ResponseEntity<>(newsletterDtoList, HttpStatus.OK);
    }

    @Override
    public void deleteNewsletter(Long idNewsletter) {
        newsletterService.deleteNewsletter(idNewsletter);
    }
}
