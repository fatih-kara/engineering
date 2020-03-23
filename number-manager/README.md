**Build a docker image**

mvn install dockerfile:build

**Run images with docker compose**

docker-compose up

**Connect to mongodb inside the container**

> docker exec -it api-database bash

> mongo

**See the records**

> use records

> db.numbers.find()