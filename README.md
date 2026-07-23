# 🎓 AI-Powered Learning Management System

A full-stack Learning Management System (LMS) built using **Spring Boot**, **React**, and **MongoDB** with **JWT-based authentication**, **role-based access control**, and **AI-powered study assistance** using **Google Gemini**.

The application enables administrators, faculty, and students to manage courses and study materials while providing AI-generated summaries, quizzes, and previous-year question (PYQ) analysis from uploaded PDF documents.

---

## 🚀 Features

### Authentication & Authorization
- JWT-based authentication
- Spring Security integration
- Role-based access control
    - Admin
    - Faculty
    - Student
- Secure API endpoints
- Password encryption using BCrypt

---

### Admin Module

- Create Faculty accounts
- Create Student accounts
- Enable/Disable user accounts
- View all registered users
- View user details by ID
- Delete users

---

### Faculty Module

- Create courses
- View own courses
- Add students to courses using email
- Upload study materials (PDF)
- Manage course resources

---

### Student Module

- View enrolled courses
- Access uploaded study materials
- Use AI-powered learning features

---

### AI Features

The application integrates **Google Gemini** to provide:

- 📄 AI-generated study summaries
- 📝 AI-generated quizzes
- 📊 Previous Year Question (PYQ) topic analysis

Uploaded PDFs are processed using **Apache PDFBox** to extract text before sending prompts to Gemini.

---

## 🏗️ Tech Stack

### Backend

- Java 21
- Spring Boot
- Spring Security
- JWT Authentication
- Spring Data MongoDB
- MongoDB
- Apache PDFBox
- Google GenAI Java SDK
- Jackson
- Maven

### Frontend

- React
- TypeScript
- Tailwind CSS
- React Router
- Axios
- Vite

---

## 📂 Project Structure

```
AI-Powered-Learning-Management-System
│
├── backend
│   ├── Config
│   ├── Controller
│   ├── DTO
│   ├── Entity
│   ├── Repository
│   ├── Security
│   ├── Service
│   └── uploads
│
└── frontend
    ├── components
    ├── pages
    ├── services
    ├── context
    ├── hooks
    └── utils
```

---

## 🔐 Authentication Flow

1. User logs in with email and password.
2. Spring Security authenticates the credentials.
3. A JWT token is generated and returned.
4. The frontend stores the JWT.
5. Every protected request includes:

```
Authorization: Bearer <JWT>
```

6. Spring Security validates the token before allowing access to protected APIs.

---

## 🤖 AI Processing Flow

```
Faculty Uploads PDF
        │
        ▼
Material Stored
        │
        ▼
Apache PDFBox
(Extract Text)
        │
        ▼
Prompt Construction
        │
        ▼
Google Gemini API
        │
        ▼
AI Response
        │
        ▼
Summary / Quiz / PYQ Analysis
```

---

## 🗄️ Database Design

### Users

- Admin
- Faculty
- Student

### Course

- Created by Faculty
- Contains enrolled student emails
- Stores uploaded materials

### Material

- PDF metadata
- File path
- Associated course

MongoDB stores metadata while uploaded PDF files are stored on the server filesystem.

---

## ⚙️ Setup

### Clone Repository

```bash
git clone <repository-url>
```

---

### Backend

```bash
cd backend
```

Configure:

```
application.properties
```

Required properties:

```
spring.data.mongodb.uri=...
gemini.api.key=YOUR_API_KEY
jwt.secret=YOUR_SECRET_KEY
```

Run:

```bash
mvn spring-boot:run
```

---

### Frontend

```bash
cd frontend
npm install
npm run dev
```

Frontend runs on:

```
http://localhost:5173
```

Backend:

```
http://localhost:8080
```

---

## 📌 API Highlights

### Authentication

```
POST /auth/login
```

### Admin

```
POST   /admin/users
GET    /admin/users
GET    /admin/users/{id}
PUT    /admin/users/{id}/enable
PUT    /admin/users/{id}/disable
DELETE /admin/users/{id}
```

### Faculty

```
POST /faculty/courses
GET  /faculty/courses

POST /faculty/materials/{courseId}
GET  /faculty/materials/{courseId}
```

### Student

```
GET /student/courses
GET /student/materials/{courseId}
```

### AI

```
POST /ai/summary/{materialId}
POST /ai/quiz/{materialId}
POST /ai/analyze/{materialId}
```

---

## 🔮 Future Improvements

- Assignment submission
- Online quizzes with evaluation
- Attendance management
- Announcement module
- AI chat assistant for course-specific Q&A
- Cloud storage for uploaded files (AWS S3 / Azure Blob)
- OCR support for scanned PDFs
- Docker deployment

---

## 👨‍💻 Author

**Ramm Lakshmanan**

Computer Science Engineering Student

