# OOP_Egyetemi_tanulmanyrendszer

## Description

This project is a simple model of a university study system implemented in Java. It contains classes for people, students, teachers, courses, enrollments, and grades.

## Main Classes

- **Szemely** – an abstract base class containing the person's ID, name, and email address.
- **Hallgato** – represents a student and stores their enrollments.
- **Oktato** – represents a teacher.
- **Kurzus** – represents a course, its teacher, credit value, and maximum number of students.
- **Beiratkozas** – represents a student's enrollment in a course during a specific semester.
- **Jegy** – represents a grade.
- **Lekerdezesek** – contains queries implemented using the Stream API.

## Business Rules

- A student cannot enroll in a course if it is full.
- A student cannot enroll in the same course more than once during the same semester.
- A grade must be an integer between 1 and 10.
- The weighted average is calculated based on course credits.
- Credits are earned only when the grade is at least 5.
- The course pass rate is calculated using enrollments that already have a grade.

## Implementation Decisions

The `Szemely` class is abstract. The `Hallgato` and `Oktato` classes inherit from it.

The person's ID is used by the `equals()` and `hashCode()` methods to compare people.

Enrollments are stored in both the `Hallgato` and `Kurzus` classes because they are needed to calculate the student's weighted average and the course pass rate. Enrollments are created using the `beiratkozik()` method, which updates both lists.

The grade is optional and is represented using `Optional<Jegy>`. The internal lists cannot be modified directly from outside the classes because the getter methods return unmodifiable copies.

The semester is represented by a `String` because the assignment does not specify a required format.

## Stream API Queries

The project contains two Stream API queries:

- Finding the top three students in a course, ordered by their grades in descending order.
- Calculating the total number of credits taught by each teacher.

## Testing

The project uses JUnit 5 and contains eight tests covering:

- Enrollment in a full course.
- Preventing duplicate enrollment.
- Grade validation.
- Weighted average calculation.
- Earned credits calculation.
- Course pass rate calculation.
- The `equals()` and `hashCode()` methods.
- Stream API queries.

To run the tests, use the following command in the project directory:

```bash
mvn test
```

## UML Diagram

The `osztalydiagram.puml` file contains the UML class diagram of the implemented model, including inheritance, relationships, and multiplicities.

## Technologies Used

- Java 17
- Maven
- JUnit 5
- Stream API
- PlantUML
