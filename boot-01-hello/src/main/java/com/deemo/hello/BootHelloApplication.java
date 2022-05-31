package com.deemo.hello;

import com.deemo.hello.bean.Cat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class BootHelloApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(BootHelloApplication.class, args);
		System.out.println(applicationContext.getBean(Cat.class));
	}

}
