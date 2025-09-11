# PassForge API 🚀

A powerful and educational REST API built with Java and Spring Boot to analyze password strength and check for data breaches.

## About This Project

This project is a personal learning initiative to build a robust backend application from the ground up. The primary goals were to practice professional development workflows, understand layered architecture, and implement a scalable, rule-based logic engine.

---

## Core Features

* **Rule-Based Strength Analysis:** Password strength is calculated via a dynamic and scalable engine. New rules (e.g., for length, character variety, common passwords) can be easily added.
* **Data Breach Checking:** Integrates with the "Have I Been Pwned" (HIBP) API using the secure k-Anonymity model to check if a password has been exposed in a known data breach.
* **Actionable Suggestions:** Provides a list of concrete suggestions to help users improve their password security.
* **Unit Tested:** Core business logic is verified with JUnit 5 tests to ensure reliability.

---

## API Endpoints

### Analyze Password

Analyzes a given password for strength and breach status.

* **URL:** `/api/passwords/analyze`
* **Method:** `POST`
* **Request Body:**

    ```json
    {
        "password": "your_password_here"
    }
    ```

* **Success Response (200 OK):**

    ```json
    {
        "score": 70,
        "strengthLevel": "STRONG",
        "pwned": false,
        "suggestions": [
            "Consider making your password longer than 12 characters."
        ]
    }
    ```

---

## How to Run Locally

1.  **Prerequisites:**
    * Java 17 (or higher)
    * Apache Maven
    * Git

2.  **Clone the repository:**
    ```bash
    git clone [https://github.com/andryous/passforge.git](https://github.com/andryous/passforge.git)
    cd passforge
    ```

3.  **Run the application:**
    ```bash
    ./mvnw spring-boot:run
    ```
    The API will be available at `http://localhost:8080`.

## Technologies Used

* **Framework:** Spring Boot 3.5.5
* **Language:** Java 21
* **Build Tool:** Maven
* **Dependencies:**
    * Spring Web
    * Spring WebFlux (for `WebClient`)
    * Lombok
    * Jakarta Annotations API
* **Testing:** JUnit 5, Postman