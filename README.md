# Task Manager API

A Spring Boot REST API for managing tasks.

## Implemented so far

- Project initialized with Spring Boot and Maven
- Switched configuration to `application.yml`
- Integrated H2 in-memory database for development and testing
- Added `Task` JPA entity and `TaskStatus` enum
- Created unit test for the `Task` model
- Added `TaskRepository` interface for data access
- Implemented `TaskService` for business logic
- Implemented `TaskController` with POST and GET endpoints for task creation and retrieval
- Added custom `TaskNotFoundException` and global exception handler for 404 responses
- Added unit and controller tests for service and endpoints, including not-found case

## Getting Started

- Java 17+ and Maven required