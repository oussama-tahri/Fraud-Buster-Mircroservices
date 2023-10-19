package com.tahrioussama.customer.services;

import com.tahrioussama.amqp.RabbitMQMessageProducer;
import com.tahrioussama.customer.entities.Customer;
import com.tahrioussama.customer.records.CustomerRegistryRequest;
import com.tahrioussama.customer.repositories.CustomerRepository;
import com.tahrioussama.fraud.FraudCheckResponse;
import com.tahrioussama.fraud.FraudClient;
import com.tahrioussama.notification.NotificationClient;
import com.tahrioussama.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {
       private final CustomerRepository customerRepository;
       private final FraudClient fraudClient;
       private final RabbitMQMessageProducer rabbitMQMessageProducer;
    public void registerCustomer(CustomerRegistryRequest customerRegistryRequest) {
        Customer customer = Customer.builder()
                .firstName(customerRegistryRequest.firstName())
                .lastName(customerRegistryRequest.lastName())
                .email(customerRegistryRequest.email())
                .build();
        // todo: check if email valid
        // todo: check if email not taken
        customerRepository.saveAndFlush(customer);
        // todo: check if fraudster
        FraudCheckResponse fraudCheckResponse =
                fraudClient.isFraudster(customer.getId());

        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("fraudster");
        }
        // todo: send notification
                NotificationRequest notificationRequest = new NotificationRequest(
                        customer.getId(),
                        customer.getEmail(),
                        String.format("Hi %s, Welcome to OT...",
                                customer.getFirstName())
                );
                rabbitMQMessageProducer.publish(
                        notificationRequest,
                        "internal.exchange",
                        "internal.notification.routing-key"
                );
    }
}
