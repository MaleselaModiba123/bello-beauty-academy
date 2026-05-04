# Changelog
## Bello Beauty Academy Platform

All notable changes to this project are documented in this file.

---

## [Assignment 10] — May 2026

### Added

**Source Code: `/src/models`**
- `User.java`: base class with shared identity and authentication attributes for all user roles
- `Student.java`: extends `User` with student-specific attributes and enrollment behaviour
- `Trainer.java`: extends `User` with trainer-specific attributes and session behaviour
- `Administrator.java`: extends `User` with administrator privileges and management methods
- `Course.java`: training course entity with category, capacity, cost, and status management
- `Enrollment.java`: central joining entity between `Student` and `Course` with full status lifecycle
- `Certificate.java`: digital PDF credential entity with unique certificate number and generation metadata
- `CourseCategory.java`: enum defining the four course categories: `LASH`, `BROW`, `NAIL`, `MAKEUP`
- `EnrollmentStatus.java`: enum defining enrollment lifecycle states
- `UserRole.java`: enum defining the three platform roles: `STUDENT`, `TRAINER`, `ADMIN`

**Creational Patterns: `/src/creational_patterns`**
- `simple_factory/UserFactory.java`: centralised factory that creates `Student`, `Trainer`, or `Administrator` instances based on a role string
- `factory_method/NotificationCreator.java`: abstract creator defining the `createNotification()` factory method
- `factory_method/EnrollmentNotificationCreator.java`: concrete creator producing `EnrollmentNotification` instances
- `factory_method/PaymentConfirmedNotificationCreator.java`: concrete creator producing `PaymentConfirmedNotification` instances
- `factory_method/CertificateReadyNotificationCreator.java`: concrete creator producing `CertificateReadyNotification` instances
- `abstract_factory/UIComponentFactory.java`: abstract factory interface for creating UI component families
- `abstract_factory/StandardUIFactory.java`: concrete factory producing standard UI components
- `abstract_factory/AccessibleUIFactory.java`: concrete factory producing accessible high-contrast UI components
- `prototype/CertificateTemplate.java`: cloneable certificate template with deep copy implementation
- `prototype/CertificateTemplateRegistry.java`: registry storing and cloning pre-configured certificate templates
- `singleton/DatabaseConnectionManager.java`: thread-safe singleton managing the PostgreSQL connection pool
- `builder/CourseConfig.java`: fluent builder for constructing `CourseConfig` objects with mandatory field validation

**Unit Tests: `/tests`**
- `TestCoreModels.java`: tests for `Student`, `Course`, `Enrollment`, and `Certificate` model classes
- `TestSimpleFactory.java`: tests for `UserFactory` including all three roles and invalid input handling
- `TestFactoryMethod.java`: tests for all three `NotificationCreator` subclasses and correct notification type dispatch
- `TestAbstractFactory.java`: tests for both `StandardUIFactory` and `AccessibleUIFactory` component families
- `TestBuilder.java`: tests for `CourseConfig.Builder` including mandatory fields, optional defaults, overrides, and edge cases
- `TestPrototype.java`: tests for `CertificateTemplate` deep copy behaviour and `CertificateTemplateRegistry` cloning
- `TestSingleton.java`: tests for `DatabaseConnectionManager` including single instance guarantee, thread safety, and connection pool operations

**Build Configuration**
- `pom.xml`: Maven project setup with JUnit 5 dependency for unit test execution

**Documentation**
- `CHANGELOG.md`: this file
- Updated `README.md` with language choice justification, pattern rationale section, updated repository structure, and instructions for running tests with Maven

### GitHub Project Board

A new **Assignment 10** milestone was created to track all work for this assignment, with a description summarising the implementation of core domain classes, all six creational patterns, and unit tests.

Nine new issues were created on the Assignment 10 milestone and linked to Project Board 5:
- #50 Implement core model classes from Class Diagram
- #51 Implement Simple Factory: UserFactory
- #52 Implement Factory Method: NotificationCreator hierarchy
- #53 Implement Abstract Factory: UIComponentFactory families
- #54 Implement Builder: CourseConfig fluent builder
- #55 Implement Prototype: CertificateTemplate and registry
- #56 Implement Singleton: DatabaseConnectionManager
- #57 Write unit tests for all creational patterns
- #58 Add CHANGELOG.md and update README for Assignment 10

Two bug issues were created and resolved during testing:
- #59 Fix: Remove untestable cloningThrows test from TestSingleton
- #60 Fix: Resolve package name and test sources root configuration

All issues are assigned, linked to the Assignment 10 milestone, and moved to Done on the Kanban board.

---

## Prior Assignments

Changes from Assignments 3 through 9 are documented in the commit history and the corresponding documents in the `/docs` directory.