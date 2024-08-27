# **MediCare Hub**


## **Overview**

The MediCare Hub is a web application designed to manage various aspects of a healthcare facility. Built using Spring Boot and Thymeleaf, it provides functionalities for managing users (patients and doctors), appointments, and departments. The system includes role-based access for Admins, Doctors, and Users.

## **Features**

* **Role-Based Authentication:** Custom login handling with role-based redirection.

* **User Management:** Admins can manage users, assign roles, and view user details.

* **Appointment Management:** Users can request appointments, and both users and doctors can view and update appointment statuses.

* **Department Management:** Admins can create and manage departments and assign doctors to departments.

* **Profile Management:** Users, doctors, and admins can view and update their profiles.

## **Technologies Used**

* **Backend:** Spring Boot, Spring Security, Spring Data JPA

* **Frontend:** Thymeleaf, HTML, CSS

* **Database:** MySQL

* **Security:** BCryptPasswordEncoder for password hashing, custom authentication success handler

## **Prerequisites**

* Java 17 or later

* Maven

* IDE (e.g.,Eclipse)

* MySQL Database



## Setup

### Cloning the Repository

```bash
git clone https://github.com/your-repository-url.git
cd your-repository-directory
```

### Building the Project

Ensure Maven is installed and build the project using:

```bash
mvn clean install
```

### Running the Application

You can run the application using:

```bash
mvn spring-boot:run
```

Alternatively, you can run the generated JAR file:

```bash
java -jar target/your-project-name.jar
```

## Configuration

### Application Properties

Configure application settings in the `src/main/resources/application.properties` file. Here are the key configurations:

```properties
# Database configuration
spring.datasource.url=jdbc:mysql://localhost:3306/medicarehub
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.jpa.hibernate.ddl-auto=update

# Server configuration
server.port=8090

# Thymeleaf configuration
spring.thymeleaf.prefix=classpath:/templates/
spring.thymeleaf.suffix=.html
spring.thymeleaf.mode=HTML
spring.thymeleaf.encoding=UTF-8

# Application name
spring.application.name=MediCare_Hub
```

### Security Configuration

The application uses Spring Security for authentication and authorization. Roles are managed through the `SecurityConfig` class, and the `CustomSuccessHandler` redirects users based on their roles after login.

## Endpoints

### Public Endpoints

- **Home Page:** `/` - Home page for the application.
- **Login:** `/signin` - Login page.
- **Register:** `/register` - Registration page.

### User Endpoints

- **Request Appointment:** `/user/request-form` - Form to request an appointment.
- **Appointment List:** `/user/appointment-list` - View all appointments for the logged-in user.
- **Profile:** `/user/profile` - View and update user profile.
- **Doctor List:** `/user/doctors` - View list of doctors.

### Doctor Endpoints

- **Home:** `/doctor/` - Home page for doctors.
- **Appointment List:** `/doctor/appointment-list` - View appointments for the logged-in doctor.
- **Profile:** `/doctor/profile` - View and update doctor profile.

### Admin Endpoints

- **Home:** `/admin/` - Admin dashboard.
- **Manage Users:** `/admin/manage-users` - Manage and update user roles.
- **Appointment List:** `/admin/appointment-list` - View all approved appointments.
- **Department List:** `/admin/department-list` - List all departments.
- **Create Department:** `/admin/create-department` - Form to create a new department.
- **Assign Department:** `/admin/assign-department` - Form to assign departments to doctors.

## Database Schema

The application uses JPA entities to manage the database schema. The main entities include:

- **UserDetail:** Represents users (both doctors and patients).
- **Appointment:** Represents appointments between users and doctors.
- **Department:** Represents different departments in the healthcare system.

## Code Structure

- `com.example.entity:` Contains JPA entities.
- `com.example.repository:` Contains JPA repositories.
- `com.example.service:` Contains service layer interfaces and implementations.
- `com.example.config:` Contains configuration classes.
- `com.example.controller:` Contains Spring MVC controllers.

## Testing

Unit tests and integration tests can be added to the `src/test/java` directory. Use Maven to run tests:

```bash
mvn test
```

## License
This project is licensed under the MIT License. See the LICENSE file for details.

## Contact
For questions or feedback, please contact dhruvbansal0505@gmail.com .

---

