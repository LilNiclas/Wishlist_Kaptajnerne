FROM lakruzz/lamj:latest

ENV PORT=8080
ENV MYSQL_PORT=3306

COPY src /src
COPY pom.xml /pom.xml
RUN set -ex; \
     mvn -f /pom.xml clean package; \
     mv /target/*.jar /app/; \
     rm -rf /target; \
     rm -rf /src; \
     rm -rf /pom.xml;

COPY src/mysql/init/* /docker-entrypoint-initdb.d/

EXPOSE $PORT $MYSQL_PORT

CMD set -eux; \
    lamj.init.sh; \
    java -jar /app/*.jar;

docker build  -t wishu .
docker run -it --rm --name wishu --pid=host -p 8080:8080 -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root wishu
