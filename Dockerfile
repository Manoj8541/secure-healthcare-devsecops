# ================================
# STAGE 1 — BUILD STAGE
# ================================
FROM eclipse-temurin:17-jdk-alpine AS builder

# Set working directory
WORKDIR /app

# Copy maven files first (better caching)
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn

# Download dependencies first (cached layer)
RUN ./mvnw dependency:go-offline -B

# Copy source code
COPY src ./src

# Build application
RUN ./mvnw clean package -DskipTests -B

# ================================
# STAGE 2 — RUN STAGE
# ================================
FROM eclipse-temurin:17-jre-alpine AS runner

# Security — create non-root user
RUN addgroup -S healthgroup && \
    adduser -S healthuser -G healthgroup

# Set working directory
WORKDIR /app

# Copy built jar from builder stage
COPY --from=builder /app/target/*.jar app.jar

# Change ownership to non-root user
RUN chown -R healthuser:healthgroup /app

# Switch to non-root user
USER healthuser

# Expose port
EXPOSE 9090

# Health check
HEALTHCHECK --interval=30s \
            --timeout=10s \
            --start-period=30s \
            --retries=3 \
            CMD wget --no-verbose \
                --tries=1 \
                --spider \
                http://localhost:9090/actuator/health \
                || exit 1

# Run application
ENTRYPOINT ["java", \
            "-XX:+UseContainerSupport", \
            "-XX:MaxRAMPercentage=75.0", \
            "-Djava.security.egd=file:/dev/./urandom", \
            "-jar", \
            "app.jar"]