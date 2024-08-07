#Spring Приложение с REST API
Spring приложение с регистрацией пользователя, авторизацией, прием заявок и выдачу списка новостей с использованием RestAPI


Как запустить app/приложение:

Откройте терминал и перейдите в корневую директорию проекта
```bash
docker compose up --build 
```
Оно загрузит mvn clean package и java -jar /target/demo-0.0.1-SNAPSHOT.jar с Dockerfile
и запустит приложение на localhost:8080

Dockerfile выглдяит примерно так:
```bash
FROM maven:3.9.6-eclipse-temurin-17 AS build
COPY src /src
COPY pom.xml /
WORKDIR /
RUN mvn clean package

FROM openjdk:17-jdk
COPY --from=build /target/demo-0.0.1-SNAPSHOT.jar /usr/local/lib/app-1.0.0.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/app-1.0.0.jar"]
```


### API Endpoints

```plaintext
- POST /register - Регистрация нового пользователя и выдача JWT токена {
	"firstName":"andrew",
	"lastName":"andrew",
	"password":"1234",
	"username":"andrew@gmail.com",
	"role":"USER"
}
Роли: USER или ADMIN


- POST /login - Аутентификация/Логин пользователя и выдача JWT токена
{
	"username":"gloria@gmail.com",
	"password":"1234"
}

- POST /applications - Создание заявки (доступ только для role: ADMIN)
{
	"product":"prod4",
	"quantity":10,
	"delivery_address":"IT street",
	"phone_number":"87778988888"
}

- GET /applications - Получение заявки (доступ только для role: ADMIN)


- POST /news - Создание новости (доступ только для role: ADMIN)
{
	"title":"Sherlock",
	"content":"rocks"
}

- GET /news - Получение новости (доступ только для role: ADMIN)

- GET /users - Получение всех юзеров в базе данных (доступ только для role: ADMIN)

- GET /users/{id} - Получение конкретного юзера в базе данных (доступ только для role: ADMIN)
```
