# Run Backend Correctly In VS Code

Do not click **Run Code** from the Code Runner extension for this Spring Boot app.

That command runs only:

```bash
javac WellnestBackendApplication.java
```

Spring Boot needs Maven dependencies from `pom.xml`, so run it using one of these methods.

## Method 1: Java Run Button

1. Open this folder in VS Code workspace: `Backend - Spring Boot`
2. Wait until Java finishes importing the Maven project.
3. Open:

```text
src/main/java/com/wellnest/api/WellnestBackendApplication.java
```

4. Click the **Run** link above the `main` method, not the Code Runner button.

## Method 2: Terminal

Open a terminal in:

```text
c:\Users\bhara\OneDrive\Documents\Updated project\wellnest-backend
```

Then run:

```bash
mvn spring-boot:run
```

If `mvn` is not recognized, install Maven or use the Java Run button after installing the recommended VS Code Java extensions.

## Correct Success Message

Wait until you see:

```text
Started WellnestBackendApplication
```

Then the API is running at:

```text
http://localhost:8081
```
