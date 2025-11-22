package com.turkcell.customer_service.application.customer.service;

import com.turkcell.customer_service.application.common.exception.BusinessRuleException;
import com.turkcell.customer_service.application.common.exception.ResourceNotFoundException;
import com.turkcell.customer_service.application.customer.dto.CreateCustomerRequest;
import com.turkcell.customer_service.application.customer.dto.CustomerResponse;
import com.turkcell.customer_service.application.customer.dto.UpdateCustomerRequest;
import com.turkcell.customer_service.domain.customer.Customer;
import com.turkcell.customer_service.domain.customer.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@Transactional
public class CustomerApplicationServiceImpl implements CustomerApplicationService {

    private final CustomerRepository customerRepository;

    public CustomerApplicationServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerResponse create(CreateCustomerRequest request) {
        ensureUniqueConstraints(request.getEmail(), request.getPhone(), null);

        Customer customer = Customer.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .memberStatus(request.getMemberStatus())
                .build();

        Customer saved = customerRepository.save(Objects.requireNonNull(customer));
        return toResponse(saved);
    }

    @Override
    public CustomerResponse update(UUID id, UpdateCustomerRequest request) {
        Customer customer = getCustomer(id);

        if (request.getEmail() != null && !request.getEmail().equals(customer.getEmail())) {
            ensureUniqueConstraints(request.getEmail(), null, id);
            customer.setEmail(request.getEmail());
        }

        if (request.getPhone() != null && !request.getPhone().equals(customer.getPhone())) {
            ensureUniqueConstraints(null, request.getPhone(), id);
            customer.setPhone(request.getPhone());
        }

        if (request.getName() != null) {
            customer.setName(request.getName());
        }

        if (request.getMemberStatus() != null) {
            customer.setMemberStatus(request.getMemberStatus());
        }

        return toResponse(customer);
    }

    @Override
    public void delete(UUID id) {
        Customer customer = getCustomer(id);
        customerRepository.delete(Objects.requireNonNull(customer));
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerResponse getById(UUID id) {
        return toResponse(getCustomer(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerResponse> getAll() {
        return customerRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    private Customer getCustomer(UUID id) {
        return customerRepository.findById(Objects.requireNonNull(id))
                .orElseThrow(() -> new ResourceNotFoundException("Customer %s not found".formatted(id)));
    }

    private void ensureUniqueConstraints(String email, String phone, UUID currentId) {
        if (email != null) {
            customerRepository.findByEmail(email)
                    .filter(customer -> currentId == null || !customer.getId().equals(currentId))
                    .ifPresent(customer -> {
                        throw new BusinessRuleException("Email is already in use");
                    });
        }

        if (phone != null) {
            customerRepository.findByPhone(phone)
                    .filter(customer -> currentId == null || !customer.getId().equals(currentId))
                    .ifPresent(customer -> {
                        throw new BusinessRuleException("Phone is already in use");
                    });
        }
    }

    private CustomerResponse toResponse(Customer customer) {
        return CustomerResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .memberStatus(customer.getMemberStatus())
                .registerDateTime(customer.getRegisterDateTime())
                .build();
    }
}

