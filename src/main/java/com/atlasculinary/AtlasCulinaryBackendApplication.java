package com.atlasculinary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class AtlasCulinaryBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(AtlasCulinaryBackendApplication.class, args);
    }

}