# Library Management System API

A RESTful API for managing books and members in a library using Java, Spring Framework, Hibernate, and PostgreSQL.

---

## Features

- Manage Books and Members (Create, Read, Update, Delete)
- Borrow and Return Books with availability tracking
- Prevent deletion of books if borrowed or reserved
- Prevent deletion of members if they have borrowed books
- JSON request and response format

---

## Technologies

- Java 21
- Spring Framework 6 (Spring MVC, Spring Data JPA)
- Hibernate 6
- PostgreSQL
- Maven
- Jakarta Servlet 6

---

## Setup Instructions

1. Clone the repository:
   ```bash
   git clone https://github.com/PraneethJay/LibraryManagementSystem.git
   cd LibraryManagementSystem
