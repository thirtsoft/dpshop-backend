package com.dp.dpshopbackend.services;

import com.dp.dpshopbackend.dto.ArticleDto;

import java.util.List;

public interface ArticleService {

    ArticleDto save(ArticleDto articleDto);

    ArticleDto update(Long id, ArticleDto articleDto);

    ArticleDto findById(Long id);

    ArticleDto findByReference(String reference);

    List<ArticleDto> findAll();

    List<ArticleDto> findListArticleByScategories(Long scatId);

    void delete(Long id);
}
