FROM maven:3-openjdk-17

WORKDIR /app

COPY pom.xml .

COPY src ./src

RUN mvn clean package

RUN mvn dependency:go-offline

COPY . .

RUN mvn package -DskipTests

# ENV SPRING_DATASOURCE_URL=jdbc:h2:mem:lanchonete
# ENV SPRING_DATASOURCE_USERNAME=sa
# ENV SPRING_DATASOURCE_PASSWORD=

EXPOSE 8080

ENV MYSQL_HOST=postech
ENV MYSQL_ROOT_PASSWORD=postech
ENV MYSQL_PORT=3306
ENV MYSQL_DATABASE=lanchonete
ENV MYSQL_USER=sa
ENV MYSQL_PASSWORD=postech

ADD /target/lanchonete-0.0.1-SNAPSHOT.jar lanchonete.jar

ENTRYPOINT ["java", "-jar", "lanchonete.jar"]


# docker build -t lanchonete .
# docker network create springboot-mysql-net
# docker run -p 8080:8080 --name lanchoneteAPI lanchonete:latest /bin/bash
# docker build -t springbootmysql .
# docker run --name mysql -p 8080:8080 -d springbootmysql -e MYSQL_HOST=postech -e MYSQL_ROOT_PASSWORD=postech -e MYSQL_PORT=3306 -e MYSQL_DATABASE=lanchonete -e MYSQL_USER=sa -e MYSQL_PASSWORD=postech mysql:8.0.33