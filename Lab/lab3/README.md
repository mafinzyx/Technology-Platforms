# Laboratorium 5 & 6

---

## 🧪 Laboratorium 5 – JPA & Hibernate

### 📚 Project Overview

This project is part of a university lab designed to introduce students to the fundamentals of object-relational mapping using the **Java Persistence API (JPA)**. The implementation uses **Hibernate** as the JPA provider and **H2** as an in-memory database.

The goal is to build a **text-based Java application** that allows users to manage two related entity classes in a **one-to-many** relationship.

### 🔧 Technologies Used

- Java
- JPA (Java Persistence API)
- Hibernate (JPA implementation)
- H2 Database (in-memory)
- Maven (project management)

### 🧩 Objectives

1. **Entity Modeling**
   - Implementation of at least **two entity classes**
   - Configuration of a **persistence unit** that automatically generates database tables

2. **Database Operations**
   - Add new entries (with relationship handling)
   - Delete entries
   - Display all or a limited number of entries

3. **Custom Queries**
   - Five different JPQL queries
   - Example:
     - All employees over 30
     - Top 10 employees with longest tenure

4. **Initial Data**
   - Test data saved into DB on startup

### 📁 Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/example/
│   │       ├── entity/
│   │       ├── repository/
│   │       └── Main.java
│   └── resources/
│       └── META-INF/
│           └── persistence.xml
```

### 🚀 Getting Started

```bash
git clone https://github.com/mafinzyx/Technology-Platforms.git
cd Technology-Platforms
mvn clean install
mvn exec:java -Dexec.mainClass="com.example.Main"
```

---

## 🧪 Laboratorium 6 – Unit Testing with JUnit & Mockito

### 🎯 Goal

This lab focuses on writing **unit tests** using **JUnit** and **Mockito**. Use **AssertJ** or **Hamcrest** for assertions. The project simulates managing an in-memory collection of entities, with logic separated into:

- Repository
- Controller

### 🧱 Components

#### Repository
- Manages an in-memory collection
- Exposes CRUD-like methods
- Based on structure from Lab 5
- **Specifications**:
  - Removing a non-existing item → throws `IllegalArgumentException`
  - Retrieving a non-existing item → returns `Optional.empty()`
  - Retrieving an existing item → returns `Optional.of(obj)`
  - Saving an item with existing key → throws `IllegalArgumentException`

#### Controller
- Uses dependency injection to get the repository
- Accepts/returns `String` values, which map to entity objects
- **Specifications**:
  - Remove existing → `"done"`
  - Remove non-existing → `"not found"`
  - Get non-existing → `"not found"`
  - Get existing → stringified entity
  - Save new → `"done"`
  - Save with existing key → `"bad request"`

### 📝 Tasks

1. Implement:
   - Entity class
   - DTO
   - Repository
   - Controller
   - Make sure return values and exception handling align with spec

2. **Test the Repository**
   - All specified test scenarios

3. **Test the Controller**
   - All specified test scenarios
   - Use mocked repository (`Mockito`)

### 🔍 Tools Used

- JUnit 5
- Mockito
- AssertJ or Hamcrest
- Java 17+
- Maven

---