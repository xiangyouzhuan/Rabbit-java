# kafka-java

这是一个用java实现的RabbiMQ中Send和Receive程序的demo，在使用前需要安装配置好Rabbit,并确保其能正常使用

# 环境要求
* Java 17
* Apache Maven 3.8.7

## 运行consumer程序
```bash
git clone git@github.com:xiangyouzhuan/kafka-java.git
cd kafka-java
```

更改配置文件consumer/src/main/resources/application.properties，指定kafka运行主机的ip和端口，以及topic

运行程序
```bash
cd consumer
mvn spring-boot:run
```

## 运行producer程序
打开另一个终端
```bash
cd producer
mvn spring-boot:run
```

输入需要发送到topic的信息
```bash
hello!
```
producer终端显示
```bash
发送消息: hello! 到my-topic
```

回到consumer程序运行的终端，终端输出
```bash
Receiving!...
收到订阅的topic:my-topic发送的消息hello!
```





