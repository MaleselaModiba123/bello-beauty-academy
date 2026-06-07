# Project Roadmap
## Bello Beauty Academy Platform

**Document Version:** 1.0
**Date:** May 2026
**Status:** Final

This roadmap outlines the planned future features and enhancements for the Bello Beauty Academy Platform. Items are organised by priority and release phase. Community contributions are welcome on any item marked as open for contribution.

---

## Table of Contents

1. [Current State](#1-current-state)
2. [Phase 2 - Payment and Notifications](#2-phase-2--payment-and-notifications)
3. [Phase 3 - Mobile and Virtual Learning](#3-phase-3--mobile-and-virtual-learning)
4. [Phase 4 - Analytics and Reporting](#4-phase-4--analytics-and-reporting)
5. [Phase 5 - Infrastructure and Performance](#5-phase-5--infrastructure-and-performance)
6. [Open for Contribution](#6-open-for-contribution)

---

## 1. Current State

The following features are implemented and available in the current release:

| Feature | Status |
|---------|--------|
| Student registration and login | Complete |
| Course catalogue browsing | Complete |
| Student enrollment with proof of payment upload | Complete |
| Admin payment confirmation dashboard | Complete |
| Trainer attendance and assessment recording | Complete |
| Digital certificate generation | Complete |
| REST API with Swagger documentation | Complete |
| CI/CD pipeline with GitHub Actions | Complete |
| In-memory repository layer | Complete |
| Service layer with business logic | Complete |

---

## 2. Phase 2 - Payment and Notifications

**Target:** Next release

### PayFast Online Payment Integration
Replace the manual proof of payment workflow with instant online card payment via the PayFast payment gateway. Students will be able to pay at enrollment without administrator intervention.

- Generate signed PayFast payment requests
- Handle Instant Transaction Notification (ITN) webhooks
- Automatically activate enrollment on successful payment
- Related issue: see Future Scope in [SPECIFICATION.md](./docs/SPECIFICATION.md)

### Email Notification System
Connect the notification service to a real email provider using SendGrid or NodeMailer.

- Enrollment confirmation emails
- Payment confirmation and rejection emails
- Schedule change alerts
- Certificate ready notifications

### SMS Notifications
Send SMS alerts for critical events such as payment confirmation and schedule changes using a provider such as Twilio or Africa's Talking.

---

## 3. Phase 3 - Mobile and Virtual Learning

**Target:** Future release

### React Native Mobile Application
Build a cross-platform mobile application for iOS and Android that allows students to access their schedule, course materials, progress records, and certificates from their mobile devices.

- Student dashboard
- Course materials download
- Certificate download
- Push notifications for schedule updates

### Live Virtual Classes
Integrate with a video conferencing API (Zoom or Google Meet) to support remote training sessions alongside in-person classes.

- Schedule virtual sessions
- Send join links to enrolled students
- Record session attendance for virtual classes

---

## 4. Phase 4 - Analytics and Reporting

**Target:** Future release

### Advanced Admin Dashboard
Replace the current text-based reports with an interactive dashboard showing enrollment trends, course completion rates, revenue summaries, and trainer workload in real time.

- Enrollment trend charts
- Course completion rate graphs
- Revenue summary by course category
- Trainer session load visualisation

### Student Progress Analytics
Give students a visual overview of their training progress including attendance rates, assessment scores, and predicted completion dates.

- Attendance rate progress bar
- Assessment score history chart
- Estimated completion date calculator

### Certificate Verification Portal
A public-facing page where employers and clients can verify the authenticity of a Bello Beauty Academy certificate by entering the unique certificate number.

- Public certificate lookup by certificate number
- Display student name, course, and issue date
- QR code on certificate linking to verification page

---

## 5. Phase 5 - Infrastructure and Performance

**Target:** Future release

### PostgreSQL Database Integration
Replace the in-memory HashMap repositories with a fully implemented PostgreSQL database backend using JDBC or Spring Data JPA.

- Complete `DatabaseStudentRepository` implementation
- Database migrations using Flyway or Liquibase
- Connection pooling with HikariCP

### Redis Caching
Introduce Redis caching for frequently accessed data such as the course catalogue and active enrollment lists to reduce database load and improve response times.

- Cache course catalogue with TTL
- Cache active enrollment lists per student
- Cache invalidation on data updates

### Docker Compose Deployment
Provide a complete `docker-compose.yml` that spins up the API, PostgreSQL database, and Redis cache together for reproducible local development and deployment.

- API container
- PostgreSQL container
- Redis container
- Environment variable configuration

### AWS S3 File Storage Integration
Connect the proof of payment upload and certificate PDF storage to a real AWS S3 bucket instead of the current local file path placeholders.

- Upload proof of payment to S3
- Store generated certificate PDFs in S3
- Generate pre-signed download URLs

---

## 6. Open for Contribution

The following items are good starting points for new contributors. Each item has a corresponding GitHub issue.

| Feature | Difficulty | Label |
|---------|------------|-------|
| Add `findByCourseId()` to certificate repository | Beginner | `good-first-issue` |
| Add input validation to all API endpoints | Beginner | `good-first-issue` |
| Write unit tests for CourseServiceTest edge cases | Beginner | `good-first-issue` |
| Add pagination to GET /api/courses endpoint | Beginner | `good-first-issue` |
| Add GET /api/courses/category/{category} tests | Beginner | `good-first-issue` |
| Implement PayFast payment gateway | Intermediate | `feature-request` |
| Add Redis caching for course catalogue | Intermediate | `feature-request` |
| Implement PostgreSQL repository layer | Intermediate | `feature-request` |
| Build certificate verification portal | Intermediate | `feature-request` |

---

Want to work on something not listed here? Open a [feature request issue](https://github.com/Aaniquah222641495/bello-beauty-academy/issues/new) and describe your idea. We welcome all contributions.