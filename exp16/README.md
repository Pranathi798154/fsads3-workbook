# EXP16 - Swagger/OpenAPI for Student CRUD

## Structure

- `backend`: Spring Boot student CRUD API with Swagger/OpenAPI documentation
- `frontend`: React student CRUD UI connected to the documented backend

## Database

- MySQL database: `exp15`
- Student table: `exp16_students`

## Backend URLs

- App: `http://localhost:8086`
- Swagger UI: `http://localhost:8086/swagger-ui/index.html`
- Alternate Swagger path: `http://localhost:8086/swagger-ui.html`

## Student Endpoints

- `POST /students`
- `GET /students`
- `GET /students/{id}`
- `PUT /students/{id}`
- `DELETE /students/{id}`

## Run Commands

Backend:

```powershell
cd backend
mvn spring-boot:run
```

Frontend:

```powershell
cd frontend
npm run dev
```
