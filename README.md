# Student and Course Backend

Spring Boot application for managing students and courses, using Thymeleaf, Spring Data JPA, and MySQL.

## Requirements

- Java 21
- Docker Desktop running, with Docker Compose available
- Maven, or the included Maven wrapper (`./mvnw`)

This project currently uses Spring Boot 4.1.1. The assignment specifies Spring Boot 3; confirm the version requirement with your instructor before submission.

Before making Docker changes, save a backup copy of the original working project.

## Environment setup

Create a `.env` file in the project root with your local values. These are sample credentials only:

```dotenv
MYSQL_ROOT_PASSWORD=exampleRootPassword
MYSQL_DATABASE=demo40
MYSQL_USER=appuser
MYSQL_PASSWORD=exampleAppPassword
MYSQL_HOST_PORT=3306
APP_HOST_PORT=8080
```

Keep real passwords out of submissions and GitHub. Include an `.env.example` with sample values instead. The `.dockerignore` excludes `.env` from image builds, but does not exclude it from Git or ZIP files.

MySQL initializes these credentials when its data volume is first created. Editing `.env` later does not change existing MySQL accounts or passwords.

## Build and run with Docker

Run these commands from the project root:

```sh
./mvnw clean package
docker compose up -d --build
docker compose ps
```

On Windows, use `mvnw.cmd clean package` for the Maven command.

The Dockerfile packages the built JAR into a Java 21 runtime image. Compose starts MySQL first, waits for its health check, and then starts the application. Inside Docker, the app connects to `mysql:3306`. From the host, MySQL is available at `localhost:3306` by default.

## Test the application

1. Check `docker compose ps`: both `app` and `mysql` should be running, and MySQL should be healthy.
2. Open <http://localhost:8080>.
3. Open <http://localhost:8080/showNewStudentForm>, create a test student, and confirm it appears at <http://localhost:8080/studentList>.
4. Update and delete the test student through the student list page.
5. Check the course API with `curl -i http://localhost:8080/course/`. An empty course list returns HTTP 204; existing courses return HTTP 200.

View application and database logs:

```sh
docker compose logs --tail=100 app mysql
```

Run automated tests separately with:

```sh
./mvnw test
```

Tests use the test configuration with an in-memory H2 database.

## Stop and restart

Stop the stack while keeping database records:

```sh
docker compose down
```

Start it again:

```sh
docker compose up -d
```

After changing application code, rebuild the JAR and image using the build commands above. MySQL records persist in the `mysql_data` volume.

## Troubleshooting

- **Port 3306 already in use:** stop the other MySQL server, or change `MYSQL_HOST_PORT` to `3307` in `.env` and run `docker compose up -d` again. The connection between containers still uses port 3306.
- **Port 8080 already in use:** stop the application running in IntelliJ, or change `APP_HOST_PORT` in `.env` and use that port in the browser.
- **Connection refused or dialect error:** confirm MySQL is healthy and inspect the logs. Hibernate cannot determine the dialect when it cannot connect to the database.
- **Docker daemon unavailable:** start Docker Desktop before running Compose.

## Submission

Include the source code, `pom.xml`, Maven wrapper files and `.mvn` folder, `Dockerfile`, `compose.yaml`, `.dockerignore`, this README, and an `.env.example` containing sample values. Exclude real `.env` credentials, `target/`, and `.idea/` from the submission ZIP.

Follow the complete student handout for any additional evidence or screenshot requirements. Capture both containers running and the application working if requested.
