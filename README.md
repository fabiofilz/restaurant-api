# Delivery food - restaurant-api

```
docker pull mysql/mysql-server:latest
```

```bash
docker run -p 13306:3306 --name mysql-docker-local -eMYSQL_ROOT_PASSWORD=Password -d mysql:latest
```

```bash
docker ps -a
docker container ls
```

```bash
docker exec -it a488ddb49b3a sh
```

[//]: # (mysql --host=127.0.0.1 --port=13306 -u root -p)

```bash
docker container start 550f92677ffe 
```

```bash
docker container stop 550f92677ffe
docker container rm 550f92677ffe
```

```bash
docker compose up -d
```

```bash
docker compose stop
```

```bash
docker compose down
```