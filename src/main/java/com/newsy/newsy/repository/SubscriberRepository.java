package com.newsy.newsy.repository;

import com.newsy.newsy.model.Subscriber;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface SubscriberRepository extends MongoRepository<Subscriber, String> {
    Optional<Subscriber> findByEmail(String email);
    Optional<Subscriber> findByVerificationToken(String token);
}