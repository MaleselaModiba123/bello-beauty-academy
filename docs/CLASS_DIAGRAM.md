# Class Diagram
## Bello Beauty Academy Platform

**Document Version:** 3.0
**Date:** May 2026
**Status:** Updated — Assignment 12

---

## Table of Contents

1. [Class Diagram](#1-class-diagram)
2. [Design Decisions](#2-design-decisions)
3. [Repository Layer Class Diagram](#3-repository-layer-class-diagram)
4. [Service and API Layer Architecture Diagram](#4-service-and-api-layer-architecture-diagram)

---

## 1. Class Diagram

The following Mermaid.js class diagram represents the full object-oriented structure of the Bello Beauty Academy Platform. It includes all seven core domain entities with their attributes, methods, relationships, and multiplicities. All four UML relationship types are represented: inheritance, association, aggregation, and composition.

```mermaid
classDiagram
    %% ─────────────────────────────────────────
    %% BASE CLASS
    %% ─────────────────────────────────────────
    class User {
        -userId : String
        -firstName : String
        -lastName : String
        -email : String
        -passwordHash : String
        -role : String
        -accountStatus : String
        -createdAt : DateTime
        -lastLoginAt : DateTime
        +register() void
        +verifyEmail() void
        +login() String
        +resetPassword() void
        +updateProfile() void
        +deactivate() void
    }

    %% ─────────────────────────────────────────
    %% SPECIALISED USER ROLES
    %% ─────────────────────────────────────────
    class Student {
        -studentId : String
        -phoneNumber : String
        -dateOfBirth : Date
        -emergencyContact : String
        +browseCourses() List
        +submitEnrollment() Enrollment
        +uploadProofOfPayment() void
        +viewSchedule() List
        +viewProgress() List
        +downloadCertificate() File
    }

    class Trainer {
        -trainerId : String
        -specialisation : String
        -bio : String
        -isAvailable : Boolean
        +viewAssignedSessions() List
        +recordAttendance() void
        +submitAssessment() void
        +uploadCourseMaterial() void
    }

    class Administrator {
        -adminId : String
        -department : String
        +createCourse() Course
        +manageCourse() void
        +manageTrainer() void
        +approveEnrollment() void
        +confirmPayment() void
        +generateCertificate() Certificate
        +generateReport() Report
    }

    %% ─────────────────────────────────────────
    %% CORE DOMAIN ENTITIES
    %% ─────────────────────────────────────────
    class Course {
        -courseId : String
        -title : String
        -category : String
        -description : String
        -durationDays : Integer
        -prerequisites : String
        -cost : Decimal
        -maxCapacity : Integer
        -currentEnrollments : Integer
        -status : String
        -createdAt : DateTime
        +publish() void
        +suspend() void
        +archive() void
        +checkAvailability() Boolean
        +getEnrolledStudents() List
    }

    class Enrollment {
        -enrollmentId : String
        -studentId : String
        -courseId : String
        -status : String
        -appliedAt : DateTime
        -activatedAt : DateTime
        -completedAt : DateTime
        +activate() void
        +complete() void
        +suspend() void
        +withdraw() void
        +isEligibleForCertificate() Boolean
    }

    class Payment {
        -paymentId : String
        -enrollmentId : String
        -method : String
        -status : String
        -amount : Decimal
        -proofOfPaymentUrl : String
        -uploadedAt : DateTime
        -confirmedAt : DateTime
        -confirmedBy : String
        -rejectionReason : String
        +uploadProof() void
        +confirm() void
        +reject() void
        +getStatus() String
    }

    class Certificate {
        -certificateId : String
        -enrollmentId : String
        -studentId : String
        -courseId : String
        -certificateNumber : String
        -issueDate : Date
        -pdfUrl : String
        -issuedBy : String
        +generate() void
        +issue() void
        +download() File
        +verify() Boolean
    }

    class Session {
        -sessionId : String
        -courseId : String
        -trainerId : String
        -venue : String
        -sessionDate : Date
        -startTime : Time
        -endTime : Time
        -status : String
        +publish() void
        +cancel() void
        +complete() void
        +getAttendees() List
    }

    class Progress {
        -progressId : String
        -enrollmentId : String
        -sessionId : String
        -attendanceStatus : String
        -assessmentScore : Decimal
        -assessmentPassed : Boolean
        -recordedAt : DateTime
        -trainerNotes : String
        +recordAttendance() void
        +recordAssessment() void
        +calculateAttendanceRate() Decimal
        +isOnTrack() Boolean
    }

    class CourseMaterial {
        -materialId : String
        -courseId : String
        -uploadedBy : String
        -title : String
        -fileUrl : String
        -fileType : String
        -uploadedAt : DateTime
        +upload() void
        +delete() void
        +download() File
    }

    %% ─────────────────────────────────────────
    %% NOTES
    %% ─────────────────────────────────────────
    note for Enrollment "Status lifecycle: PENDING > AWAITING_PAYMENT\n> ACTIVE > IN_PROGRESS > COMPLETED\nActivated only after Payment is CONFIRMED"
    note for Payment "Supports CASH and EFT methods.\nProof of payment uploaded to AWS S3.\nConfirmed manually by Administrator."
    note for Certificate "Generated as a branded PDF.\nUnique certificate number format: BBA-YYYY-NNNN.\nStored in AWS S3 and available for student download."

    %% ─────────────────────────────────────────
    %% INHERITANCE
    %% User is the base class for all three roles
    %% ─────────────────────────────────────────
    User <|-- Student
    User <|-- Trainer
    User <|-- Administrator

    %% ─────────────────────────────────────────
    %% COMPOSITION
    %% Payment and Certificate cannot exist
    %% independently of their parent Enrollment
    %% ─────────────────────────────────────────
    Enrollment "1" *-- "1" Payment : composed of
    Enrollment "1" *-- "0..1" Certificate : produces

    %% ─────────────────────────────────────────
    %% AGGREGATION
    %% Sessions and CourseMaterials belong to a
    %% Course but can exist independently
    %% ─────────────────────────────────────────
    Course "1" o-- "0..*" Session : schedules
    Course "1" o-- "0..*" CourseMaterial : contains

    %% ─────────────────────────────────────────
    %% ASSOCIATIONS
    %% ─────────────────────────────────────────
    Student "1" --> "0..*" Enrollment : submits
    Course "1" --> "0..*" Enrollment : receives
    Trainer "1" --> "0..*" Course : assigned to
    Trainer "1" --> "0..*" Session : delivers
    Enrollment "1" --> "0..*" Progress : tracks
    Session "1" --> "0..*" Progress : generates
    Student "1" --> "0..*" Certificate : earns
    Course "1" --> "0..*" Certificate : awarded by
```

---

## 2. Design Decisions

### 2.1 Inheritance: User as the Base Class

`User` is designed as a base class with three specialised subclasses: `Student`, `Trainer`, and `Administrator`. This decision reflects the fact that all three roles share a common identity and authentication model. Every person on the platform has an email address, a hashed password, an account status, and a role. Rather than duplicating these attributes across three separate, unrelated classes, inheritance keeps the common data in one place and allows each subclass to extend it with role-specific attributes and behaviours.

This aligns directly with [NFR03](SPECIFICATION.md#71-security) (role-based access control), where the `role` attribute on the base `User` class is what the authentication middleware reads to enforce access boundaries at the API level.

### 2.2 Composition: Payment and Certificate within Enrollment

`Payment` and `Certificate` are modelled using **composition** with `Enrollment`. This means neither can exist independently of an `Enrollment` record.

A `Payment` record is created automatically at the moment an enrollment is submitted. It has no meaning outside the context of that enrollment. If the enrollment is removed, the payment record has no parent to belong to. Composition correctly captures this lifecycle dependency.

Similarly, a `Certificate` is the final output of a completed enrollment. It cannot be generated without a valid, completed enrollment to reference. Composition makes this constraint explicit in the model and enforces it at the structural level.

### 2.3 Aggregation: Session and CourseMaterial within Course

`Session` and `CourseMaterial` are modelled using **aggregation** with `Course`. Unlike composition, aggregation represents a "has-a" relationship where the child can exist or be managed independently of the parent.

A `Session` is part of a course's delivery schedule, but sessions can be cancelled, rescheduled, or reassigned without the course itself being affected. A `CourseMaterial` file can be deleted or replaced without removing the course. This weaker ownership relationship is correctly represented by aggregation rather than composition.

### 2.4 Separating Progress from Enrollment

`Progress` could have been modelled as a set of attributes directly on `Enrollment`. However, progress is recorded per session, meaning a student can attend or miss each individual session separately. This required a separate entity with a relationship to both `Enrollment` and `Session`, allowing the system to store granular attendance and assessment records for every session a student attends. This structure is what drives the automated at-risk detection described in the state diagrams in [STATE_DIAGRAMS.md](./STATE_DIAGRAMS.md).

### 2.5 Notes in the Diagram

Notes have been added to the three most behaviourally complex entities (`Enrollment`, `Payment`, and `Certificate`) to provide inline clarification of key constraints and business rules without cluttering the class definitions themselves. This is particularly useful for the `Enrollment` status lifecycle, which spans eight distinct states and would be difficult to infer from the class definition alone.

### 2.6 Multiplicity Summary

| Relationship | Multiplicity | Reasoning |
|-------------|-------------|-----------|
| `Student` to `Enrollment` | `1` to `0..*` | A student may have no enrollments yet, or many across different courses |
| `Course` to `Enrollment` | `1` to `0..*` | A course may have no enrollments yet, or many active students |
| `Enrollment` to `Payment` | `1` to `1` | Every enrollment has exactly one payment record, no more and no less |
| `Enrollment` to `Certificate` | `1` to `0..1` | An enrollment produces at most one certificate, and only upon completion |
| `Trainer` to `Session` | `1` to `0..*` | A trainer may be assigned to multiple sessions; each session has one trainer |
| `Course` to `Session` | `1` to `0..*` | A course consists of multiple scheduled sessions over its duration |
| `Course` to `CourseMaterial` | `1` to `0..*` | A course can have many uploaded materials |
| `Enrollment` to `Progress` | `1` to `0..*` | An enrollment accumulates one progress record per session attended |

### 2.7 Assumptions

- `Session` is distinct from `Course`. A course is the programme definition; sessions are the individual scheduled class meetings that make up that course's delivery.
- The `Administrator` class does not hold a direct object reference to confirmed payments. Confirmation is tracked on the `Payment` entity via the `confirmedBy` attribute, which stores the administrator's user ID. This avoids tight coupling between `Administrator` and `Payment`.
- `CourseMaterial` records the `uploadedBy` attribute as a String (user ID) rather than a direct object reference, to preserve traceability without introducing an additional relationship line into the diagram.

---

## 3. Repository Layer Class Diagram

This section was added in Assignment 11. It shows the generic repository interface, the four entity-specific interfaces, the in-memory implementations, the stub implementations for future backends, and the RepositoryFactory. It also shows how the repository layer connects to the core domain entities from Section 1.

```mermaid
classDiagram

    %% Domain entities
    class Student {
        -userId : String
        -name : String
        -email : String
        +getUserId() String
        +getEmail() String
        +getActiveEnrollmentCount() int
    }

    class Course {
        -courseId : String
        -title : String
        -category : CourseCategory
        -active : Boolean
        +getCourseId() String
        +getCategory() CourseCategory
        +isActive() Boolean
    }

    class Enrollment {
        -enrollmentId : String
        -status : EnrollmentStatus
        +getEnrollmentId() String
        +getStudent() Student
        +getCourse() Course
        +getStatus() EnrollmentStatus
    }

    class Certificate {
        -certificateId : String
        -certificateNumber : String
        +getCertificateId() String
        +getStudent() Student
        +getCertificateNumber() String
    }

    %% Generic repository interface
    class Repository {
        <<interface>>
        +save(T entity) void
        +findById(ID id) Optional~T~
        +findAll() List~T~
        +delete(ID id) void
    }

    %% Entity-specific interfaces
    class StudentRepository {
        <<interface>>
        +findByEmail(String) Optional~Student~
        +findActiveStudents() List~Student~
    }

    class CourseRepository {
        <<interface>>
        +findActiveCourses() List~Course~
        +findByCategory(CourseCategory) List~Course~
    }

    class EnrollmentRepository {
        <<interface>>
        +findByStudentId(String) List~Enrollment~
        +findByCourseId(String) List~Enrollment~
        +findByStatus(EnrollmentStatus) List~Enrollment~
    }

    class CertificateRepository {
        <<interface>>
        +findByStudentId(String) List~Certificate~
        +findByCertificateNumber(String) Optional~Certificate~
    }

    %% In-memory implementations
    class InMemoryStudentRepository {
        -storage : Map~String, Student~
        +save(Student) void
        +findById(String) Optional~Student~
        +findAll() List~Student~
        +delete(String) void
        +findByEmail(String) Optional~Student~
        +findActiveStudents() List~Student~
    }

    class InMemoryCourseRepository {
        -storage : Map~String, Course~
        +save(Course) void
        +findById(String) Optional~Course~
        +findAll() List~Course~
        +delete(String) void
        +findActiveCourses() List~Course~
        +findByCategory(CourseCategory) List~Course~
    }

    class InMemoryEnrollmentRepository {
        -storage : Map~String, Enrollment~
        +save(Enrollment) void
        +findById(String) Optional~Enrollment~
        +findAll() List~Enrollment~
        +delete(String) void
        +findByStudentId(String) List~Enrollment~
        +findByCourseId(String) List~Enrollment~
        +findByStatus(EnrollmentStatus) List~Enrollment~
    }

    class InMemoryCertificateRepository {
        -storage : Map~String, Certificate~
        +save(Certificate) void
        +findById(String) Optional~Certificate~
        +findAll() List~Certificate~
        +delete(String) void
        +findByStudentId(String) List~Certificate~
        +findByCertificateNumber(String) Optional~Certificate~
    }

    %% Stub implementations
    class DatabaseStudentRepository {
        +save(Student) void
        +findById(String) Optional~Student~
        +findAll() List~Student~
        +delete(String) void
        +findByEmail(String) Optional~Student~
        +findActiveStudents() List~Student~
    }

    class FileSystemCourseRepository {
        +save(Course) void
        +findById(String) Optional~Course~
        +findAll() List~Course~
        +delete(String) void
        +findActiveCourses() List~Course~
        +findByCategory(CourseCategory) List~Course~
    }

    %% Factory
    class RepositoryFactory {
        +getStudentRepository(String) StudentRepository
        +getCourseRepository(String) CourseRepository
        +getEnrollmentRepository(String) EnrollmentRepository
        +getCertificateRepository(String) CertificateRepository
    }

    %% Entity interfaces extend generic interface
    Repository <|-- StudentRepository
    Repository <|-- CourseRepository
    Repository <|-- EnrollmentRepository
    Repository <|-- CertificateRepository

    %% In-memory implementations
    StudentRepository <|.. InMemoryStudentRepository
    CourseRepository <|.. InMemoryCourseRepository
    EnrollmentRepository <|.. InMemoryEnrollmentRepository
    CertificateRepository <|.. InMemoryCertificateRepository

    %% Stub implementations
    StudentRepository <|.. DatabaseStudentRepository
    CourseRepository <|.. FileSystemCourseRepository

    %% Factory creates repositories
    RepositoryFactory ..> StudentRepository : creates
    RepositoryFactory ..> CourseRepository : creates
    RepositoryFactory ..> EnrollmentRepository : creates
    RepositoryFactory ..> CertificateRepository : creates

    %% Repositories manage domain entities
    StudentRepository ..> Student : manages
    CourseRepository ..> Course : manages
    EnrollmentRepository ..> Enrollment : manages
    CertificateRepository ..> Certificate : manages
```

### 3.1 Design Decisions

**Generic Repository interface:** Defining the four CRUD operations once on `Repository<T, ID>` means none of the entity-specific interfaces need to repeat them. Adding a new entity repository only requires a new interface that extends `Repository` and any entity-specific query methods.

**Factory Pattern over Dependency Injection:** The `RepositoryFactory` keeps the codebase simple and self-contained without requiring a DI container. The factory achieves the same decoupling goal: the rest of the system asks the factory for a repository and never needs to know which implementation it gets back.

**Stubs over full implementations:** The `DatabaseStudentRepository` and `FileSystemCourseRepository` stubs prove that the architecture supports future backends without any changes to the interfaces or the factory. A new developer can implement either stub fully by filling in the method bodies without touching any other class.


---

## 4. Service and API Layer Architecture Diagram

This section was added in Assignment 12. It shows the full three-layer clean architecture of the Bello Beauty Academy Platform, including the Repository Layer from Assignment 11, the Service Layer, and the REST API Layer added in Assignment 12. The diagram shows the dependency flow between layers and the components within each layer.

```mermaid
classDiagram

    %% ─────────────────────────────────────────
    %% REST API LAYER
    %% ─────────────────────────────────────────
    class StudentController {
        -studentService : StudentService
        +getAllStudents() ResponseEntity
        +getStudentById(String) ResponseEntity
        +registerStudent(Student) ResponseEntity
        +updateStudent(String, Student) ResponseEntity
        +deleteStudent(String) ResponseEntity
    }

    class CourseController {
        -courseService : CourseService
        +getAllCourses() ResponseEntity
        +getActiveCourses() ResponseEntity
        +getCourseById(String) ResponseEntity
        +getCoursesByCategory(String) ResponseEntity
        +createCourse(Course) ResponseEntity
        +updateCourse(String, Course) ResponseEntity
        +deactivateCourse(String) ResponseEntity
    }

    class EnrollmentController {
        -enrollmentService : EnrollmentService
        +getAllEnrollments() ResponseEntity
        +getEnrollmentById(String) ResponseEntity
        +getEnrollmentsByStudent(String) ResponseEntity
        +enrollStudent(Enrollment) ResponseEntity
        +activateEnrollment(String) ResponseEntity
        +cancelEnrollment(String) ResponseEntity
    }

    %% ─────────────────────────────────────────
    %% SERVICE LAYER
    %% ─────────────────────────────────────────
    class StudentService {
        -studentRepository : StudentRepository
        +registerStudent(Student) Student
        +getStudentById(String) Student
        +getAllStudents() List~Student~
        +updateStudent(String, Student) Student
        +deleteStudent(String) void
    }

    class CourseService {
        -courseRepository : CourseRepository
        +createCourse(Course) Course
        +getCourseById(String) Course
        +getAllCourses() List~Course~
        +getActiveCourses() List~Course~
        +getCoursesByCategory(CourseCategory) List~Course~
        +updateCourse(String, Course) Course
        +deactivateCourse(String) void
    }

    class EnrollmentService {
        -enrollmentRepository : EnrollmentRepository
        -studentRepository : StudentRepository
        -courseRepository : CourseRepository
        +enrollStudent(Enrollment) Enrollment
        +getEnrollmentById(String) Enrollment
        +getAllEnrollments() List~Enrollment~
        +getEnrollmentsByStudent(String) List~Enrollment~
        +activateEnrollment(String) Enrollment
        +cancelEnrollment(String) void
    }

    %% ─────────────────────────────────────────
    %% CUSTOM EXCEPTIONS
    %% ─────────────────────────────────────────
    class StudentNotFoundException {
        +StudentNotFoundException(String id)
    }

    class CourseNotFoundException {
        +CourseNotFoundException(String id)
    }

    class EnrollmentNotFoundException {
        +EnrollmentNotFoundException(String id)
    }

    class DuplicateEnrollmentException {
        +DuplicateEnrollmentException(String studentId, String courseId)
    }

    class CourseNotActiveException {
        +CourseNotActiveException(String courseId)
    }

    %% ─────────────────────────────────────────
    %% REPOSITORY LAYER
    %% ─────────────────────────────────────────
    class StudentRepository {
        <<interface>>
        +save(Student) void
        +findById(String) Optional~Student~
        +findAll() List~Student~
        +delete(String) void
        +findByEmail(String) Optional~Student~
        +findActiveStudents() List~Student~
    }

    class CourseRepository {
        <<interface>>
        +save(Course) void
        +findById(String) Optional~Course~
        +findAll() List~Course~
        +delete(String) void
        +findActiveCourses() List~Course~
        +findByCategory(CourseCategory) List~Course~
    }

    class EnrollmentRepository {
        <<interface>>
        +save(Enrollment) void
        +findById(String) Optional~Enrollment~
        +findAll() List~Enrollment~
        +delete(String) void
        +findByStudentId(String) List~Enrollment~
        +findByCourseId(String) List~Enrollment~
        +findByStatus(EnrollmentStatus) List~Enrollment~
    }

    %% ─────────────────────────────────────────
    %% DOMAIN ENTITIES
    %% ─────────────────────────────────────────
    class Student {
        -userId : String
        -name : String
        -email : String
    }

    class Course {
        -courseId : String
        -title : String
        -active : Boolean
        +deactivate() void
    }

    class Enrollment {
        -enrollmentId : String
        -status : EnrollmentStatus
        +activate() void
        +complete() void
        +cancel() void
    }

    %% ─────────────────────────────────────────
    %% API LAYER → SERVICE LAYER (dependency)
    %% ─────────────────────────────────────────
    StudentController ..> StudentService : delegates to
    CourseController ..> CourseService : delegates to
    EnrollmentController ..> EnrollmentService : delegates to

    %% ─────────────────────────────────────────
    %% SERVICE LAYER → REPOSITORY LAYER (dependency)
    %% ─────────────────────────────────────────
    StudentService ..> StudentRepository : uses
    CourseService ..> CourseRepository : uses
    EnrollmentService ..> EnrollmentRepository : uses
    EnrollmentService ..> StudentRepository : uses
    EnrollmentService ..> CourseRepository : uses

    %% ─────────────────────────────────────────
    %% SERVICE LAYER → EXCEPTIONS
    %% ─────────────────────────────────────────
    StudentService ..> StudentNotFoundException : throws
    CourseService ..> CourseNotFoundException : throws
    EnrollmentService ..> EnrollmentNotFoundException : throws
    EnrollmentService ..> DuplicateEnrollmentException : throws
    EnrollmentService ..> CourseNotActiveException : throws

    %% ─────────────────────────────────────────
    %% REPOSITORY LAYER → DOMAIN ENTITIES
    %% ─────────────────────────────────────────
    StudentRepository ..> Student : manages
    CourseRepository ..> Course : manages
    EnrollmentRepository ..> Enrollment : manages
```

### 4.1 Layer Responsibilities

**REST API Layer (`src/api/`):** Controllers are thin. They receive HTTP requests, delegate all business logic to the service layer, and return HTTP responses with appropriate status codes. No business logic lives in a controller.

**Service Layer (`src/services/`):** Services own all business logic. They validate inputs, enforce business rules such as duplicate checks and invalid state transitions, and use repositories for persistence. Services are the only layer that throws custom exceptions.

**Repository Layer (`src/repositories/`):** Repositories are responsible only for persistence operations. They do not contain business logic. The in-memory HashMap implementations are used for development and testing. The `RepositoryFactory` returns the correct implementation based on storage type.

**Domain Entities (`src/models/`):** Core data objects with state transition methods. They enforce their own invariants (e.g. `Enrollment.cancel()` throws if the enrollment is already completed).

### 4.2 Dependency Flow

The dependency flow is strictly one-directional and follows clean architecture:

```
HTTP Request
     ↓
Controller (API Layer)
     ↓
Service (Business Logic Layer)
     ↓
Repository (Persistence Layer)
     ↓
Domain Entity (Data Layer)
```

No layer ever depends on a layer above it. The Repository does not know the Service exists. The Service does not know the Controller exists. This makes each layer independently testable and replaceable.

### 4.3 Why Service Layers Matter

Without a service layer, business logic ends up in controllers. This is called a "fat controller" and is a common anti-pattern. Fat controllers are hard to test because they mix HTTP concerns with business rules, hard to reuse because the logic is tied to a specific endpoint, and hard to maintain because a single change can break multiple endpoints at once.

The service layer solves this by acting as the single source of truth for all business rules. The same `EnrollmentService.enrollStudent()` method can be called from a REST controller, a scheduled job, or a test with identical behaviour. The controller only needs to know how to translate HTTP into a service call and how to translate the result back into HTTP.
