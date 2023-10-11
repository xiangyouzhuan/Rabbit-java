package com.example.demo;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws Exception {
		Properties props = new Properties();
		File file = new File("src/main/resources/application.properties");
		try (InputStream in = new FileInputStream(file)) {
			props.load(in);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		String userName = props.getProperty("userName");
		int port = Integer.parseInt(props.getProperty("port"));
		String password = props.getProperty("password");
		String virtualHost = props.getProperty("virtualHost");
		String host = props.getProperty("host");
		String QUEUE_NAME = props.getProperty("QUEUE_NAME");

		ConnectionFactory factory = new ConnectionFactory();

		factory.setUsername(userName);
		factory.setPassword(password);
		factory.setVirtualHost(virtualHost);
		factory.setHost(host);
		factory.setPort(port);

		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

		DeliverCallback deliverCallback = (consumerTag, delivery) -> {
			String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
			System.out.println("Received '" + message + "'"+"from QUEUE:"+QUEUE_NAME);
		};
		channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
	}
}
