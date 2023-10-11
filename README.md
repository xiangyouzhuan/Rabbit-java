# RabbitMQ-java

这是一个用java实现的RabbiMQ中Send和Receive程序的demo，在使用前需要安装配置好RabbitMQ,并确保其能正常使用

# 环境要求
* Java 17
* Apache Maven 3.8.7

## 运行consumer程序
```bash
git clone git@github.com:xiangyouzhuan/RabbitMQ-java.git
cd RabbitMQ-java
```

为测试本项目，在RabbitMQ运行的主机上执行以下命令用于创建user和virtual host
```bash
rabbitmqctl add_user 'username' '2a55f70a841f18b97c3a7db939b7adc9e34a0f1b'

rabbitmqctl add_vhost qa1
```



更改配置文件Recv/src/main/resources/application.properties，
指定RabbitMQ运行主机的
```bash
host=
port=
virtualHost=qa1
QUEUE_NAME=hello

userName=username
password=2a55f70a841f18b97c3a7db939b7adc9e34a0f1b
```

运行程序
```bash
cd Recv
mvn spring-boot:run
```

## 运行Send程序
打开另一个终端

更改配置文件Send/src/main/resources/application.properties，
指定RabbitMQ运行主机的
```bash
host=
port=
virtualHost=qa1
QUEUE_NAME=hello

userName=username
password=2a55f70a841f18b97c3a7db939b7adc9e34a0f1b
```
```bash
cd Send
mvn spring-boot:run
```

输入需要发送到topic的信息
```bash
hello!
```
producer终端显示
```bash
Send: 'hello!' to QUEUE:hello
```

回到consumer程序运行的终端，终端输出
```bash
 [*] Waiting for messages. To exit press CTRL+C
Received 'hello!'from QUEUE:hello

```





