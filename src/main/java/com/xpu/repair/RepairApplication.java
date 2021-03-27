package com.xpu.repair;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xpu.repair.mapper")
public class RepairApplication {

    public static void main(String[] args) {
        SpringApplication.run(RepairApplication.class, args);
    }

}
