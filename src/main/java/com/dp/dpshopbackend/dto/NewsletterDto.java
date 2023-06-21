package com.dp.dpshopbackend.dto;

import com.dp.dpshopbackend.models.Newsletter;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
public class NewsletterDto {

    private Long id;

    private String code;

    @NotNull(message = "L'email ne doit pas etre vide")
    @NotEmpty(message = "L'email ne doit pas etre vide")
    @NotBlank(message = "L'email ne doit pas etre vide")
    private String customerEmail;

    private String subject;

    private String message;

    private Date dateInscription;

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

    public static NewsletterDto fromEntityToDto(Newsletter newsletter) {
        if (newsletter == null) {
            return null;
        }
        return NewsletterDto.builder()
                .id(newsletter.getId())
                .code(newsletter.getCode())
                .customerEmail(newsletter.getCustomerEmail())
                .subject(newsletter.getSubject())
                .message(newsletter.getMessage())
                .dateInscription(newsletter.getDateInscription())
                .actif(newsletter.getActif())
                .build();
    }

    public static Newsletter fromDtoToEntity(NewsletterDto newsletterDto) {
        if (newsletterDto == null) {
            return null;
        }

        Newsletter newsletter = new Newsletter();
        newsletter.setId(newsletterDto.getId());
        newsletter.setCode(newsletterDto.getCode());
        newsletter.setCustomerEmail(newsletterDto.getCustomerEmail());
        newsletter.setSubject(newsletterDto.getSubject());
        newsletter.setMessage(newsletterDto.getMessage());
        newsletter.setDateInscription(newsletterDto.getDateInscription());
        newsletter.setActif(newsletterDto.isActif());
        return newsletter;
    }
}
