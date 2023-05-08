package com.example.mspersonalisation;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class MsPersonalisationApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MsPersonalisationApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
