package com.aisat.hkgott;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.aisat.hkgott.mapper")
public class HkgottApplication {

    public static void main(String[] args) {
        SpringApplication.run(HkgottApplication.class, args);
    }
}
