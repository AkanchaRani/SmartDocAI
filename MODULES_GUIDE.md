# SmartDocAI - New Modules Guide

This file explains everything that was added on top of your existing
**User Management** module. Nothing in `entity/User.java`, `dto/UserRequest.java`,
`dto/UserResponse.java`, `repository/UserRepository.java`, `service/UserService.java`,
`service/impl/UserServiceImpl.java`, or `controller/UserController.java` was touched.

## 1. Folder Structure (new files only)

```
src/main/java/com/akancha/smartdocai/
 ├── entity/
 │    ├── Project.java
 │    ├── ApiDetails.java
 │    ├── DatabaseTable.java
 │    ├── TechnicalInfo.java
 │    └── GeneratedDocument.java
 ├── dto/
 │    ├── ProjectRequest.java / ProjectResponse.java
 │    ├── ApiDetailsRequest.java / ApiDetailsResponse.java
 │    ├── DatabaseTableRequest.java / DatabaseTableResponse.java
 │    ├── TechnicalInfoRequest.java / TechnicalInfoResponse.java
 │    ├── LoginRequest.java / LoginResponse.java
 │    ├── DashboardResponse.java
 │    └── GeneratedDocumentResponse.java
 ├── repository/
 │    ├── ProjectRepository.java
 │    ├── ApiDetailsRepository.java
 │    ├── DatabaseTableRepository.java
 │    ├── TechnicalInfoRepository.java
 │    └── GeneratedDocumentRepository.java
 ├── service/  (+ service/impl/)
 │    ├── ProjectService(Impl)
 │    ├── ApiDetailsService(Impl)
 │    ├── DatabaseTableService(Impl)
 │    ├── TechnicalInfoService(Impl)
 │    ├── AuthService(Impl)
 │    ├── DashboardService(Impl)
 │    ├── DocumentGeneratorService(Impl)   <- dummy AI generator (StringBuilder)
 │    └── PdfExportService(Impl)           <- OpenPDF based export
 ├── controller/
 │    ├── AuthController.java
 │    ├── DashboardController.java
 │    ├── ProjectController.java
 │    ├── ApiDetailsController.java
 │    ├── DatabaseTableController.java
 │    ├── TechnicalInfoController.java
 │    └── DocumentGenerationController.java
 └── exception/
      └── InvalidCredentialsException.java (+ handler added in GlobalExceptionHandler)

src/main/resources/static/
 ├── login.html, dashboard.html, projects.html, api.html,
 │   database.html, technical.html, generate.html, documentation.html, index.html
 ├── css/style.css
 └── js/common.js, login.js, dashboard.js, projects.js, api.js,
       database.js, technical.js, generate.js, documentation.js
```

## 2. Dependency added

`pom.xml` now also has OpenPDF (used only by the PDF Export module):

```xml
<dependency>
    <groupId>com.github.librepdf</groupId>
    <artifactId>openpdf</artifactId>
    <version>1.3.30</version>
</dependency>
```

Run `mvn clean install` once after pulling these changes so Maven downloads it.

## 3. How Login Works (kept intentionally simple)

- `POST /auth/login` checks the email + password against the existing `users` table
  (same plain-text password style already used in your User module).
- On success, an `HttpSession` is created server side AND the frontend also stores
  the user object in `localStorage` (`smartdocai_user`) so plain static HTML pages
  can guard themselves with `requireLogin()` from `common.js`.
- `POST /auth/logout` invalidates the session; frontend clears `localStorage`.
- JWT can be added later by replacing `AuthServiceImpl` + adding a filter — the
  controller/service split already keeps this isolated.

## 4. API Endpoints Added

| Module | Method | Endpoint | Body |
|---|---|---|---|
| Auth | POST | `/auth/login` | `{ "email": "", "password": "" }` |
| Auth | POST | `/auth/logout` | - |
| Dashboard | GET | `/dashboard/summary` | - |
| Project | POST | `/projects` | ProjectRequest |
| Project | GET | `/projects` | - |
| Project | GET | `/projects/{id}` | - |
| Project | PUT | `/projects/{id}` | ProjectRequest |
| Project | DELETE | `/projects/{id}` | - |
| API Docs | POST/GET/PUT/DELETE | `/apis`, `/apis/{id}` | ApiDetailsRequest |
| API Docs | GET | `/apis/project/{projectId}` | - |
| DB Schema | POST/GET/PUT/DELETE | `/database-tables`, `/database-tables/{id}` | DatabaseTableRequest |
| DB Schema | GET | `/database-tables/project/{projectId}` | - |
| Technical Info | POST/GET/PUT/DELETE | `/technical-info`, `/technical-info/{id}` | TechnicalInfoRequest |
| Technical Info | GET | `/technical-info/project/{projectId}` | - |
| AI Generator | POST | `/generate/{projectId}` | - (dummy content built with StringBuilder) |
| AI Generator | GET | `/generate/project/{projectId}` | latest generated doc |
| PDF Export | GET | `/generate/pdf/{projectId}` | downloads a `.pdf` file |

All CRUD endpoints return the same `ApiResponse<T>` wrapper you already use
(`{ success, message, data }`), and reuse `ResourceNotFoundException` +
`GlobalExceptionHandler` exactly like the User module.

## 5. Quick Testing Steps (Postman or curl)

1. Create a user first (existing endpoint): `POST /users`
2. Login: `POST /auth/login` with that user's email/password
3. Create a project: `POST /projects`
   ```json
   { "projectName": "SmartDocAI", "description": "Docs generator",
     "technology": "Spring Boot, MySQL", "version": "1.0", "status": "In Progress",
     "userId": 1 }
   ```
4. Add an API: `POST /apis` with `"projectId": 1`
5. Add a DB table: `POST /database-tables` with `"projectId": 1`
6. Add technical info: `POST /technical-info` with `"projectId": 1`
7. Generate docs: `POST /generate/1` — returns the generated text
8. View docs: `GET /generate/project/1`
9. Download PDF: `GET /generate/pdf/1` (open in browser, or Postman "Send and Download")
10. Dashboard: `GET /dashboard/summary`

## 6. Running the Frontend

Because the HTML/CSS/JS files live under `src/main/resources/static`, Spring Boot
serves them automatically. Once the app is running on port 8080, open:

```
http://localhost:8080/
```

which redirects to `login.html`. After logging in you land on `dashboard.html`,
and the sidebar links to every module page.
