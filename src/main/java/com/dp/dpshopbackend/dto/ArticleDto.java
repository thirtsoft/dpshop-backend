package com.dp.dpshopbackend.dto;

import com.dp.dpshopbackend.models.Article;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDto {

    private long id;

    private String reference;

    private String designation;

    private int quantity;

    private double price;

    private double currentPrice;

    private boolean promo;

    private boolean selected;

    private String description;

    private String photo;

    private ScategorieDto scategorieDto;

    public static ArticleDto fromEntityToDto(Article article) {
        if (article == null) {
            return null;
        }

        return ArticleDto.builder()
                .id(article.getId())
                .reference(article.getReference())
                .designation(article.getDesignation())
                .quantity(article.getQuantity())
                .price(article.getPrice())
                .currentPrice(article.getCurrentPrice())
                .promo(article.isPromo())
                .selected(article.isSelected())
                .description(article.getDescription())
                .photo(article.getPhoto())
                .scategorieDto(ScategorieDto.fromEntityToDto(article.getScategorie()))
                .build();
    }

    public static Article fromDtoToEntity(ArticleDto articleDto) {
        if (articleDto == null) {
            return null;
        }

        Article article = new Article();
        article.setId(articleDto.getId());
        article.setReference(articleDto.getReference());
        article.setDesignation(articleDto.getDesignation());
        article.setQuantity(articleDto.getQuantity());
        article.setPrice(articleDto.getPrice());
        article.setCurrentPrice(articleDto.getCurrentPrice());
        article.setPromo(articleDto.isPromo());
        article.setSelected(articleDto.isSelected());
        article.setDescription(articleDto.getDescription());
        article.setPhoto(articleDto.getPhoto());

        return article;
    }

}
