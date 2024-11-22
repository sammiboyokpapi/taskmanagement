
# Task Management Application

This is a Task Management application built using Spring Boot.

## Table of Contents

1. [Getting Started](#getting-started)
   - [Prerequisites](#prerequisites)
   - [Installation](#installation)
2. [API Endpoints](#api-endpoints)
3. [Usage](#usage)
4. [Testing](#testing)
5. [Exception Handling](#exception-handling)

## Getting Started

These instructions will help you set up the project on your local machine.

### Prerequisites

To run this project, you need:

- Java 11 or higher
- Maven (for building the project)
- IDE (e.g., IntelliJ IDEA, Eclipse, or Visual Studio Code)

### Installation

1. Clone the repository:
    ```bash
    git clone <repository-url>
    ```

2. Navigate to the project directory:
    ```bash
    cd taskmanagement
    ```

3. Build the project with Maven:
    ```bash
    mvn clean install
    ```

4. Run the application:
    ```bash
    mvn spring-boot:run
    ```

## API Endpoints

Here are the available API endpoints:

| Method | Endpoint         | Description             | Request Body                                      |
|--------|------------------|-------------------------|--------------------------------------------------|
| POST   | /tasks            | Create a new task       | `{ "title": "Task Title", "description": "Task Description", "status": "PENDING" }` |
| GET    | /tasks            | Get all tasks           | None                                             |
| GET    | /tasks/{id}       | Get a task by ID        | None                                             |
| PUT    | /tasks/{id}       | Update a task by ID     | `{ "title": "Updated Title", "description": "Updated Description", "status": "COMPLETED" }` |
| DELETE | /tasks/{id}       | Delete a task by ID     | None                                             |

## Usage

After setting up the project and running it, you can use the endpoints via Postman, curl, or any API client. You can create, read, update, or delete tasks by sending HTTP requests to the endpoints.

Example: To create a new task, send a POST request to `/tasks` with the following JSON in the body:
```json
{
  "title": "New Task",
  "description": "This is a new task.",
  "status": "PENDING"
}
```

## Testing

To run tests for the application:

1. Run the following Maven command to execute the unit tests:
    ```bash
    mvn test
    ```

2. The tests will be run, and you can check the results in the console.

## Exception Handling

The application includes a global exception handler for various types of exceptions:

- **MethodArgumentNotValidException**: Handles validation errors for request bodies.
- **HttpMessageNotReadableException**: Handles issues with the readability of request payloads (e.g., invalid JSON).
- **TaskNotFoundException**: Handles errors when a task is not found in the database.
- **Generic Exception**: Catches unexpected errors and returns a 500 Internal Server Error.


