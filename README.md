# Kassa Application

## Requirements
- Java 23.0.2-oracle (Use SDKMAN: `sdk env install` to set up the correct Java version)

## Running with Docker
```bash
docker-compose up -d --build
```

## Services
- **Main Application**: http://localhost:8080
  - API Documentation: http://localhost:8080/swagger-ui/index.html
  - API Docs JSON: http://localhost:8080/v3/api-docs
- **Prometheus**: http://localhost:9090
- **Alertmanager**: http://localhost:9093
- **Grafana**: http://localhost:3000
  - Default credentials: admin/admin