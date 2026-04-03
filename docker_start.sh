#!/bin/bash

# 定义基本参数
source .env

# 打包
# 如果是dev环境，先打包
# 如果是prod环境，先从远程拉取包
if [ APP_ENV = "dev" ]; then
    source docker_build.sh
elif [ APP_ENV = "prod" ]; then
    echo "从远程拉取包"
    docker pull sunkolin/${APP_NAME}:${APP_VERSION}
fi

# 停止并删除容器，再启动
sudo docker stop ${APP_NAME}
sudo docker rm -fv ${APP_NAME}
sudo docker run -d --name ${APP_NAME} -p ${APP_PORT}:${APP_PORT} sunkolin/${APP_NAME}:${APP_VERSION}