package com.payment.midtrans;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MidtransApplication {

	public static void main(String[] args) {
		SpringApplication.run(MidtransApplication.class, args);
	}

}
