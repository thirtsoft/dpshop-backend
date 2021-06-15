package com.dp.dpshopbackend.services.impl;

import com.dp.dpshopbackend.dto.ArticleDto;
import com.dp.dpshopbackend.exceptions.ResourceNotFoundException;
import com.dp.dpshopbackend.models.Article;
import com.dp.dpshopbackend.repository.ArticleRepository;
import com.dp.dpshopbackend.services.ArticleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private final ArticleRepository articleRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public ArticleDto save(ArticleDto articleDto) {

        return ArticleDto.fromEntityToDto(
                articleRepository.save(
                        ArticleDto.fromDtoToEntity(articleDto)
                )
        );
    }

    @Override
    public ArticleDto saveArticleWithFile(String article, MultipartFile photoArticle) throws IOException {
        ArticleDto articleDto = new ObjectMapper().readValue(article, ArticleDto.class);
        System.out.println(articleDto);

        articleDto.setPhoto(photoArticle.getOriginalFilename());

        return ArticleDto.fromEntityToDto(
                articleRepository.save(
                        ArticleDto.fromDtoToEntity(articleDto)
                )
        );

    }


    @Override
    public ArticleDto update(Long id, ArticleDto articleDto) {

        if (!articleRepository.existsById(id)) {
            throw new ResourceNotFoundException("Article not found");
        }

        Optional<Article> article = articleRepository.findById(id);

        if (!article.isPresent()) {
            throw new ResourceNotFoundException("Article not found");
        }

        ArticleDto articleDtoResult = ArticleDto.fromEntityToDto(article.get());
        articleDtoResult.setReference(articleDto.getReference());
        articleDtoResult.setDesignation(articleDto.getDesignation());
        articleDtoResult.setPrice(articleDto.getPrice());
        articleDtoResult.setCurrentPrice(articleDto.getCurrentPrice());
        articleDtoResult.setQuantity(articleDto.getQuantity());
        articleDtoResult.setPhoto(articleDto.getPhoto());
        articleDtoResult.setSelected(articleDto.isSelected());
        articleDtoResult.setPromo(articleDto.isPromo());
        articleDtoResult.setDescription(articleDto.getDescription());
        articleDtoResult.setScategoryDto(articleDto.getScategoryDto());

        return ArticleDto.fromEntityToDto(
                articleRepository.save(
                        ArticleDto.fromDtoToEntity(articleDtoResult)
                )
        );
    }

    @Override
    public ArticleDto findById(Long id) {
        if (id == null) {
            log.error("Article Id is null");
            return null;
        }

        Optional<Article> article = articleRepository.findById(id);

        return Optional.of(ArticleDto.fromEntityToDto(article.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun article avec l'Id = " + id + "n'a été trouvé")
        );
    }

    @Override
    public ArticleDto findByReference(String reference) {
        if (!StringUtils.hasLength(reference)) {
            log.error("Article REFERENCE is null");
        }

        Optional<Article> article = articleRepository.findArticleByReference(reference);

        return Optional.of(ArticleDto.fromEntityToDto(article.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun article avec l'Id = " + reference + "n'a été trouvé")
        );


    }

    @Override
    public List<ArticleDto> findAll() {
        return articleRepository.findAll().stream()
                .map(ArticleDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleDto> findListArticleByScategories(Long scatId) {
        if (scatId == null) {
            log.error("Article Scategory is null");
        }
        return articleRepository.findArticleByScategory(scatId).stream()
                .map(ArticleDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleDto> findListArticleBySelected() {
        return articleRepository.findArticleBySelected().stream()
                .map(ArticleDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Article Id is null");
            return;
        }
        articleRepository.deleteById(id);

    }
}
