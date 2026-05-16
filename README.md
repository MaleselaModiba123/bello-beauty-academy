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
├── tests/                              ← JUnit 5 unit tests
│   ├── TestCoreModels.java
│   ├── TestSimpleFactory.java
│   ├── TestFactoryMethod.java
│   ├── TestAbstractFactory.java
│   ├── TestBuilder.java
│   ├── TestPrototype.java
│   ├── TestSingleton.java
│   ├── TestInMemoryRepositories.java
│   ├── services/
│   │   ├── StudentServiceTest.java
│   │   ├── CourseServiceTest.java
│   │   └── EnrollmentServiceTest.java
│   └── api/
│       └── ApiIntegrationTest.java
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
│   ├── factories/                      ← RepositoryFactory for storage abstraction
│   │   └── RepositoryFactory.java
│   ├── exceptions/                     ← Custom exceptions for the service layer
│   │   ├── StudentNotFoundException.java
│   │   ├── CourseNotFoundException.java
│   │   ├── EnrollmentNotFoundException.java
│   │   ├── DuplicateEnrollmentException.java
│   │   └── CourseNotActiveException.java
│   ├── services/                       ← Business logic layer
│   │   ├── StudentService.java
│   │   ├── CourseService.java
│   │   └── EnrollmentService.java
│   └── api/                            ← REST API controllers
│       ├── StudentController.java
│       ├── CourseController.java
│       └── EnrollmentController.java
│
└── docs/
    ├── openapi.yaml
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
