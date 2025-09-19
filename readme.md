# Book Tracker API

A Spring Boot REST API for tracking books, featuring JWT authentication, pagination, and filtering.

## Getting Started

### Prerequisites

- Java 17+
- Gradle (or use the included `gradlew` script)
- PostgreSQL (or use the included H2 in-memory database for testing)
- Git

### Setup

1. **Clone the repository:**
   ```
   git clone https://github.com/your-username/book-tracker.git
   cd book-tracker
   ```

2. **Configure the database:**
   - By default, the app uses **PostgreSQL** when running with `./gradlew bootRun` (see `build.gradle`).
   - To use H2 for local development, uncomment the H2 profile in `build.gradle`:
     ```groovy
     //tasks.named('bootRun') {
     //    args = ["--spring.profiles.active=h2"]
     //}
     ```
   - Database credentials for PostgreSQL should be set in `src/main/resources/application-postgres.properties`:
     ```
     spring.datasource.url=jdbc:postgresql://localhost:5432/booktracker
     spring.datasource.username=your_db_user
     spring.datasource.password=your_db_password
     spring.jpa.hibernate.ddl-auto=update
     ```

3. **Run the application:**
   ```
   ./gradlew bootRun
   ```

4. **Access the API documentation:**
   - [Swagger UI](http://localhost:8080/swagger-ui/index.html)
   - [OpenAPI Spec](http://localhost:8080/v3/api-docs)

### Authentication

- Obtain a JWT token by sending a POST request to `/auth/login` with a username.
- Use the token in the `Authorization` header as `Bearer <your-token>` for all protected endpoints.

### Running Tests

- Run all unit tests:
  ```
  ./gradlew test
  ```
- Test results are available in `build/reports/tests/test/index.html`.



---

Feel free to open issues or submit pull requests!