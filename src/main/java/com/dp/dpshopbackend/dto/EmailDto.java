package com.dp.dpshopbackend.dto;

import com.dp.dpshopbackend.models.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmailDto {

    private Long id;

    private String name;

    private String email;

    private String recever;

    private String subject;

    private String message;


    private FournisseurDto fournisseurDto;

    public static EmailDto fromEntityToDto(Email email) {
        if (email == null) {
            return null;
        }

        return EmailDto.builder()
                .id(email.getId())
                .name(email.getName())
                .email(email.getEmail())
                .recever(email.getRecever())
                .subject(email.getSubject())
                .message(email.getMessage())
                .fournisseurDto(FournisseurDto.fromEntityToDto(email.getFournisseur()))
                .build();
    }

    public static Email fromDtoToEntity(EmailDto emailDto) {
        if (emailDto == null) {
            return null;
        }

        Email email = new Email();
        email.setId(emailDto.getId());
        email.setName(emailDto.getName());
        email.setEmail(emailDto.getEmail());
        email.setRecever(emailDto.getRecever());
        email.setSubject(emailDto.getSubject());
        email.setMessage(emailDto.getMessage());
        email.setFournisseur(FournisseurDto.fromDtoToEntity(emailDto.getFournisseurDto()));

        return email;
    }

}
