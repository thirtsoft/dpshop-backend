package com.dp.dpshopbackend.controller;

import com.dp.dpshopbackend.controller.api.ArticleApi;
import com.dp.dpshopbackend.dto.ArticleDto;
import com.dp.dpshopbackend.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RestController
@CrossOrigin
public class ArticleController implements ArticleApi {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Override
    public ResponseEntity<ArticleDto> save(ArticleDto articleDto) {
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
        //            .orElseThrow(() -> new ResourceNotFoundException("Utilisateur that id is" + id + "not found"));
        return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/shopmania/productphotos/" + articleDto.getPhoto()));
    }


}
