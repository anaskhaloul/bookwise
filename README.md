# BookWise

BookWise is a library lending application built as a portfolio project around Java, Hibernate/JPA, SQL, and the library-management work in Anas Khaloul's CV. It includes a browser-based desk interface served by the same Spring Boot application as its REST API.

## Features

- Responsive library overview with catalog and overdue-loan summaries
- Paginated book search by title, author, or ISBN
- Book and member registration with server-side validation
- Checkout and return workflow with a 14-day loan period
- Overdue loan list and consistent JSON errors
- MySQL persistence with Hibernate and Flyway schema migrations
- Local container setup for the application and database
- Starter catalog of ten programming and computing titles, plus fictional demo members

## Start locally

Install Docker Desktop, then from this directory run:

```sh
docker compose up --build
```

Open <http://localhost:8080>. The initial image build downloads Maven dependencies and compiles the app using Java 17; later starts reuse the built image. Stop with `Ctrl+C`. The database is stored in a Docker volume named `bookwise-data`.

On first startup, Flyway inserts ten sample technical books and three fictional demo members. This is a starter dataset; it is inserted once and is not duplicated on later starts.

Local-only database credentials are in `compose.yaml`; replace them before using this setup beyond a local machine. The application accepts `DB_URL`, `DB_USER`, `DB_PASSWORD`, and `PORT` environment variables.

## API

| Method | Route | Purpose |
|---|---|---|
| `GET` | `/api/v1/books?q=tolkien&page=0&size=10` | Search catalog |
| `GET` | `/api/v1/books/summary` | Count titles and available copies |
| `POST` | `/api/v1/books` | Add a book |
| `GET` | `/api/v1/books/{id}` | Get a book |
| `POST` | `/api/v1/members` | Register a member |
| `POST` | `/api/v1/loans` | Check out a copy |
| `POST` | `/api/v1/loans/{id}/return` | Return a loan |
| `GET` | `/api/v1/members/{id}/loans` | List a member's active loans |
| `GET` | `/api/v1/loans/overdue` | List overdue loans |

Example book request:

```json
{"title":"The Pragmatic Programmer","author":"David Thomas","isbn":"9780135957059","copies":3}
```

Example checkout request:

```json
{"bookId":1,"memberId":1}
```

## Project structure

- `catalog`: book entity, search, and catalog API
- `loan`: members, loans, lending rules, and lending API
- `shared`: common API error format
- `src/main/resources/static`: web interface
- `src/main/resources/db/migration`: versioned MySQL schema

## Roadmap

Authentication and role-based access, audit history, richer book/author modeling, availability filters, and deployment documentation are possible next milestones. The current version is a learning project, not a production library system.
