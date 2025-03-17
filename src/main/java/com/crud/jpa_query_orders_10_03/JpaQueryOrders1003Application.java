package com.crud.jpa_query_orders_10_03;

import com.crud.jpa_query_orders_10_03.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class JpaQueryOrders1003Application {

	public static void main(String[] args) {
		SpringApplication.run(JpaQueryOrders1003Application.class, args);
	}
}
