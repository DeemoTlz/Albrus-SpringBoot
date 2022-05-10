package com.deemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class App {

    public static void main(String[] args) {
        // OFF > FATAL > ERROR > WARN > INFO > DEBUG > TRACE > ALL
        // 由于使用了 Spring Log4j2 配置文件增强，所以建议是在 Spring 启动之后再调用

        /*log.error("ERROR...");
        log.warn("WARN...");
        log.info("INFO...");
        log.debug("DEBUG...");
        log.trace("TRACE...");*/

        SpringApplication.run(App.class, args);

        log.error("ERROR...");
        log.warn("WARN...");
        log.info("INFO...");
        log.debug("DEBUG...");
        log.trace("TRACE...");
    }

}
