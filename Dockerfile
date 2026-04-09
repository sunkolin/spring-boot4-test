FROM debian:slim

LABEL maintainer="sunkolin <sunkolin@qq.com>"

# 设置时区
RUN apt-get update && apt-get install -y --no-install-recommends tzdata
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# 替换阿里云源
RUN sed -i 's/deb.debian.org/mirrors.aliyun.com/g' /etc/apt/sources.list.d/debian.sources

# 设置别名
RUN echo "alias ll='ls -al'" >> /etc/profile
ENV ENV="/etc/profile"

# curl,wget,telnet,net-tools,java
RUN apt-get update && apt-get install -y --no-install-recommends \
    curl \
    wget \
    telnet \
    net-tools \
    openjdk-17-jre \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/*

# java
ENV JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
ENV PATH=$PATH:$JAVA_HOME/bin

# 部署应用
RUN mkdir -p /app
COPY ./target/*.jar /app/

# 启动
CMD ["sh", "-c", "java -Xms1G -Xmx2G -jar /app/*.jar --spring.profiles.active=${APP_ENV:-dev} --server.port=${APP_PORT:-8080}"]