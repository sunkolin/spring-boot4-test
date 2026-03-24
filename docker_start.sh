# 定义基本参数
source .env

# 打包
mvn clean install -Dmaven.test.skip=true

# 打镜像
sudo docker build -t sunkolin/${APP_NAME}:${APP_VERSION} -f Dockerfile .
sudo docker tag sunkolin/${APP_NAME}:${APP_VERSION} sunkolin/${APP_NAME}:latest

# 停止
sudo docker ps -a -q --filter "name=${APP_NAME}" |grep -q . && docker stop ${APP_NAME} && docker rm -fv ${APP_NAME}

# 启动
sudo docker run -itd --name="${APP_NAME}" -p 8080:8080 sunkolin/${APP_NAME}:${APP_VERSION}