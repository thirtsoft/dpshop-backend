package com.dp.dpshopbackend.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "emailMessage")
@Data
public class EmailMessage implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "from")
    private String from;

    @Column(name = "to_address")
    private String to_address;

    @Column(name = "subject")
    private String subject;

    @Column(name = "body")
    @Lob
    private String body;

    public EmailMessage(String from, String to_address, String subject, String body) {
        this.from = from;
        this.to_address = to_address;
        this.subject = subject;
        this.body = body;
    }
}
