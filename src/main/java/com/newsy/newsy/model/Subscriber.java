package com.newsy.newsy.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "subscribers")
public class Subscriber {

    @Id
    private String id;

    private String email;
    private List<String> categories;

    private boolean verified = false;
    private String verificationToken;

    private boolean unsubscribed = false;

    private Instant createdAt = Instant.now();
}