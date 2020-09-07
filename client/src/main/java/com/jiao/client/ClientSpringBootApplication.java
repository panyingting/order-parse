package com.jiao.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.jiao.client", "com.jiao.order.parse"})

public class ClientSpringBootApplication {

    public static void main(String[] args) {

        SpringApplication.run(ClientSpringBootApplication.class);
    }


}
