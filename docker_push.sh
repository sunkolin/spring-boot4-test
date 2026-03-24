# 定义基本参数
source .env

# 打包
mvn clean install -Dmaven.test.skip=true

# 打镜像
sudo docker build -t sunkolin/${APP_NAME}:$APP_VERSION} -f Dockerfile .
sudo docker tag sunkolin/${APP_NAME}:${APP_VERSION} sunkolin/${APP_NAME}:latest
sudo docker login -u sunkolin -p 'XiaoZhi888+'
sudo docker push sunkolin/${APP_NAME}:${APP_VERSION}
sudo docker push sunkolin/${APP_NAME}:latest