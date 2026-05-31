# Contributing to Bello Beauty Academy Platform

**Document Version:** 1.0
**Date:** May 2026
**Status:** Final

Thank you for your interest in contributing to the Bello Beauty Academy Platform. This document provides everything you need to get the project running locally, understand the codebase, and submit your first pull request.

---

## Table of Contents

1. [Prerequisites](#1-prerequisites)
2. [Getting Started](#2-getting-started)
3. [Project Structure](#3-project-structure)
4. [Coding Standards](#4-coding-standards)
5. [How to Pick an Issue](#5-how-to-pick-an-issue)
6. [How to Submit a Pull Request](#6-how-to-submit-a-pull-request)
7. [Running the Tests](#7-running-the-tests)
8. [Branch Naming Conventions](#8-branch-naming-conventions)
9. [Commit Message Format](#9-commit-message-format)
10. [Code of Conduct](#10-code-of-conduct)

---

## 1. Prerequisites

Before you begin make sure you have the following installed:

| Tool | Version | Purpose |
|------|---------|---------|
| Java JDK | 21 or later | Primary language |
| Maven | 3.8 or later | Build and dependency management |
| Git | Any recent version | Version control |
| IntelliJ IDEA | Any version | Recommended IDE |

---

## 2. Getting Started

### Fork and clone the repository

```bash
# Fork the repository on GitHub, then clone your fork
git clone https://github.com/YOUR-USERNAME/bello-beauty-academy.git
cd bello-beauty-academy
```

### Add the upstream remote

```bash
# This keeps your fork in sync with the original repository
git remote add upstream https://github.com/Aaniquah222641495/bello-beauty-academy.git
```

### Build the project

```bash
mvn clean install
```

### Run the application

```bash
mvn spring-boot:run
```

The API will be available at `http://localhost:8080`.
The Swagger UI will be available at `http://localhost:8080/swagger-ui/index.html`.

### Sync your fork before starting work

```bash
git checkout main
git pull upstream main
git push origin main
```

---

## 3. Project Structure

```
bello-beauty-academy/
├── .github/workflows/ci.yml     ← CI/CD pipeline
├── src/
│   ├── api/                     ← REST API controllers
│   ├── services/                ← Business logic layer
│   ├── repositories/            ← Repository interfaces and implementations
│   ├── exceptions/              ← Custom exceptions
│   ├── models/                  ← Domain entities
│   ├── config/                  ← Spring Boot configuration
│   └── creational_patterns/     ← Design pattern implementations
├── tests/                       ← JUnit 5 unit and integration tests
├── docs/                        ← Project documentation
└── pom.xml                      ← Maven build configuration
```

---

## 4. Coding Standards

### General

- Write clean, readable code with meaningful variable and method names
- Keep methods short and focused on a single responsibility
- Add brief `//` comments on non-obvious logic
- Do not leave commented-out code in pull requests

### Java

- Follow standard Java naming conventions: `camelCase` for methods and variables, `PascalCase` for classes, `UPPER_SNAKE_CASE` for constants
- Use `Optional<T>` instead of returning `null`
- Throw meaningful custom exceptions from the `exceptions` package rather than generic `RuntimeException`
- Keep business logic in the service layer — controllers must remain thin

### Testing

- Every new feature must include unit tests
- Service layer tests must use Mockito to mock repositories
- Aim for at least one test per public method
- All 153 existing tests must continue to pass after your changes

### Package structure

- New controllers go in `src/api/`
- New services go in `src/services/`
- New repository interfaces go in `src/repositories/`
- New in-memory implementations go in `src/repositories/inmemory/`
- New exceptions go in `src/exceptions/`
- New models go in `src/models/`

---

## 5. How to Pick an Issue

1. Go to the [Issues tab](https://github.com/Aaniquah222641495/bello-beauty-academy/issues)
2. Filter by the `good-first-issue` label for beginner-friendly tasks
3. Filter by the `feature-request` label for larger enhancements
4. Comment on the issue to let others know you are working on it
5. Wait for a maintainer to assign it to you before starting

Do not start work on an issue that is already assigned to someone else.

---

## 6. How to Submit a Pull Request

### Create a feature branch

```bash
git checkout -b feature/your-feature-name
```

### Make your changes and commit

```bash
git add .
git commit -m "feat: Add your feature description (#issue-number)"
```

### Push your branch

```bash
git push origin feature/your-feature-name
```

### Open a pull request on GitHub

1. Go to the repository on GitHub
2. Click **Compare and pull request**
3. Set the base branch to `main`
4. Fill in the pull request description explaining what you changed and why
5. Reference the issue your PR resolves using `Closes #issue-number`
6. Wait for the CI pipeline to pass — the PR cannot be merged if tests fail
7. Request a review from a maintainer

### Pull request checklist

Before submitting make sure:

- [ ] All existing tests pass with `mvn clean test`
- [ ] New tests are included for any new functionality
- [ ] Code follows the project coding standards
- [ ] Commit messages follow the format described below
- [ ] The PR description references the issue it closes

---

## 7. Running the Tests

```bash
# Run all tests
mvn clean test

# Run a specific test class
mvn test -Dtest=TestCoreModels

# Run tests for the service layer only
mvn test -Dtest=StudentServiceTest,CourseServiceTest,EnrollmentServiceTest
```

The CI pipeline runs all tests automatically on every push and pull request. A pull request cannot be merged if any test fails.

---

## 8. Branch Naming Conventions

| Type | Format | Example |
|------|--------|---------|
| New feature | `feature/description` | `feature/add-payment-gateway` |
| Bug fix | `fix/description` | `fix/enrollment-status-error` |
| Documentation | `docs/description` | `docs/update-api-reference` |
| Test | `test/description` | `test/add-certificate-service-tests` |

---

## 9. Commit Message Format

Follow this format for all commit messages:

```
type: Short description of the change (#issue-number)
```

| Type | When to use |
|------|-------------|
| `feat` | Adding a new feature |
| `fix` | Fixing a bug |
| `docs` | Documentation changes only |
| `test` | Adding or updating tests |
| `chore` | Build process or tooling changes |
| `refactor` | Code changes that neither fix a bug nor add a feature |

**Examples:**
```
feat: Add PayFast payment gateway integration (#45)
fix: Fix enrollment status not updating on payment confirmation (#52)
docs: Update API reference with new enrollment endpoints (#67)
test: Add unit tests for CertificateService (#71)
```

---

## 10. Code of Conduct

- Be respectful and constructive in all code review comments
- Welcome contributors of all skill levels
- Focus feedback on the code, not the person
- If you have questions, open a discussion or comment on the relevant issue

---

We look forward to your contributions. If you have any questions that are not answered here, open an issue and we will help you get started.