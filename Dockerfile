# Stage 1: Build the application using Maven
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
# Bỏ qua test khi build, vì chúng ta đã có job test riêng trong CI
RUN mvn clean install -DskipTests

# Stage 2: Create the final, smaller image
FROM openjdk:17-jdk-slim
WORKDIR /app
# Lấy file jar từ stage build
COPY --from=build /app/target/*.jar app.jar
# Expose port mà ứng dụng sẽ chạy (lấy từ biến môi trường)
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]