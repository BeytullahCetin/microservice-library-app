package com.turkcell.customer_service.application.customer.dto;

import com.turkcell.customer_service.domain.customer.MemberStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCustomerRequest {

    @Size(max = 150)
    private String name;

    @Size(max = 30)
    @Pattern(regexp = "^[0-9+\\- ]{7,30}$", message = "Phone number format is invalid")
    private String phone;

    @Email
    @Size(max = 150)
    private String email;

    private MemberStatus memberStatus;
}

