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

@CrossOrigin(origins = {"https://soulbusinesse.com"})
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
    public ResponseEntity<NewsletterDto> update(Long id, NewsletterDto newsletterDto) {
        newsletterDto.setId(id);
        return ResponseEntity.ok(newsletterService.save(newsletterDto));
    }

    @Override
    public ResponseEntity<NewsletterDto> findById(Long id) {
        return ResponseEntity.ok(newsletterService.findById(id));
    }

    @Override
    public ResponseEntity<List<NewsletterDto>> findAll() {
        List<NewsletterDto> newsletterDtoList = newsletterService.findAll();
        return new ResponseEntity<>(newsletterDtoList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<NewsletterDto>> getAllNewslettersOrderByIdDesc() {
        List<NewsletterDto> newsletterDtoList = newsletterService.findByOrderByIdDesc();
        return new ResponseEntity<>(newsletterDtoList, HttpStatus.OK);
    }

    @Override
    public BigDecimal countNumberOfNewsletters() {
        return newsletterService.countNumberOfNewsletters();
    }

    @Override
    public void delete(Long id) {
        newsletterService.delete(id);
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
