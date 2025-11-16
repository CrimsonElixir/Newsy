## Newsletter

This guide explains the subscription feature, required configuration, why email may fail on some hosts (Render free tier), and how to test locally.

How it works (brief):
- Frontend: subscription dialog posts `POST /api/subscribe` with `{ email, categories }`.
- Backend: `SubscriptionController` stores subscribers (with verification token). Verified subscribers are used by `NewsletterScheduler`.
- Scheduler: `NewsletterScheduler` runs on a cron schedule, collects verified subscribers, queries top articles per category (case-insensitive), compiles the HTML email using `src/main/resources/templates/newsletter.html`, and sends via `EmailService`.
- The scheduler skips sending to a subscriber if no articles are found for their selected categories.

What to add / configuration (concise)
- Set the following environment variables in your host (Render) or in `.env` for local testing.

Required env vars (recommended keys):
```ini
MAIL_HOST=smtp.rediffmail.com   #SMTP host
MAIL_PORT=587   #SMTP port (587 for STARTTLS, 465 for SSL)
MAIL_USERNAME=username  #SMTP username
MAIL_PASSWORD=password  #SMTP password
MAIL_FROM=no-reply@newsy.local  #From address (must be valid/allowed by provider)
MAIL_FROM_NAME=Newsy
MAIL_SMTP_AUTH=true
MAIL_STARTTLS_ENABLE=false  #true for Prod
MAIL_STARTTLS_REQUIRED=false    #true for Prod
```

Corresponding Spring properties (example `application.properties` using env substitution):

```properties
spring.mail.host=${MAIL_HOST}
spring.mail.port=${MAIL_PORT}
spring.mail.username=${MAIL_USERNAME}
spring.mail.password=${MAIL_PASSWORD}
spring.mail.properties.mail.smtp.auth=${MAIL_SMTP_AUTH:true}
spring.mail.properties.mail.smtp.starttls.enable=${MAIL_STARTTLS_ENABLE:false}
spring.mail.properties.mail.smtp.starttls.required=${MAIL_STARTTLS_REQUIRED:false}
# timeouts (recommended)
spring.mail.properties.mail.smtp.connectiontimeout=10000
spring.mail.properties.mail.smtp.timeout=10000
spring.mail.properties.mail.smtp.writetimeout=10000
```

Why email fails on Render free tier:
- Outbound SMTP ports are blocked on many free cloud plans - the app cannot reach external SMTP servers.
- Other Provider policies.

What that means in practice:
- Email subscription works locally but fails on deployment.

Recommended production options:
- Use a transactional email provider (SendGrid, Mailgun, Amazon SES). These support modern TLS, are cloud-friendly, and usually provide API or SMTP options on ports that cloud hosts allow.


Troubleshooting checklist:
- Verify env vars are set properly.
- Check local/Render deploy logs.


Files to review for the subscription flow
- Backend: `NewsletterScheduler`, `SubscriptionController`, `SubscriptionService`, `SubscriberRepository`, `EmailService`
- Templates: `src/main/resources/templates/newsletter.html`, `verification-email.html`
- Frontend: `Subscription`, `Subscribed`, `UnSubscribed` components and `Subscription` service.
---

**Happy Learning! 🎉**
