package com.heukbaekbook.heukbaekbookconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class HeukbaekbookConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(HeukbaekbookConfigServerApplication.class, args);
    }

}
