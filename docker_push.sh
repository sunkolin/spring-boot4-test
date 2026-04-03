#!/bin/bash

# 定义基本参数
source .env

# 打包
source docker_build.sh

# 推送
sudo docker login -u sunkolin -p 'XiaoZhi888+'
sudo docker push sunkolin/${APP_NAME}:${APP_VERSION}
sudo docker push sunkolin/${APP_NAME}:latest