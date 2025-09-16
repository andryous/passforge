# PassForge - Backend API

[![Java](https://img.shields.io/badge/Java-21-blue.svg)](https://www.java.com) [![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.5-brightgreen.svg)](https://spring.io/projects/spring-boot) [![Maven](https://img.shields.io/badge/Maven-3.9-red.svg)](https://maven.apache.org/)

A powerful and secure API for password strength analysis, featuring integration with the Have I Been Pwned database.

---
### ► Key Features

* **Scalable Rule Engine:** Analyzes password strength based on a configurable set of rules (length, character variety, common passwords).
* **Secure HIBP Integration:** Checks passwords against the 'Have I Been Pwned' database using the **k-Anonymity** model to protect user privacy.
* **Comprehensive Analysis:** Returns a detailed response including a numeric score, a strength level, pwned status, and actionable suggestions.
* **Unit Tested:** Core business logic is validated with JUnit 5 tests.

---
### ► Tech Stack

* **Backend:** Java 21, Spring Boot 3
* **Build:** Apache Maven
* **Testing:** JUnit 5

---
### ► Getting Started

#### 1. Prerequisites
* Java JDK 21
* Maven 3.9+

#### 2. Clone & Build
```bash
git clone https://github.com/andryous/passforge-backend.git
cd passforge-backend
./mvnw clean install
```

#### 3. Run the Application
This project requires no special environment configuration to run.
```bash
./mvnw spring-boot:run
```
The application will start on http://localhost:8080.

### ► API Endpoint
The API exposes a single endpoint for password analysis.  
URL: POST /api/passwords/analyze  

Request Body:
```json
{
"password": "your_password_to_test"
}
```
Success Response (200 OK):

```properties
{
"score": 85,
"strengthLevel": "VERY_STRONG",
"pwned": false,
"suggestions": [
"Add symbols (e.g., !@#$%) to your password."
]
}
```

### 👤 Author
* **Claudio Rodriguez**