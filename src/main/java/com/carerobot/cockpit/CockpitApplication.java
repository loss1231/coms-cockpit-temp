package com.carerobot.cockpit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.carerobot.cockpit.mapper")
public class CockpitApplication {

    public static void main(String[] args) {
        SpringApplication.run(CockpitApplication.class, args);
    }

}
