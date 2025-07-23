# PersonaFi

**PersonaFi** is a full-stack web application that analyzes users' quiz responses and maps them to financial personality types. The goal of this project was to learn Spring Boot while building a deployable product from scratch.

## Live Demo

- Website: [https://personafi.vercel.app](https://personafi.vercel.app)
- Demo Video: [Watch on YouTube](https://youtu.be/NtS-QbLqOVE)

## Tech Stack

### Backend
- Spring Boot (Java 21)
- PostgreSQL (hosted on Neon)
- JPA / Hibernate
- Docker (for containerizing the backend)
- Deployed on Render

### Frontend
- React
- Material UI
- Recharts (for personality distribution charts)
- Deployed on Vercel

## Testing

- Backend unit and integration tests written using JUnit 5 and Mockito
- Controller tests cover both success and error cases
- Custom error handler ensures invalid submissions return proper HTTP status
- CI pipeline using GitHub Actions:
  - Installs Java and Node
  - Builds and tests backend on every push to main

## Folder Structure (Key Parts)

```
PersonaFi/
├── .github/workflows/ci.yml     # GitHub Actions CI
├── backend/                     # Spring Boot backend code (includes Testing)
├── frontend/                    # React frontend code
├── Dockerfile                   # Backend Docker image
```

## What I Learned

1. Building a working Spring Boot application with proper MVC structure
2. Using PostgreSQL with JPA for persistent data
3. Writing tests in Java using JUnit and Mockito
4. Automating builds and tests using GitHub Actions
5. Deploying full-stack apps with Docker, Vercel, and Render
