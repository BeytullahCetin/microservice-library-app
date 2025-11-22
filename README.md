# Microservice Library App

## Docker environment

Requirements:
- Docker 27+ with Compose plugin
- JDK/java tooling only if you plan to build locally outside containers

### Build & run

```bash
cd /Users/ardacoha/Documents/GitHub/microservice-library-app
docker compose build
docker compose up -d
```

The stack exposes the following ports on `localhost`:
- Config Server `8787`
- Discovery Server `8761`
- Gateway Server `8888`
- Book `8081`, Customer `8082`, Borrow `8083`, Review `8086` (internal 8083), Fine `8085`
- Kafka `9094` (internal 9092) and Kafka UI `8079`
- PostgreSQL instances `5432-5436`

Configuration files are served from `./configurations`. To stop and clean up containers and networks run `docker compose down`.
