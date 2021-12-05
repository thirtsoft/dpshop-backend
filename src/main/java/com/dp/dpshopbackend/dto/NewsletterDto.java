package com.dp.dpshopbackend.dto;

import com.dp.dpshopbackend.models.Newsletter;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class NewsletterDto {

    private Long id;

    private String code;

    private String customerEmail;

    private Date dateInscription;

    public static NewsletterDto fromEntityToDto(Newsletter newsletter) {
        if (newsletter == null) {
            return null;
        }
        return NewsletterDto.builder()
                .id(newsletter.getId())
                .code(newsletter.getCode())
                .customerEmail(newsletter.getCustomerEmail())
                .dateInscription(newsletter.getDateInscription())
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
        newsletter.setDateInscription(newsletterDto.getDateInscription());

        return newsletter;
    }
}
