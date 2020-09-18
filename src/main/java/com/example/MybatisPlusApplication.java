package com.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@Slf4j
public class MybatisPlusApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        try {
            SpringApplication.run(MybatisPlusApplication.class, args);
           log.info("————————————————启动成功————————————————");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MybatisPlusApplication.class);
    }

}
