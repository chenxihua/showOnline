package com.gosuncn.refresh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Properties;

// 开启定时
@EnableScheduling
@SpringBootApplication
public class RefreshApplication {

    public static void main(String[] args) {
        SpringApplication.run(RefreshApplication.class, args);
    }



}
