# 🧪 Spring Boot Microservice – Question Service

This is a standalone microservice built using **Spring Boot** to manage quiz questions.  
The goal of this project is to explore **microservice architecture** by isolating the question-handling logic from the rest of the system.

This service will later integrate with other microservices (like `QuizService`) to form a full quiz platform.

---

## 💡 Features Implemented

✅ Expose all quiz questions via REST API  
✅ Fetch random questions by category & count  
✅ DTOs for secure data sharing (`QuestionWrapper`)  
✅ Accept multiple question IDs and return corresponding questions  
✅ Validate user answers and return score  
✅ Persistent storage using **MySQL**

> This microservice is built to be stateless and loosely coupled, suitable for production use with REST communication.

---

## 🛠 Tech Stack

- Java 21  
- Spring Boot 3.5.3  
- Spring Web  
- Spring Data JPA  
- **MySQL**  
- IntelliJ IDEA

---

## 🧠 What I Learned

Building this microservice taught me how to:
- Isolate core business logic for quiz questions
- Securely send question data using DTOs (excluding answers)
- Use Spring Data JPA with MySQL for persistent data storage
- Create reusable and clean REST APIs for inter-service communication

This lays the foundation for building a complete system where `QuizService` can dynamically consume questions and validate user answers.

---

## 📂 Project Structure
```
question-Service/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/example/question_Service/
│       │       ├── Controller/
│       │       │   └── QuestionController.java
│       │       ├── DAO/
│       │       │   └── QuestionDao.java
│       │       ├── Model/
│       │       │   ├── Question.java
│       │       │   ├── QuestionWrapper.java
│       │       │   └── Response.java
│       │       ├── Service/
│       │       │   └── QuestionService.java
│       │       └── QuestionServiceApplication.java
│       └── resources/
│           ├── static/
│           ├── templates/
│           └── application.properties

```



