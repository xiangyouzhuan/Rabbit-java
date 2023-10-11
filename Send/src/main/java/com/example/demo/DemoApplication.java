package com.example.demo;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.Scanner;

@SpringBootApplication
public class DemoApplication {

//	private final static String QUEUE_NAME = "hello";

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

		while(true) {
			try (Connection connection = factory.newConnection();
				 Channel channel = connection.createChannel()) {
				channel.queueDeclare(QUEUE_NAME, false, false, false, null);
				Scanner scanner = new Scanner(System.in);
				String messageSend = scanner.next();
				System.out.printf("Send: '%s' to QUEUE:%s\n", messageSend, QUEUE_NAME);
				channel.basicPublish("", QUEUE_NAME, null, messageSend.getBytes(StandardCharsets.UTF_8));
			}
		}
	}
}
