package com.deemo.hello.config;

import com.deemo.hello.bean.Cat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeemoConfiguration {

    @Bean
    public Cat cat() {
        return new Cat();
    }

}
