🧬 AI-Based Learning Disorder Detector – Teacher Dashboard
📌 Overview

This project is a Spring Boot backend system designed to assist schools in the early detection of learning disorders (e.g., dyslexia, ADHD, slow learning).

Teachers can:

Register students.

Record quiz attempts & handwriting samples.

Review AI-based risk predictions.

🎯 The goal is to empower teachers with early insights and support interventions for students who need help.

🛠️ Tech Stack

Java 17

Spring Boot 3.x (REST API)

Spring Data JPA + Hibernate (ORM)

MySQL (Database)

Lombok (Boilerplate reduction)

Maven (Build tool)

📂 Project Structure
src/main/java/com/learningdetector/aild/
 ├── entity/         # JPA Entities (Student, Teacher, Quiz, Prediction, etc.)
 ├── repository/     # JPA Repositories (DAO layer)
 ├── controller/     # REST Controllers (API layer)
 └── AiLearningDisorderApplication.java  # Main Spring Boot entry point

⚙️ Setup & Installation
1. Clone the Repo
git clone https://github.com/your-username/ai-learning-disorder.git
cd ai-learning-disorder

2. Create Database

Log in to MySQL:

CREATE DATABASE learningdb;

3. Configure application.properties

Update src/main/resources/application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/learningdb
spring.datasource.username=root
spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect

4. Run the Application
mvn spring-boot:run

📡 API Endpoints
👩‍🎓 Student APIs

POST /students → Add new student

GET /students → List all students

GET /students/{id} → Get student by ID

DELETE /students/{id} → Delete student

👨‍🏫 Teacher APIs

POST /teachers → Add new teacher

GET /teachers → List all teachers

📝 Quiz APIs

POST /quizzes → Create new quiz

GET /quizzes → List all quizzes

🔮 Prediction APIs (future ML integration)

POST /predictions → Add AI risk prediction

GET /predictions/student/{id} → View predictions for a student

🚀 Future Enhancements

Integrate a Python-based ML model for risk prediction.

Add JWT authentication for secure teacher login.

Build a frontend dashboard (React/Angular/Thymeleaf).

Support real handwriting analysis via OCR integration.

🧑‍💻 Contributing
