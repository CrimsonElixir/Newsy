package com.newsy.newsy.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewsApiResponse {

    private String status;
    private Integer totalResults;
    private List<Result> results;
    private String nextPage;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Result {
        @JsonProperty("article_id")
        private String articleId;

        private String title;
        private String link;
        private List<String> keywords;
        private List<String> creator;
        private String description;
        private String content;

        @JsonProperty("pubDate")
        private String pubDate;

        @JsonProperty("image_url")
        private String imageUrl;

        @JsonProperty("source_id")
        private String sourceId;

        @JsonProperty("source_name")
        private String sourceName;

        @JsonProperty("source_icon")
        private String sourceIcon;

        private List<String> category;
        private List<String> country;
        private String language;
    }
}
