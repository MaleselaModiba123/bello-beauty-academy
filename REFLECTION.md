# Reflection: Peer Review and Open-Source Collaboration
## Bello Beauty Academy Platform

**Document Version:** 1.0
**Date:** May 2026
**Status:** Final

---

## Peer Feedback Received

Before writing this reflection, I want to document the specific feedback received from peers during the review period. The repository was shared via WhatsApp and Microsoft Teams and six reviewers provided comments.

| Reviewer | Comment |
|----------|---------|
| Reviewer 1 | "Your README is very well structured and easy to understand." |
| Reviewer 2 | "The Swagger UI is a nice touch - makes it easy to see what the API does without reading code." |
| Reviewer 3 | "The C4 diagrams in ARCHITECTURE.md are really clear. I could understand the whole system just from those." |
| Reviewer 4 | "The CONTRIBUTING.md is detailed. I knew exactly what to do to get it running." |
| Reviewer 5 | "The CI/CD pipeline is impressive. Every push triggers tests automatically." |
| Reviewer 6 | "The folder structure is a bit unusual compared to standard Spring Boot projects - took me a moment to find things." |

---

## Reflection

### 1. How the Repository Was Improved Based on Peer Feedback

The feedback from six reviewers gave me a much clearer picture of what was working and what still needed attention. The positive comments about the README, Swagger UI, C4 diagrams, and CI/CD pipeline confirmed that the documentation and automation work done across Assignments 10 through 13 was visible and appreciated. However the most useful comment came from Reviewer 6, who noted that the folder structure was unusual compared to standard Spring Boot projects.

This was a completely fair observation. The project uses a non-standard Maven source directory configuration where `src` is the source root and `tests` is the test root rather than the standard `src/main/java` and `src/test/java` layout. This decision was made early in the project to keep the structure simple for a university assignment context, but it creates confusion for anyone arriving with Spring Boot experience. In response to this feedback, the CONTRIBUTING.md was updated to explicitly call out this non-standard layout in the project structure section, with an explanation of why it differs from the default and what to expect when opening the project in IntelliJ.

The comment from Reviewer 4 about the CONTRIBUTING.md being detailed enough to get started was encouraging because it validated the decision to write it thoroughly rather than as a brief placeholder. The comment from Reviewer 2 about Swagger UI confirmed that the OpenAPI documentation added in Assignment 12 was genuinely useful to someone who had not written any of the code.

---

### 2. Challenges in Onboarding Contributors

The most significant challenge was that the project was not originally designed with contributors in mind. Each assignment built on the previous one in a specific, controlled way. The codebase evolved organically rather than being structured from the start for external collaboration. This meant that many decisions that were obvious to me as the sole developer were completely opaque to anyone reading the code for the first time.

The fact that `BelloBeautyAcademyApplication` is in the `api` package rather than the root `src` directory is a good example. The reason it ended up there was a series of technical decisions made during Assignment 12 to resolve Spring Boot component scanning behaviour across non-standard source roots. None of that context existed anywhere in the codebase until CONTRIBUTING.md was written. Onboarding documentation forces you to explain decisions you made under pressure and never documented, which is an uncomfortable but valuable exercise.

The absence of pull requests from peers during the review period, despite 27 forks, also highlighted a challenge inherent to short-term peer review. A developer who forks a repository on Monday and is expected to submit a meaningful pull request by Friday has a very narrow window to understand the architecture, set up the environment, identify a suitable issue, write code that meets the existing standards, and navigate the pull request workflow. Real open-source projects typically see a long tail of engagement where most forks never produce pull requests. The ones that do usually come from contributors who spent weeks exploring the codebase before writing a single line.

---

### 3. Lessons Learned About Open-Source Collaboration

The most important lesson is that documentation is not a deliverable you add at the end - it is a practice you build throughout the project. Every assignment added new capabilities and every assignment also required updating the README, CHANGELOG, and architecture documentation. By the time Assignment 14 arrived, the documentation was already comprehensive because it had been maintained continuously. The six positive comments about documentation quality were a direct result of this ongoing discipline.

The second lesson is that labels and issue structure matter more than I expected. Before this assignment, issues were used primarily as a project management tool to track which tasks were in progress. The `good-first-issue` and `feature-request` labels shift the purpose of issues from internal tracking to external communication. A well-labeled issue tells a potential contributor not just what needs to be done but whether they are the right person to do it. An issue labeled `good-first-issue` with a clear description, a suggested file, and an expected outcome removes almost all of the barrier to entry for a new contributor.

The third lesson is about the relationship between CI/CD and contributor confidence. The comment from Reviewer 5 about the CI/CD pipeline being impressive highlighted something I had not fully appreciated before: the pipeline is not just a quality tool for the maintainer, it is a safety net for contributors. A new contributor does not need to fully understand the entire codebase before submitting a change. They just need to make their change, push it, and let the pipeline tell them if anything broke. Without this safety net, every pull request is a risk and most people will choose not to take that risk.

Finally this assignment reinforced that open-source readiness is not a binary state. The 27 forks and 25 stars this repository received are a starting point, not an endpoint. A repository becomes more contributor-friendly incrementally, through every documentation improvement, every well-scoped issue, every helpful code review comment, and every time a new contributor successfully submits their first pull request.

---

