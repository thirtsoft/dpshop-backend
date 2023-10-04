package com.dp.dpshopbackend.services;

import com.dp.dpshopbackend.dto.ArticleDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface ArticleService {

    ArticleDto save(ArticleDto articleDto);

    ArticleDto saveArticleWithFile(String articleDto, MultipartFile photoArticle) throws IOException;

    ArticleDto update(Long id, ArticleDto articleDto);

    ArticleDto findById(Long id);

    ArticleDto findByReference(String reference);

    List<ArticleDto> findListArticleByScategories(Long scatId);

    List<ArticleDto> findListArticleByKeyword(String keyword);

    List<ArticleDto> findListArticleGroupByPrice(double price);

    List<ArticleDto> findListArticleByPriceMinMax(double min, double max);

    List<ArticleDto> findListArticleBySelected();

    List<ArticleDto> findTop12ByOrderByCreateDateDesc();

    List<ArticleDto> findAllActiveArticles();

    Page<ArticleDto> findArticleByPageable(Pageable pageable);

    Page<ArticleDto> findArticleByScategoryPageables(Long scatId, Pageable pageable);

    Page<ArticleDto> findArticleBySamePricePageables(double price, Pageable pageable);

    BigDecimal countNumberOfArticleInSubCategory(@Param("subcat") Long scatId);

    void deleteArticle(Long id);
}
