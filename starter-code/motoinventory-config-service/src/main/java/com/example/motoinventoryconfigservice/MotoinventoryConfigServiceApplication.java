package com.example.motoinventoryconfigservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class MotoinventoryConfigServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MotoinventoryConfigServiceApplication.class, args);
	}

}
