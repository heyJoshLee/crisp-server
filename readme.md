# To start
- Create proper database
- Update properties file
- Build project
- Launch

# Preparing Postgres (chatGPT)
To set up a local PostgreSQL database that your Spring Boot application can connect to with the specified configuration, follow these steps:

### 1. **Install PostgreSQL**
If you don’t have PostgreSQL installed yet, you can install it by following the instructions for your operating system:

- **Windows**: Download the installer from [PostgreSQL official website](https://www.postgresql.org/download/windows/).
- **macOS**: Use Homebrew: `brew install postgresql`.
- **Linux**: Use the package manager, for example:
    - Ubuntu/Debian: `sudo apt-get install postgresql postgresql-contrib`
    - CentOS/RHEL: `sudo yum install postgresql-server postgresql-contrib`

### 2. **Start PostgreSQL**
If PostgreSQL is installed but not running, start it:
- **Windows**: Usually starts automatically as a service.
- **macOS/Linux**:
  ```bash
  sudo service postgresql start
  ```

### 3. **Access PostgreSQL**
To interact with PostgreSQL, use the `psql` command-line tool:
- **Access PostgreSQL** (as `postgres` user):
  ```bash
  sudo -u postgres psql
  ```

### 4. **Create Database and User**

Inside the `psql` shell, execute the following commands:

#### a. Create the `crisp_mvp` database:
```sql
CREATE DATABASE crisp_mvp;
```

#### b. Create the user `crisp`:
```sql
CREATE USER crisp WITH PASSWORD 'crisp';
```

#### c. Grant permissions to the `crisp` user on the `crisp_mvp` database:
```sql
GRANT ALL PRIVILEGES ON DATABASE crisp_mvp TO crisp;
```

#### d. Optionally, make sure the user has the right privileges on tables in the database:
```sql
\c crisp_mvp -- Switch to the new database
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO crisp;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO crisp;
GRANT ALL PRIVILEGES ON ALL FUNCTIONS IN SCHEMA public TO crisp;
```

### 5. **Test the Connection**
Exit `psql` and try connecting to the database using the `psql` tool:
```bash
psql -h localhost -U crisp -d crisp_mvp
```
It will prompt you for the password (`crisp`), and if the connection is successful, you’re ready to proceed.

### 6. **Update Spring Boot Application Configuration**

Your `application.properties` file should already be correctly configured, but double-check the following entries for your Spring Boot application:

```properties
spring.application.name=mvp
spring.datasource.url=jdbc:postgresql://localhost:5432/crisp_mvp
spring.datasource.username=crisp
spring.datasource.password=crisp
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.hibernate.ddl-auto=create-drop
```

- **`spring.datasource.url`**: This points to your PostgreSQL database (`localhost:5432/crisp_mvp`).
- **`spring.datasource.username`** and **`spring.datasource.password`**: These correspond to the PostgreSQL user credentials (`crisp`).
- **`spring.jpa.hibernate.ddl-auto=create-drop`**: This setting will cause Hibernate to automatically generate your database schema and drop it when the application stops. You can change this to `update` or `validate` in a production environment, but for development purposes, `create-drop` is fine.

### 7. **Run Your Spring Boot Application**

Now, run your Spring Boot application. If everything is set up correctly, it should be able to connect to the `crisp_mvp` database on your local PostgreSQL instance.

- **Run the application**:
  ```bash
  ./mvnw spring-boot:run  # If using Maven
  # OR
  ./gradlew bootRun  # If using Gradle
  ```

### 8. **Verify the Connection**
If your application starts successfully, check the logs for any database connection errors. You should see Spring Boot initializing the database connection and schema creation (due to `ddl-auto=create-drop`).

#### Troubleshooting:
- If you get errors related to connection issues, make sure PostgreSQL is running and accessible on `localhost:5432`.
- Ensure that the user `crisp` has been granted sufficient privileges on the database.
- Check if any firewall or PostgreSQL configuration is blocking the connection.

That’s it! You’ve set up your local PostgreSQL database and Spring Boot application to connect to it.


# Things to improve

- Add validations
- Add log in / auth
- Clean up unused code
- Add methods to get resources and related resources with few queries