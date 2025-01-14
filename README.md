# Cf6FinalProject
# CF6 Lending Library Application
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

### Tabs & Their Functions:
1. **Home (`index.html`)** - The main page, providing an overview of the library system.
2. **Search Books (`books.html`)** - Search through a list of available books (ISBN link provides further book information)
3. **Manage Borrowed Books (`borrowings.html`)** - Shows currently borrowed books (with admin privileges).
4. **Borrowed Books (`my-borrowings.html`)** - Displays books borrowed by the logged-in user.
5. **Manage Books (`manage-books.html`)** - Allows administrators to add, edit, or delete books.
6. **Manage Users (`manage-users.html`)** - Enables administrators to add, edit, or delete user accounts.
7. **Login (`login.html`)** - Handles user authentication.
8. **Register (`register.html`)** - Allows new users to create an account.
9. **Forgot Password (`forgot-password.html`)** - Sends a password reset link.
10. **Reset Password (`reset-password.html`)** - Enables users to set a new password.
11. **Edit Book (`edit-book.html`)** - Allows editing of book details.
12. **Contact Us (`Contact-us.html`)** - Displays a contact form.
13. **About Us (`about-us.html`)** - Provides information about the project.

### Project Analysis:
- **Tech Stack**: Spring Boot (backend), Thymeleaf (frontend), MySQL (database).
- **Security**: Implemented via `SecurityConfig.java`, using Spring Security.
- **Database**: Uses repositories (`BookRepository.java`, `UserRepository.java`) for database interactions.
- **Services**: Business logic handled in `BookService.java`, `UserService.java`, etc.
- **Exception Handling**: Centralized via `GlobalExceptionHandler.java`.

### Additional Notes
- Ensure your MySQL server is running before starting the application.
- The application uses the `book_lending` database. Make sure the user specified in the configuration has the necessary permissions.

