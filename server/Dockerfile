# ─── Stage 1: Build ──────────────────────────────────────────────────────────
FROM eclipse-temurin:17-jdk AS builder

WORKDIR /app

# Copy Gradle wrapper and config first (better layer caching)
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

# Ensure gradlew is executable
RUN chmod +x ./gradlew

# Download dependencies (cached unless build files change)
RUN sh ./gradlew dependencies --no-daemon || true

# Copy source and build the fat JAR
COPY src src
RUN sh ./gradlew bootJar --no-daemon

# ─── Stage 2: Run ────────────────────────────────────────────────────────────
FROM eclipse-temurin:17-jre

WORKDIR /app

# Create a non-root user for security
RUN groupadd -r appgroup && useradd -r -g appgroup appuser

# Copy the fat JAR from the build stage
COPY --from=builder /app/build/libs/*.jar app.jar

RUN chown appuser:appgroup app.jar

USER appuser

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
