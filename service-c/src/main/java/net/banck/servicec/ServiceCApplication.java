package net.banck.servicec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ServiceCApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceCApplication.class, args);
	}

}
