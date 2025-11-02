package com.newsy.newsy.dto;

import com.newsy.newsy.model.News;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsDTO {

    private String id;
    private String title;
    private String author;
    private String source;
    private String url;
    private LocalDateTime publishedAt;
    private String category;
    private String description;
    private String imageUrl;
    private String sourceIcon;

    public static NewsDTO fromEntity(News news) {
        return new NewsDTO(
            news.getId(),
            news.getTitle(),
            news.getAuthor(),
            news.getSource(),
            news.getUrl(),
            news.getPublishedAt(),
            news.getCategory(),
            news.getDescription(),
            news.getImageUrl(),
            news.getSourceIcon()
        );
    }
}
