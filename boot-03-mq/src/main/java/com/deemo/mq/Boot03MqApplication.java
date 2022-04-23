package com.deemo.mq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Boot03MqApplication {

	public static void main(String[] args) {
		SpringApplication.run(Boot03MqApplication.class, args);
	}

}
