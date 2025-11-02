package com.newsy.newsy.repository;

import com.newsy.newsy.model.News;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends MongoRepository<News, String> {

    List<News> findTop5ByCategoryOrderByPublishedAtDesc(String category);

    List<News> findAllByOrderByPublishedAtDesc();

    @Query(sort = "{ 'fetchedAt': -1, 'publishedAt': -1 }")
    List<News> findAllByOrderByFetchedAtDescPublishedAtDesc();

    @Query(value = "{ 'category': ?0 }", sort = "{ 'fetchedAt': -1, 'publishedAt': -1 }")
    List<News> findTop5ByCategoryOrderByFetchedAtDescPublishedAtDesc(String category);

    void deleteByCategory(String category);
}
