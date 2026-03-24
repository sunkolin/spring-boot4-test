FROM alpine:latest

LABEL maintainer="sunkolin <sunkolin@qq.com>"

# 设置时区
RUN echo "Asia/Shanghai" > /etc/timezone
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

# 设置ll别名（全局生效）
RUN echo "alias ll='ls -al'" >> /etc/profile
ENV ENV="/etc/profile"

# 替换阿里云源
RUN sed -i 's/dl-cdn.alpinelinux.org/mirrors.aliyun.com/g' /etc/apk/repositories

# 安装wget，gcc，net-tools，telnet，xinetd，bash，openjdk17，curl
RUN apk add --no-cache \
    wget \
    gcc \
    net-tools \
    telnet \
    xinetd \
    bash \
    curl \
    openjdk17

# 清理apk缓存，减小镜像体积
RUN rm -rf /var/cache/apk/*

# 配置JDK17
RUN ln -sf /usr/lib/jvm/java-17-openjdk /usr/lib/jvm/java-17
ENV JAVA_HOME=/usr/lib/jvm/java-17
ENV PATH=$PATH:$JAVA_HOME/bin

# 启动项目
ENV APP_NAME=spring-boot4-test
ENV APP_PORT=8080
ENV APP_ENV=dev
ENV APP_VERSION=1.0.0
RUN mkdir -p /app
COPY ./target/*.jar /app/
CMD ["sh", "-c", "java -Xms1G -Xmx2G -jar /app/*.jar --spring.profiles.active=${APP_ENV} --server.port=${APP_PORT}"]

