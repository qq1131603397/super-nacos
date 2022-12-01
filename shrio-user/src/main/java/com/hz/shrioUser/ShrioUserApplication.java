package com.hz.shrioUser;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hz.shrioUser.mapper")
public class ShrioUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShrioUserApplication.class, args);
    }

}
