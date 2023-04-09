package com.springboot.model.testing;

import com.springboot.model.testing.model.Customer;
import com.springboot.model.testing.model.Order;
import com.springboot.model.testing.repository.CustomerRepository;
import com.springboot.model.testing.repository.OrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ModelTestingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ModelTestingApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(CustomerRepository customerRepository, OrderRepository orderRepository) {
		return (args) -> {
			Customer customer = new Customer();
			customer.setName("John");
			customerRepository.save(customer);

			Order order = new Order();
			order.setProduct("Phone");
			order.setCustomer(customer);
			orderRepository.save(order);
		};
	}

}
