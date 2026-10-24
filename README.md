# HPE Forage Software Engineering — Task Three: Implement the Ability to Upload Data

This project is an update of the Task Two Spring Boot employee API. The original five
Java source classes are still here; three have been updated to support POST requests.

## Routes

| Method | URL | Purpose | Expected status |
| --- | --- | --- | --- |
| GET | http://localhost:8080/employees | Return all employees | 200 OK |
| POST | http://localhost:8080/employees | Create an employee from JSON | 201 Created |

The application starts with four sample employees. POST adds to the in-memory list
while the server stays running. Because there is no database yet, posted employees
disappear when the application restarts.

## Java requirements
- JDK 17+ (tested previously by the user with Java 21)
- Gradle 8.14.6, matching the user's existing installation

## On Windows — reconnect a Gradle binary that is already downloaded

Open PowerShell in the directory containing `build.gradle` and run:

```powershell
$gradle = Get-ChildItem 'C:\Users\PMLS\Downloads\gradle-8.14.6-bin' -Filter gradle.bat -Recurse -File | Select-Object -First 1
$env:Path = "$($gradle.DirectoryName);$env:Path"
gradle --version
java -version
```

`gradle --version` should report Java 21 for the Gradle JVM. If it reports a newer
incompatible JDK, select the installed JDK 21 by setting JAVA_HOME before running Gradle.

Optionally generate the Gradle wrapper once: `gradle wrapper --gradle-version 8.14.6`.
The project can then run with `.\gradlew.bat bootRun`, provided Java is configured.

## Start the service

```powershell
gradle bootRun
```

Leave it running in the first terminal. In a second PowerShell terminal, GET employees:

```powershell
Invoke-RestMethod -Method GET -Uri 'http://localhost:8080/employees' | ConvertTo-Json -Depth 6
```

POST one new employee:

```powershell
$body = @{
  employee_id = 'EMP005'
  first_name  = 'Zain'
  last_name   = 'Iqbal'
  email       = 'zain.iqbal@example.com'
  title       = 'DevOps Engineer'
} | ConvertTo-Json

Invoke-RestMethod -Uri 'http://localhost:8080/employees' -Method POST -ContentType 'application/json' -Body $body
```

GET again — it should now include the fifth employee.

## Test and package

Stop the server with Ctrl+C, then run:

```powershell
gradle clean test bootJar
java -jar .\build\libs\hpe-employee-service.jar
```

Check GET/POST again if desired. The executable JAR is for testing; the Forage
submission requests a ZIP of the source files.

## Key source code differences from Task Two

- `Employee.java`: now has a zero-argument constructor and getters + setters for JSON deserialization.
- `EmployeeManager.java`: `addEmployee()` adds data to the in-memory list.
- `EmployeeController.java`: `@PostMapping` and `@RequestBody` implement POST.
- `Employees.java` and `RestServiceApplication.java` are unchanged.

**Scope**: No database, PUT, DELETE, file-upload endpoint, or permanent storage.
This task uploads an employee JSON object (not a disk file).
