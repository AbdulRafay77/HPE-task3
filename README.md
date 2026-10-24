# HPE Forage — Task Two: Basic RESTful Web Service

This is a simple Java Spring Boot web server for the Forage HPE Software Engineering simulation, Task Two.

## What it does

- Implements `GET http://localhost:8080/employees`
- Returns four hard-coded employees in valid JSON
- Contains the five requested core Java classes: `Employee`, `Employees`, `EmployeeManager`, `EmployeeController`, and `RestServiceApplication`
- Has no database, GreenLake deployment, or POST/PUT/DELETE because **Task Two does not require them**

## Requirements

- **JDK 17 or later** (not merely a JRE)
- **Gradle 8.4 or later from the 8.x series**. Recommended Gradle 8.14.x for Spring Boot 3.5.x. Install from https://gradle.org/install/ (follow Windows instructions).
- Internet access on first build to download Spring Boot dependencies from Maven Central

Run `java -version`, `javac -version`, and `gradle --version` from a terminal to confirm.

## Run on Windows (PowerShell or VS Code terminal)

Open the project root folder (the folder containing `build.gradle`) in VS Code, then run:

```powershell
gradle bootRun
```

Wait until the log reports that Tomcat started on port 8080. Keep that terminal running.

Open `http://localhost:8080/employees` in your browser, or in a second PowerShell terminal:

```powershell
Invoke-RestMethod http://localhost:8080/employees | ConvertTo-Json -Depth 5
```

## Test and build the executable

```powershell
gradle test
gradle bootJar
```

The executable is `build/libs/hpe-employee-service.jar`.
To stop `gradle bootRun`, press Ctrl+C, then run the executable:

```powershell
java -jar build/libs/hpe-employee-service.jar
```

Open `http://localhost:8080/employees` again.

If another program is using port 8080, stop it before testing to satisfy the Forage task's URL.

## Optional: generate the standard Gradle wrapper

If Gradle is installed, run once:

```powershell
gradle wrapper --gradle-version 8.14.3
```

Afterward you can use `./gradlew.bat bootRun` instead of the globally installed `gradle`. Include generated wrapper files when sharing your project if desired.

## Response example

```json
{
  "Employees": [
    {
      "employee_id": "EMP001",
      "first_name": "Ali",
      "last_name": "Khan",
      "email": "ali.khan@example.com",
      "title": "Software Engineer"
    },
    {
      "employee_id": "EMP002",
      "first_name": "Sara",
      "last_name": "Ahmed",
      "email": "sara.ahmed@example.com",
      "title": "Backend Developer"
    }
  ]
}
```

Two more employee entries appear in the actual response.

## Files and their purpose

| File | Responsibility |
|---|---|
| `Employee.java` | Models a single employee; JSON property annotations produce the exact snake_case property names. |
| `Employees.java` | Holds the entire list and serializes it under the `Employees` key. |
| `EmployeeManager.java` | Creates four hard-coded employee objects. |
| `EmployeeController.java` | Maps GET `/employees` to the manager's data. |
| `RestServiceApplication.java` | Starts the embedded Spring Boot server. |
| `build.gradle` | Declares Java, Spring Boot, and JUnit dependencies. |
| `EmployeeControllerTest.java` | Automated endpoint test (extra learning). |

## Submission

The Forage instructions ask for a ZIP with the **core source files**, not a running process or external database. This folder already contains those files; after running and checking the endpoint, zip the folder or upload the provided source ZIP. Leave `build/` and `.gradle/` caches out of the ZIP.

### Note on testing

This package was assembled and statically inspected in the preparation environment. A full Spring Boot/Gradle build must be run on a machine with dependency downloads available (as described above); do not claim a successful runtime test until you see the JSON response on your computer.
