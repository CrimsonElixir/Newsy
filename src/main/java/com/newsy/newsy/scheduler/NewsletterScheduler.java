package com.newsy.newsy.scheduler;

import com.newsy.newsy.model.Subscriber;
import com.newsy.newsy.service.EmailService;
import com.newsy.newsy.service.NewsService;
import com.newsy.newsy.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class NewsletterScheduler {

    private final SubscriptionService subscriptionService;
    private final EmailService emailService;
    private final NewsService newsService;

    @Value("${app.max-articles-per-mail:8}")
    private int maxArticlesPerMail;

    @Scheduled(cron = "${newsletter.email.cron}")
    public void sendDaily() {
        List<Subscriber> subs = subscriptionService.getVerifiedActiveSubscribers();
        for (Subscriber s : subs) {
            try {
                List<Map<String, String>> articles = s.getCategories().stream()
                        .flatMap(cat -> newsService.fetchTopArticles(cat, 2).stream())
                        .limit(maxArticlesPerMail)
                        .map(a -> Map.of(
                                "title", a.getTitle(),
                                "url", a.getUrl(),
                                "summary", a.getDescription()
                        ))
                        .collect(Collectors.toList());

                if (articles.isEmpty()) continue;

                emailService.sendNewsletter(s.getEmail(), "Your Newsy Daily", articles, s.getVerificationToken());

            } catch (MessagingException mex) {
                // log & continue
                mex.printStackTrace();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}

