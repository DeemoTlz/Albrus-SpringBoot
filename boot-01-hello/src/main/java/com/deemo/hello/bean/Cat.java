package com.deemo.hello.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("albrus")
public class Cat {
    private Long id;
    private String name;
}
