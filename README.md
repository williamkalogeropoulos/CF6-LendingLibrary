# Cf6FinalProject
# Lending Library Application
This is a Spring Boot application for managing a book lending library. Follow the steps below to set up and run the application.
## Steps to Set Up the Application
### 1. Create the Database
Create a MySQL database named `book_lending`:
```sql
CREATE DATABASE book_lending;
```
### 2. Configure the Database Connection
Update the following properties in the `application.properties` file located in `src/main/resources/`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/book_lending
spring.datasource.username=youruser
spring.datasource.password=yourpassword
```
- Replace `youruser` with your MySQL username.
- Replace `yourpassword` with your MySQL password.
## Running the Application
After completing the above steps, you can start the application using the following command:
```bash
mvn spring-boot:run
```
The application will be accessible at:
```
http://localhost:8080
```
### Additional Notes
- Ensure your MySQL server is running before starting the application.
- The application uses the `book_lending` database. Make sure the user specified in the configuration has the necessary permissions.