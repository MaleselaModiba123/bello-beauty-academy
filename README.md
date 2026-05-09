# 💅 Bello Beauty Academy

> A modern, full stack web-based Beauty Training Academy Management System for professional beauty certification programs.

![Platform](https://img.shields.io/badge/Platform-Web%20Application-blueviolet)
![Status](https://img.shields.io/badge/Status-In%20Development-orange)
![Architecture](https://img.shields.io/badge/Architecture-C4%20Model-blue)
![Language](https://img.shields.io/badge/Language-Java-orange)
![Build](https://img.shields.io/badge/Build-Maven-blue)
![Tests](https://img.shields.io/badge/Tests-JUnit%205-green)

---

## Overview

**Bello Beauty Academy** is a comprehensive web-based training management system designed for a professional beauty academy. The platform manages the full lifecycle of beauty education, from course browsing and student enrollment, to class scheduling, progress tracking, and digital certificate generation.

The academy does **not** offer beauty services. It is a **professional beauty training institution** offering certification courses in:

- 💁 Lash Techniques
- 👁️ Brow Artistry
- 💅 Nail Technology
- 💄 Makeup Artistry

This platform digitises and streamlines all academy operations, replacing manual administrative processes with an efficient, role-based digital management system.

---

## What This System Will Do Once Completed

Once fully developed, the Bello Beauty Academy Platform will provide a complete digital management solution for the academy. Students will be able to browse and enroll in certification courses online, upload proof of payment, access course materials, track their training progress, and download their digital certificates upon completion. Trainers will be able to manage their assigned sessions, record student attendance, and submit competency assessments through a dedicated portal. Administrators will have full control over the course catalogue, student enrollments, trainer assignments, class schedules, and payment confirmations, all from a single centralised dashboard. The system will also automatically generate and issue branded PDF certificates to students who successfully complete their courses, and dispatch email notifications at every key stage of the enrollment and payment lifecycle.

---

## 📂 Repository Structure

```
bello-beauty-academy/
│
├── README.md                           ← You are here
├── CHANGELOG.md                        ← Record of all changes per assignment
├── pom.xml                             ← Maven build file with JUnit 5 dependency
│
├── src/
│   ├── models/                         ← Core domain class implementations
│   │   ├── User.java
│   │   ├── Student.java
│   │   ├── Trainer.java
│   │   ├── Administrator.java
│   │   ├── Course.java
│   │   ├── Enrollment.java
│   │   ├── Certificate.java
│   │   ├── CourseCategory.java
│   │   ├── EnrollmentStatus.java
│   │   └── UserRole.java
│   │
│   └── creational_patterns/            ← All six creational pattern implementations
│       ├── simple_factory/
│       │   └── UserFactory.java
│       ├── factory_method/
│       │   ├── NotificationCreator.java
│       │   ├── EnrollmentNotificationCreator.java
│       │   ├── PaymentConfirmedNotificationCreator.java
│       │   └── CertificateReadyNotificationCreator.java
│       ├── abstract_factory/
│       │   ├── UIComponentFactory.java
│       │   ├── StandardUIFactory.java
│       │   └── AccessibleUIFactory.java
│       ├── builder/
│       │   └── CourseConfig.java
│       ├── prototype/
│       │   ├── CertificateTemplate.java
│       │   └── CertificateTemplateRegistry.java
│       └── singleton/
│           └── DatabaseConnectionManager.java
│
├── tests/                              ← JUnit 5 unit tests for all patterns
│   ├── TestCoreModels.java
│   ├── TestSimpleFactory.java
│   ├── TestFactoryMethod.java
│   ├── TestAbstractFactory.java
│   ├── TestBuilder.java
│   ├── TestPrototype.java
│   ├── TestSingleton.java
│   └── TestInMemoryRepositories.java
│
├── src/
│   └── repositories/                   ← Repository interfaces and implementations
│       ├── Repository.java
│       ├── StudentRepository.java
│       ├── CourseRepository.java
│       ├── EnrollmentRepository.java
│       ├── CertificateRepository.java
│       ├── inmemory/
│       │   ├── InMemoryStudentRepository.java
│       │   ├── InMemoryCourseRepository.java
│       │   ├── InMemoryEnrollmentRepository.java
│       │   └── InMemoryCertificateRepository.java
│       ├── database/
│       │   └── DatabaseStudentRepository.java
│       └── filesystem/
│           └── FileSystemCourseRepository.java
│
├── src/
│   └── factories/                      ← RepositoryFactory for storage abstraction
│       └── RepositoryFactory.java
│
└── docs/
    ├── SPECIFICATION.md
    ├── ARCHITECTURE.md
    ├── STAKEHOLDER_ANALYSIS.md
    ├── SYSTEM_REQUIREMENTS.md
    ├── REFLECTION.md
    ├── TEST_AND_USE_CASE.md
    ├── AGILE_PLANNING.md
    ├── TEMPLATE_ANALYSIS.md
    ├── KANBAN_EXPLANATION.md
    ├── STATE_DIAGRAMS.md
    ├── ACTIVITY_DIAGRAMS.md
    ├── ASSIGNMENT8_REFLECTION.md
    ├── DOMAIN_MODEL.md
    ├── CLASS_DIAGRAM.md
    └── ASSIGNMENT9_REFLECTION.md
```

---

## 📄 Documentation

| Document | Description |
|----------|-------------|
| [SPECIFICATION.md](./docs/SPECIFICATION.md) | Full system specification including domain description, problem statement, and system scope |
| [ARCHITECTURE.md](./docs/ARCHITECTURE.md) | System architecture overview and C4 diagrams |
| [STAKEHOLDER_ANALYSIS.md](./docs/STAKEHOLDER_ANALYSIS.md) | Stakeholder analysis table with roles, concerns, pain points, and success metrics |
| [SYSTEM_REQUIREMENTS.md](./docs/SYSTEM_REQUIREMENTS.md) | Full System Requirements Document with functional requirements, acceptance criteria, NFRs, and traceability matrix |
| [REFLECTION.md](./docs/REFLECTION.md) | Reflection on Agile project management including template selection and Kanban customisation |
| [TEST_AND_USE_CASE.md](./docs/TEST_AND_USE_CASE.md) | Use case diagrams, use case specifications, and test cases |
| [AGILE_PLANNING.md](./docs/AGILE_PLANNING.md) | Agile planning document with user stories, prioritised product backlog, and sprint plan |
| [TEMPLATE_ANALYSIS.md](./docs/TEMPLATE_ANALYSIS.md) | GitHub project template comparison and justification for the selected Kanban template |
| [KANBAN_EXPLANATION.md](./docs/KANBAN_EXPLANATION.md) | Kanban board implementation including board structure, workflow visualisation, and WIP limits |
| [STATE_DIAGRAMS.md](./docs/STATE_DIAGRAMS.md) | Object state transition diagrams for 8 critical system objects |
| [ACTIVITY_DIAGRAMS.md](./docs/ACTIVITY_DIAGRAMS.md) | Activity workflow diagrams for 8 key system workflows |
| [ASSIGNMENT8_REFLECTION.md](./docs/ASSIGNMENT8_REFLECTION.md) | Reflection on object state modeling and activity workflow modeling |
| [DOMAIN_MODEL.md](./docs/DOMAIN_MODEL.md) | Domain model with core entities, attributes, methods, business rules, and relationships |
| [CLASS_DIAGRAM.md](./docs/CLASS_DIAGRAM.md) | Full Mermaid.js class diagram with design decisions, multiplicity explanations, and repository layer diagram (updated Assignment 11) |
| [ASSIGNMENT9_REFLECTION.md](./docs/ASSIGNMENT9_REFLECTION.md) | Reflection on domain modeling and class diagram development |
| [CHANGELOG.md](./CHANGELOG.md) | Record of all changes introduced per assignment |

---

## Language and Technology Choice

The class implementations and creational patterns for Assignment 10 are written in **Java**.

Java was chosen because the class diagram from Assignment 9 already uses typed attributes and typed method signatures, so the translation to Java was straightforward. Inheritance and interface-based design are core to the language, which made the Abstract Factory and Factory Method patterns natural to implement. The `synchronized` keyword and `volatile` modifier are built in, so the thread-safe Singleton did not need any external libraries. The static nested class convention in Java is also a clean fit for the Builder pattern, which is how `CourseConfig.Builder` is structured. Maven and JUnit 5 handle the build and test setup without much configuration.

---

## Creational Patterns

All six creational design patterns are implemented in the `/src/creational_patterns` directory. Each pattern is applied to a real use case from the Bello Beauty Academy system rather than a generic example.

| Pattern | Class | Justification |
|---------|-------|---------------|
| **Simple Factory** | `UserFactory` | The system creates three types of users (Student, Trainer, Administrator) from a single registration flow. A Simple Factory centralises that branching logic so the rest of the system does not need to know which subclass to instantiate. |
| **Factory Method** | `NotificationCreator` and subclasses | The system sends three types of transactional emails: enrollment confirmation, payment confirmed, and certificate ready. The Factory Method pattern allows each notification type to be created by its own dedicated creator, making it straightforward to add new notification types without modifying existing code. |
| **Abstract Factory** | `UIComponentFactory`, `StandardUIFactory`, `AccessibleUIFactory` | The platform needs to support both a standard UI and a high-contrast accessible UI. The Abstract Factory ensures that an entire family of related UI components (dashboard cards, course cards) is created consistently for the chosen theme, without mixing components from different families. |
| **Builder** | `CourseConfig.Builder` | A course configuration has five mandatory fields and five optional ones with sensible defaults. The Builder pattern makes it possible to construct a course configuration step by step, enforces mandatory field validation at construction time, and produces an immutable object once built. |
| **Prototype** | `CertificateTemplate`, `CertificateTemplateRegistry` | Generating a certificate PDF requires a pre-configured branded template. The Prototype pattern allows the system to clone a stored master template and personalise the clone for each student, rather than constructing a new template object from scratch every time. |
| **Singleton** | `DatabaseConnectionManager` | The system must have exactly one database connection pool. The Singleton pattern with double-checked locking and a `volatile` instance field ensures that only one `DatabaseConnectionManager` is created even when multiple threads call `getInstance()` simultaneously. |

---

## Repository Layer

The repository layer was added in Assignment 11. It abstracts all storage operations behind interfaces so the rest of the system never needs to know which storage backend is being used.

The generic `Repository<T, ID>` interface defines the four CRUD operations once. Each entity-specific interface extends it and adds query methods relevant to that entity. The in-memory HashMap implementations are used for development and testing. The `RepositoryFactory` returns the correct implementation based on a storage type string, making it straightforward to switch backends without touching any business logic.

| Component | Location | Purpose |
|-----------|----------|---------|
| `Repository<T, ID>` | `src/repositories/` | Generic interface with save, findById, findAll, delete |
| Entity interfaces | `src/repositories/` | Student, Course, Enrollment, Certificate specific queries |
| In-memory implementations | `src/repositories/inmemory/` | HashMap-based storage for development and testing |
| Database stub | `src/repositories/database/` | Future PostgreSQL implementation |
| Filesystem stub | `src/repositories/filesystem/` | Future JSON file implementation |
| `RepositoryFactory` | `src/factories/` | Returns the correct implementation based on storage type |

**Why Factory Pattern over Dependency Injection:** The factory keeps the codebase self-contained without requiring a DI framework. The rest of the system asks the factory for a repository and never needs to know which implementation it gets back. Switching from `MEMORY` to `DATABASE` in future requires changing one string.

---

## Running the Tests

Requirements: Java 17 or later, Maven 3.8 or later.

```bash
# Run all tests
mvn test

# Run a specific test class
mvn test -Dtest=TestSingleton

# Run tests with coverage report (requires jacoco plugin in pom.xml)
mvn test jacoco:report
```

---

## 🗂️ Kanban Board

The project uses a customised **Kanban** board on GitHub Projects to manage all sprint tasks. The Kanban template was selected from GitHub's available project templates based on its six-column default structure, built-in WIP limit support, and flexibility to add custom columns without sprint overhead. A full comparison of the evaluated templates is available in [TEMPLATE_ANALYSIS.md](./docs/TEMPLATE_ANALYSIS.md).

📋 [View the Project Board](https://github.com/users/Aaniquah222641495/projects/5)

### Board Columns

| Column | Purpose |
|--------|---------|
| **Backlog** | All identified work not yet scheduled for the current sprint |
| **Ready** | Groomed tasks with defined acceptance criteria, ready to be picked up |
| **In Progress** | Tasks actively being developed |
| **Blocked** | Tasks that cannot progress due to an unresolved dependency or blocker |
| **In Review** | Tasks with an open pull request awaiting code review |
| **Testing** | Tasks where development is complete and being verified against acceptance criteria |
| **Done** | Tasks that have been developed, reviewed, tested, and verified |

### Customisation Choices

**Testing column:** Added to create a clear separation between completing development and verifying it against the acceptance criteria defined in the user stories. Without this column, tasks move directly from In Progress to Done and the verification step gets skipped. This is particularly important for critical flows such as the payment confirmation process and certificate generation where correctness directly affects the student experience.

**Blocked column:** Already included in the Kanban template by default. Retained and actively used to make obstacles immediately visible. A task that cannot progress should not remain in In Progress giving a false impression of activity.

**Task labels:** Each task issue carries multiple labels to categorise the type of work involved. All Sprint 1 tasks have the `Sprint 1` and `feature` labels. Tasks are additionally labelled as `backend` for API endpoint work, `frontend` for UI implementation, `notification` for email dispatch tasks, and `testing` for test-writing tasks. This makes it easy to filter the board by work type and understand the nature of each task at a glance.

**Parent user story references:** Each task issue includes a `Parent User Story: #number` reference in its description. GitHub automatically converts this into a clickable cross-reference, linking every task back to the user story it delivers. This ensures full traceability from sprint task through to the original user requirement.

**Future sprint tasks in Backlog:** Tasks T-019, T-020, and T-021 have been added to the Backlog column to represent work from Should-have and Could-have user stories that will be scheduled in future sprints. This ensures the Backlog column reflects a realistic project state with planned but not yet scheduled work.

---

## ✨ Key Features

### 🎓 Student Features
- Browse and search all available certification courses
- Enroll in training courses online
- Upload proof of payment for manual verification
- View personal training schedule and class timetable
- Access course materials and learning resources
- Track personal training progress and attendance per course
- Download digital certificates upon course completion

### 👩‍🏫 Trainer Features
- View assigned courses and upcoming sessions
- Record and update student attendance per session
- Submit student competency assessment results
- Upload course materials and resources for enrolled students

### 🛠️ Administrator Features
- Create, edit, and manage all courses and categories
- Manage trainer profiles and course assignments
- Manage student enrollments and approval workflow
- Review proof of payment and confirm or reject payments
- Schedule and manage training sessions
- Generate and issue branded digital PDF certificates
- Generate operational reports on enrollments, completions, and payments

---

## Architecture Overview

The Bello Beauty Academy follows a **layered client-server architecture** with clearly separated concerns:

| Layer | Technology |
|-------|------------|
| Frontend | React.js (SPA) |
| Backend API | Node.js + Express.js |
| Database | PostgreSQL |
| Authentication | JWT + bcrypt |
| File Storage | AWS S3 |
| Certificate Generation | PDF Service (Puppeteer / PDFKit) |
| Notifications | Email Service (NodeMailer + SendGrid) |
| Hosting | Docker + Cloud Deployment |

---

## 👩‍💻 Author

Aaniquah Dicks

---