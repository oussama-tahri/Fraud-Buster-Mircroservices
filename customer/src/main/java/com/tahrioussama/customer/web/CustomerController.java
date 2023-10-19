package com.tahrioussama.customer.web;

import com.tahrioussama.customer.records.CustomerRegistryRequest;
import com.tahrioussama.customer.services.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/customers")
public record CustomerController(CustomerService customerService) {
    @PostMapping
    public void registerCustomer(@RequestBody CustomerRegistryRequest customerRegistryRequest) {
        log.info("new Customer registration {}", customerRegistryRequest);
        customerService.registerCustomer(customerRegistryRequest);
    }
}
