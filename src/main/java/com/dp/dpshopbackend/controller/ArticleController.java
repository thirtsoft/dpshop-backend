package com.dp.dpshopbackend.controller;

import com.dp.dpshopbackend.controller.api.ArticleApi;
import com.dp.dpshopbackend.dto.ArticleDto;
import com.dp.dpshopbackend.services.ArticleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RestController
@CrossOrigin
public class ArticleController implements ArticleApi {

    private final ArticleService articleService;

    private final String articlePhotosDir = "C://Users//Folio9470m//shopmania//productphotos//";

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Override
    public ResponseEntity<ArticleDto> save(ArticleDto articleDto) {
        return ResponseEntity.ok(articleService.save(articleDto));
    }

    @Override
    public ResponseEntity<ArticleDto> saveArticleWithFile(String article, MultipartFile photoArticle) throws IOException {
        ArticleDto articleDto = new ObjectMapper().readValue(article, ArticleDto.class);
        if (photoArticle != null && !photoArticle.isEmpty()) {
            articleDto.setPhoto(photoArticle.getOriginalFilename());
            photoArticle.transferTo(new File(articlePhotosDir + photoArticle.getOriginalFilename()));
        }

        return ResponseEntity.ok(articleService.save(articleDto));

    }


    @Override
    public ResponseEntity<ArticleDto> update(Long id, ArticleDto articleDto) {
        articleDto.setId(id);
        return ResponseEntity.ok(articleService.save(articleDto));
    }

    @Override
    public ResponseEntity<ArticleDto> findById(Long id) {
        return ResponseEntity.ok(articleService.findById(id));
    }

    @Override
    public ResponseEntity<ArticleDto> findByReference(String reference) {
        return ResponseEntity.ok(articleService.findByReference(reference));
    }

    @Override
    public List<ArticleDto> findAll() {
        return articleService.findAll();
    }

    @Override
    public List<ArticleDto> findListArticleByScategories(Long idScategory) {
        return articleService.findListArticleByScategories(idScategory);
    }

    @Override
    public void delete(Long id) {
        articleService.delete(id);
    }

    @Override
    public byte[] getPhotoArticle(Long id) throws Exception {

        ArticleDto articleDto = articleService.findById(id);

        System.out.println("Article DTO -- " + articleDto);
        System.out.println("Article DTO Designation -- " + articleDto.getDesignation());
        System.out.println("Article DTO Price -- " + articleDto.getPrice());
        System.out.println("Article DTO Photo -- " + articleDto.getPhoto());

        return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/shopmania/productphotos/" + articleDto.getPhoto()));


    }

    @Override
    public void uploadPhotoArticle(MultipartFile photoArticle, Long idArticle) throws IOException {
        ArticleDto articleDto = articleService.findById(idArticle);
        articleDto.setPhoto(photoArticle.getOriginalFilename());
        Files.write(Paths.get(System.getProperty("user.home") + "/shopmania/productphotos/" + articleDto.getPhoto()), photoArticle.getBytes());

        articleService.save(articleDto);
    }


}
