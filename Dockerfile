FROM openjdk:17

COPY target/BakendProject-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8085
# Install dependencies
RUN pip install --no-cache-dir -r requirements.txt

# Copy the rest of the application code
COPY . .

ENV SERVER_PORT=8085

ENTRYPOINT ["java", "-jar", "app.jar"]