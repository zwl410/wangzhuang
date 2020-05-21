package com.zhang.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@MapperScan(value = {"com.zhang.system.components.cmsuser.mapper"})
@EnableAsync
public class SystemApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SystemApplication.class, args);
    }

}
