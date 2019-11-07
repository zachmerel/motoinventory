package com.trilogyed.motoinventoryeurekaserviceregistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MotoinventoryEurekaServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MotoinventoryEurekaServiceRegistryApplication.class, args);
	}

}
