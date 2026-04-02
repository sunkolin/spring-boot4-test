export APP_ENV=dev
export APP_PORT=6005

java -Xms1G -Xmx2G -jar /app/*.jar --spring.profiles.active=${APP_ENV} --server.port=${APP_PORT}