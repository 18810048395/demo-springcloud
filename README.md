#springCloudAlibaba
##nacos配置
    docker run -d -p 8848:8848 \
    --name nacos \
    --env MODE=standalone \
    --env SPRING_DATASOURCE_PLATFORM=mysql \
    --env MYSQL_SERVICE_HOST=192.168.111.10 \
    --env MYSQL_SERVICE_PORT=3306 \
    --env MYSQL_SERVICE_DB_NAME=demo_springcloud \
    --env MYSQL_SERVICE_USER=root \
    --env MYSQL_SERVICE_PASSWORD=root \
    -v /usr/local/docker/nacos/conf:/home/nacos/conf \
    -v /usr/local/docker/nacos/logs:/home/nacos/logs \
    -v /usr/local/docker/nacos/data:/home/nacos/data \
    nacos/nacos-server