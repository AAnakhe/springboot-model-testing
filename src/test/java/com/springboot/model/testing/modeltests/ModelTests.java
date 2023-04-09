package com.springboot.model.testing.modeltests;
import com.springboot.model.testing.model.Customer;
import com.springboot.model.testing.model.Order;
import com.springboot.model.testing.repository.CustomerRepository;
import com.springboot.model.testing.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig
@SpringBootTest
public class ModelTests {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void testCreateCustomerAndOrder() {
        Customer customer = new Customer();
        customer.setName("John");
        customerRepository.save(customer);

        Order order = new Order();
        order.setProduct("Phone");
        order.setCustomer(customer);
        orderRepository.save(order);

        assertNotNull(customer.getId());
        assertNotNull(order.getId());
        assertEquals(customer, order.getCustomer());
    }

    @Test
    public void testFindCustomerByName() {
        Customer customer1 = new Customer();
        customer1.setName("John");
        customerRepository.save(customer1);

        Customer customer2 = new Customer();
        customer2.setName("Jane");
        customerRepository.save(customer2);

        Page<Customer> page = customerRepository.findByNameLimitedToOne("John", PageRequest.of(0, 1));
        List<Customer> customers = page.getContent();
        Optional<Customer> foundCustomer = customers.stream().findFirst();

        assertNotNull(foundCustomer);
        assertTrue(foundCustomer.isPresent());
        assertEquals(customer1.getName(), foundCustomer.get().getName());


        Page<Customer> page2 = customerRepository.findByNameLimitedToOne("Jane", PageRequest.of(0, 1));
        List<Customer> customers2 = page2.getContent();

        assertNotNull(customers2);
        assertFalse(customers2.isEmpty());
        assertTrue(customers2.contains(customer2));
    }


    @Test
    void testSaveValidOrderToRepository() {
        Customer customer = new Customer("John", "Doe", "john.doe@example.com");
        Order order = new Order(customer, "Notebook", 9, new Date());

        customerRepository.save(customer);
        orderRepository.save(order);

        Order savedOrder = orderRepository.findById(order.getId()).orElse(null);
        assertNotNull(savedOrder);
        assertEquals(order.getProduct(), savedOrder.getProduct());
    }
}
