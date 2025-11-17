package com.turkcell.customer_service.application.customer.service;

import com.turkcell.customer_service.application.customer.dto.CreateCustomerRequest;
import com.turkcell.customer_service.application.customer.dto.CustomerResponse;
import com.turkcell.customer_service.application.customer.dto.UpdateCustomerRequest;

import java.util.List;
import java.util.UUID;

public interface CustomerApplicationService {

    CustomerResponse create(CreateCustomerRequest request);

    CustomerResponse update(UUID id, UpdateCustomerRequest request);

    void delete(UUID id);

    CustomerResponse getById(UUID id);

    List<CustomerResponse> getAll();
}

