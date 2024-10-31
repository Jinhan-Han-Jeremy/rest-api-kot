# 베이스 이미지로 OpenJDK 사용
FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY build/libs/kotlin-blog-0.0.1-SNAPSHOT.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]