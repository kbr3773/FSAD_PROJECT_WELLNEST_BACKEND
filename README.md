# WellNest Backend

Spring Boot REST API for the student health and wellness platform.

Backend URL: `http://localhost:8081`

## Import in Spring Tool Suite

1. Open STS.
2. Select `File > Import > Existing Maven Projects`.
3. Choose this folder: `wellnest-backend`.
4. Run `WellnestBackendApplication`.

## MySQL

The API uses:

- Database: `wellnest_db`
- User: `root`
- Password: configure locally in `src/main/resources/application.properties`

Copy the example file first:

```text
src/main/resources/application.properties.example
```

to:

```text
src/main/resources/application.properties
```

Then set your MySQL password there.

The database is created automatically from:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/wellnest_db?createDatabaseIfNotExist=true
```

Default seeded logins:

- Admin: `admin@wellnest.com` / `Bharath@321`
- Student: `student@university.edu` / `Bharath@321`

## API

- `POST /api/auth/student/register`
- `POST /api/auth/student/login`
- `POST /api/auth/admin/login`
- `GET /api/resources`
- `GET /api/resources?type=MENTAL_HEALTH`
- `GET /api/programs`
- `POST /api/programs/{id}/enroll`
- `POST /api/support`
- `GET /api/dashboard/admin`
- `GET /api/dashboard/student`
