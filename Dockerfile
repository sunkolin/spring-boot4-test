FROM centos:7.9.2009

MAINTAINER 2025-05-15 sunkolin sunkolin@qq.com

# config
RUN cp -f /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone &&\
    alias ll="ls -al"

# software
RUN yum install -y wget gcc net-tools telnet.x86_64 xinetd.x86_64 &&\
    yum clean all

# java
RUN mkdir -p /opt/ &&\
    cd /opt/ &&\
    curl -L -O  -J -H "Cookie: key=value" -k "https://mirrors.huaweicloud.com/openjdk/17.0.2/openjdk-17.0.2_linux-x64_bin.tar.gz" &&\
    tar -zxvf openjdk-17.0.2_linux-x64_bin.tar.gz > /dev/null &&\
    rm -rf /opt/openjdk-17.0.2_linux-x64_bin.tar.gz &&\
    ln -s /opt/openjdk-17.0.2_linux-x64_bin /opt/java
ENV JAVA_HOME=/opt/java
ENV PATH=${PATH}:/opt/java/bin
RUN java -version

# arthas
RUN mkdir -p /opt/arthas/ &&\
    cd /opt/arthas/ &&\
    wget https://arthas.aliyun.com/arthas-boot.jar

# application
WORKDIR /opt/${APP_NAME}
ADD ./target/${APP_NAME}-1.0.0-SNAPSHOT.jar /opt/${APP_NAME}

# start
CMD java -jar -Xmx1G -Xmx4G -server -XX:+UseConcMarkSweepGC -XX:+UseCMSCompactAtFullCollection -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintGCTimeStamps -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/opt/logs/${APP_NAME}/ -Xloggc:~/opt/log/${APP_NAME}-gc.log /opt/${APP_NAME}/${APP_NAME}-1.0.0-SNAPSHOT.jar
