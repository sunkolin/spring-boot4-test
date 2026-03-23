# 定义基本参数
source .env

# 打包
mvn clean install -Dmaven.test.skip=true

# 打镜像
sudo docker build -t sunkolin/$APP_NAME:$APP_VERSION -f Dockerfile .
sudo docker tag sunkolin/$APP_NAME:$APP_VERSION sunkolin/$APP_NAME:latest

# 停止
sudo docker ps -a -q --filter "name=$APP_NAME" |grep -q . && docker stop $APP_NAME && docker rm -fv $APP_NAME

# 启动
sudo docker run -itd --name="$APP_NAME" -p 8080:8080 sunkolin/$APP_NAME:$APP_VERSION java -jar -Xmx1G -Xmx1G -server -XX:+UseConcMarkSweepGC -XX:+UseCMSCompactAtFullCollection -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintGCTimeStamps -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/opt/$APP_NAME/ -Xloggc:~/opt/log/$APP_NAME-gc.log /opt/$APP_NAME/$APP_NAME-$APP_VERSION-SNAPSHOT.jar --spring.application.name=$APP_NAME --server.port=8080 --spring.profiles.active=dev