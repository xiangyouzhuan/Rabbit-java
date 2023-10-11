package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.nio.charset.StandardCharsets;

@SpringBootApplication
public class DemoApplication {

	private final static String QUEUE_NAME = "hello";
	@Value("${bootstrap.servers}")
	private static int port;

	public static void main(String[] argv) throws Exception {
		System.out.println("port: " + port);
		ConnectionFactory factory = new ConnectionFactory();
//		factory.setHost("localhost");

		factory.setUsername("username");
		factory.setPassword("2a55f70a841f18b97c3a7db939b7adc9e34a0f1b");
		factory.setVirtualHost("qa1");
		factory.setHost("RabbitMQ");
		factory.setPort(5672);

		try (Connection connection = factory.newConnection();
			 Channel channel = connection.createChannel()) {
			channel.queueDeclare(QUEUE_NAME, false, false, false, null);
			String message = "Hello World!";
			channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));
			System.out.println(" [x] Sent '" + message + "'");
		}
	}

}
