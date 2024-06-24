# Note Taking App - Server Side

This is the server side of a note-taking application. You can add a note, and view the full list of notes or by ID

## How to Run

### Package the code into a JAR file
```
./mvnw package -DskipTests
```

### Configure your .env file
Make sure your .env file is present in the root directory of this project. You can find an example .env file with sample values [here](/.env.example)

### Start the docker containers
```
docker compose up
```
## OpenAPI Specs
You can find the OpenAPI documentation [here](/openapi/swagger.yaml)