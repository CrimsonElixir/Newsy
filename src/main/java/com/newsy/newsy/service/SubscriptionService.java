package com.newsy.newsy.service;

import com.newsy.newsy.dto.SubscribeRequest;
import com.newsy.newsy.model.Subscriber;
import com.newsy.newsy.repository.SubscriberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final SubscriberRepository repo;
    private final EmailService emailService;

    @Value("${app.max-subscribers:200}")
    private int maxSubscribers;

    public String subscribe(SubscribeRequest req) {
        // Basic check
        long count = repo.count();
        if (count >= maxSubscribers) {
            return "maximum_subscribers_reached";
        }

        Optional<Subscriber> existing = repo.findByEmail(req.getEmail());
        if (existing.isPresent()) {
            Subscriber e = existing.get();
            if (e.isUnsubscribed()) {
                // re-subscribe flow: create new verification token
                e.setUnsubscribed(false);
                e.setVerified(false);
                if (e.getVerificationToken() == null || e.getVerificationToken().isBlank()) {
                    e.setVerificationToken(UUID.randomUUID().toString());
                }
                e.setCategories(req.getCategories());
                repo.save(e);
                try { emailService.sendVerificationEmail(e.getEmail(), e.getVerificationToken()); }
                catch (MessagingException ex) { return "failed_to_send_verification"; }
                return "verification_sent";
            }
            if (e.isVerified()) {
                // already active
                return "already_subscribed";
            } else {
                // not yet verified — re-send verification
                if (e.getVerificationToken() == null || e.getVerificationToken().isBlank()) {
                    e.setVerificationToken(UUID.randomUUID().toString());
                    repo.save(e);
                }
                try { emailService.sendVerificationEmail(e.getEmail(), e.getVerificationToken()); }
                catch (MessagingException ex) { return "failed_to_send_verification"; }
                return "verification_sent";
            }
        }

        // new subscriber
        Subscriber s = Subscriber.builder()
                .email(req.getEmail())
                .categories(req.getCategories())
                .verified(false)
                .unsubscribed(false)
                .verificationToken(UUID.randomUUID().toString())
                .build();
        repo.save(s);

        try {
            emailService.sendVerificationEmail(s.getEmail(), s.getVerificationToken());
        } catch (MessagingException ex) {
            // consider removing the saved subscriber if email fails (optional)
            return "failed_to_send_verification";
        }
        return "verification_sent";
    }

    public String verify(String token) {
        Optional<Subscriber> s = repo.findByVerificationToken(token);
        if (s.isEmpty()) return "invalid_token";
        Subscriber sub = s.get();
        sub.setVerified(true);
        repo.save(sub);
        return "verified";
    }

    public String unsubscribe(String token) {
        Optional<Subscriber> s = repo.findByVerificationToken(token);
        if (s.isEmpty()) return "not_found";
        Subscriber sub = s.get();
        sub.setUnsubscribed(true);
        repo.save(sub);
        return "unsubscribed";
    }

    public List<Subscriber> getVerifiedActiveSubscribers() {
        return repo.findAll()
                .stream()
                .filter(s -> s.isVerified() && !s.isUnsubscribed())
                .toList();
    }
}
