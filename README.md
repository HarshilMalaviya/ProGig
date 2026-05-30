# ProGig - Freelancing Platform

A full-stack freelancing platform backend built with Spring Boot 3 and MySQL. Manages users, jobs, proposals, contracts, invoices, milestones, and transactions with JWT-based authentication.

## Tech Stack

- **Java 17** + **Spring Boot 3.3.1**
- **Spring Security** + **JWT** (jjwt 0.12.5)
- **Spring Data JPA** / **Hibernate 6.5**
- **MySQL 8**
- **Swagger / OpenAPI** (springdoc 2.6.0)
- **ModelMapper** 3.2.1
- **Lombok**
- **Maven**

## Prerequisites

- Java 17+
- Maven 3.9+
- MySQL 8+
- Docker & Docker Compose (optional)

## Quick Start (Local)

### 1. Clone and configure

```bash
git clone <repo-url>
cd ProGig
```

### 2. Create MySQL database

```sql
CREATE DATABASE progig;
```

### 3. Configure environment

Set active profile via the `ENV` environment variable (default: `dev`):

```bash
export ENV=dev   # Linux/Mac
$env:ENV="dev"   # Windows PowerShell
```

Edit `src/main/resources/application-dev.properties` with your MySQL credentials:

```properties
spring.datasource.url=database_url
spring.datasource.username=root
spring.datasource.password=your_password
server.port=3030
```

### 4. Build and run

```bash
cd ProGig
mvn clean package
java -jar target/ProGig-0.0.1-SNAPSHOT.jar
```

The app starts at `http://localhost:3030`.

## Docker Deployment

Build and run with Docker Compose:

```bash
docker compose up --build
```

Services:
- **MySQL 8** on port `3307` (internal `3306`)
- **ProGig App** on port `8080`

The app uses the `prod` Spring profile inside Docker. Configure via environment variables (see below).

### Environment Variables

| Variable | Default | Description |
|---|---|---|
| `ENV` | `dev` | Active Spring profile |
| `PORT` | `8080` | Server port |
| `SPRING_DATASOURCE_URL` | `jdbc:mysql://localhost:3306/progig` | JDBC URL |
| `SPRING_DATASOURCE_USERNAME` | `root` | DB username |
| `SPRING_DATASOURCE_PASSWORD` | `root` | DB password |
| `JWT_SECRET` | (dev fallback) | JWT signing key (required in prod) |
| `CORS_ORIGINS` | `http://localhost:5173,http://localhost:3000` | Allowed CORS origins |
| `MYSQL_HOST` | `localhost` | MySQL host |
| `MYSQL_PORT` | `3306` | MySQL port |
| `MYSQL_DATABASE` | `progig` | MySQL database name |
| `MYSQL_USER` | `root` | MySQL user |
| `MYSQL_PASSWORD` | `root` | MySQL password |

## Profiles

| Profile | File | Usage |
|---|---|---|
| `dev` | `application-dev.properties` | Local development (port 3030) |
| `prod` | `application-prod.properties` | Production / Docker (uses env vars) |

Set the active profile via `ENV` environment variable:

```bash
export ENV=prod
```

## API Documentation

Swagger UI is available when the app is running:

- **Swagger UI**: `http://localhost:3030/swagger-ui/index.html`
- **OpenAPI JSON**: `http://localhost:3030/v3/api-docs`

### Authentication

All endpoints except `/Login/**` and `/register` require a JWT Bearer token.

1. **Register**: `POST /register` with `{ "username": "...", "password": "...", "email": "..."}`
2. **Login**: `POST /Login` with `{ "username": "...", "password": "..."}` returns a JWT token
3. Include the token in subsequent requests: `Authorization: Bearer <token>`

### Main Endpoints

| Method | Path | Description |
|---|---|---|
| POST | `/register` | Register a new user |
| POST | `/Login` | Authenticate and get JWT |
| GET | `/user_api/getAllUser` | List all users |
| GET | `/user_api/getUserByUsername/{username}` | Get user by username |
| PUT | `/user_api/updateUser/{id}` | Update user |
| DELETE | `/user_api/deleteUser/{id}` | Delete user |
| POST | `/user_api/accept/{username}` | Accept user |
| POST | `/user_api/reject/{username}` | Reject user with reason |
| GET | `/jobs/Jobs` | List all jobs |
| POST | `/jobs/addjobs` | Create a job |
| GET | `/jobs/Count` | Get job and freelancer counts |
| GET | `/jobs/job/skill/{skill}` | Get jobs by skill |
| GET | `/jobs/Jobs/search?keyword=` | Search jobs |
| PUT | `/jobs/updateJob/{id}` | Update job |
| DELETE | `/jobs/deleteJob/{id}` | Delete job |
| POST | `/profile/addProfile` | Create profile |
| GET | `/profile/getProfile` | Get profile |
| PUT | `/profile/editProfile` | Edit profile |
| POST | `/Invoice/addInvoice` | Create invoice |
| GET | `/Invoice/getAll` | List invoices |
| GET | `/contract/` | List contracts |
| GET | `/millstone/getAll` | List milestones |
| GET | `/millstone/getByJobTitle/{title}` | Get milestones by job |
| GET | `/freelancer/getAllFreelancer` | List freelancers |
| GET | `/clients/getAllClients` | List clients |

## Project Structure

```
ProGig/
├── src/main/java/com/Ntra/ProGig/
│   ├── Configration/       # Security, CORS, OpenAPI config
│   ├── Controller/         # REST controllers
│   ├── Dto/                # Data transfer objects
│   ├── Entity/             # JPA entities
│   ├── Exception/          # Global exception handler
│   ├── Filter/             # JWT authentication filter
│   ├── Interceptor/        # Request logging interceptor
│   ├── Repository/         # Spring Data JPA repositories
│   └── Service/            # Business logic services
├── src/main/resources/
│   ├── application.properties
│   ├── application-dev.properties
│   ├── application-prod.properties
│   └── message.properties
├── Dockerfile
├── docker-compose.yml
└── pom.xml
```

## Building for Production

```bash
cd ProGig
mvn clean package -DskipTests
```

The JAR is at `target/ProGig-0.0.1-SNAPSHOT.jar`. Run with:

```bash
java -jar target/ProGig-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod
```

## Security Notes

- JWT secret should be set via the `JWT_SECRET` environment variable in production
- CORS origins are configurable via the `CORS_ORIGINS` environment variable
- The `prod` profile has no default JWT secret -- the app will fail to start until `JWT_SECRET` is set
- Passwords are hashed with BCrypt
- Use environment variables or a secrets manager for production database credentials
