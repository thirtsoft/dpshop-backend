package com.dp.dpshopbackend.dto;

import com.dp.dpshopbackend.models.Article;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDto {

    private Long id;

    @NotNull(message = "La référence ne doit pas etre vide")
    @NotEmpty(message = "La référence ne doit pas etre vide")
    @NotBlank(message = "La référence ne doit pas etre vide")
    private String reference;

    @NotNull(message = "La désignation de l'article ne doit pas etre vide")
    @NotEmpty(message = "La désignation de l'article ne doit pas etre vide")
    @NotBlank(message = "La désignation de l'article ne doit pas etre vide")
    private String designation;

    private int quantity;

    private int quantite;

    @NotNull(message = "Le prix de l'article ne doit pas etre vide")
    @NotEmpty(message = "Le prix de l'article ne doit pas etre vide")
    @NotBlank(message = "Le prix de l'article ne doit pas etre vide")
    private double price;

    private double currentPrice;

    private boolean promo;

    private boolean selected;

    private String description;

    private String manufactured;

    @NotNull(message = "La photo de l'article ne doit pas etre vide")
    @NotEmpty(message = "La photo de l'article ne doit pas etre vide")
    @NotBlank(message = "La photo de l'article ne doit pas etre vide")
    private String photo;

    @NotNull(message = "La sous-catégorie de l'article ne doit pas etre vide")
    @NotEmpty(message = "La sous-catégorie de l'article ne doit pas etre vide")
    @NotBlank(message = "La sous-catégorie de l'article ne doit pas etre vide")
    private ScategoryDto scategoryDto;

    @NotNull(message = "Le fournisseur de l'article ne doit pas etre vide")
    @NotEmpty(message = "Le fournisseur de l'article ne doit pas etre vide")
    @NotBlank(message = "Le fournisseur de l'article ne doit pas etre vide")
    private FournisseurDto fournisseurDto;

    private int actif;

    public void setActif(boolean actif) {
        if (actif == true)
            this.actif = 1;
        else
            this.actif = 0;
    }

    public boolean isActif() {
        if (actif == 1)
            return true;
        else
            return false;
    }

    public ArticleDto(long id, String reference, String designation, int quantity,
                      double price, double currentPrice, boolean promo, boolean selected,
                      String description, String photo, ScategoryDto scategoryDto) {
        this.id = id;
        this.reference = reference;
        this.designation = designation;
        this.quantity = quantity;
        this.price = price;
        this.currentPrice = currentPrice;
        this.promo = promo;
        this.selected = selected;
        this.description = description;
        this.photo = photo;
        this.scategoryDto = scategoryDto;
    }

    public static ArticleDto fromEntityToDto(Article article) {
        if (article == null) {
            return null;
        }
        return ArticleDto.builder()
                .id(article.getId())
                .reference(article.getReference())
                .designation(article.getDesignation())
                .quantity(article.getQuantity())
                .quantite(article.getQuantite())
                .price(article.getPrice())
                .currentPrice(article.getCurrentPrice())
                .promo(article.isPromo())
                .selected(article.isSelected())
                .description(article.getDescription())
                .manufactured(article.getManufactured())
                .photo(article.getPhoto())
                .actif(article.getActif())
                .scategoryDto(ScategoryDto.fromEntityToDto(article.getScategory()))
                .fournisseurDto(FournisseurDto.fromEntityToDto(article.getFournisseur()))
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
        article.setQuantite(articleDto.getQuantite());
        article.setPrice(articleDto.getPrice());
        article.setCurrentPrice(articleDto.getCurrentPrice());
        article.setPromo(articleDto.isPromo());
        article.setSelected(articleDto.isSelected());
        article.setDescription(articleDto.getDescription());
        article.setManufactured(articleDto.getManufactured());
        article.setPhoto(articleDto.getPhoto());
        article.setActif(articleDto.isActif());
        article.setScategory(ScategoryDto.fromDtoToEntity(articleDto.getScategoryDto()));
        article.setFournisseur(FournisseurDto.fromDtoToEntity(articleDto.getFournisseurDto()));
        return article;
    }

}
