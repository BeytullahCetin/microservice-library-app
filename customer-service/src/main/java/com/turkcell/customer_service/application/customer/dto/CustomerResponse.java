package com.turkcell.customer_service.application.customer.dto;

import com.turkcell.customer_service.domain.customer.MemberStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
public class CustomerResponse {
    private final UUID id;
    private final String name;
    private final String phone;
    private final String email;
    private final MemberStatus memberStatus;
    private final LocalDateTime registerDateTime;
}

