package com.newsy.newsy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "news")
public class News {

    @Id
    private String id;

    private String title;
    private String author;
    private String source;
    private String url;
    private LocalDateTime publishedAt;
    private String category;
    private String description;
    private LocalDateTime fetchedAt;
    private String imageUrl;
    private String sourceIcon;

    public News(String title, String author, String source, String url,
                LocalDateTime publishedAt, String category, String description,
                String imageUrl, String sourceIcon) {
        this.title = title;
        this.author = author;
        this.source = source;
        this.url = url;
        this.publishedAt = publishedAt;
        this.category = category;
        this.description = description;
        this.imageUrl = imageUrl;
        this.sourceIcon = sourceIcon;
        this.fetchedAt = LocalDateTime.now();
    }
}
