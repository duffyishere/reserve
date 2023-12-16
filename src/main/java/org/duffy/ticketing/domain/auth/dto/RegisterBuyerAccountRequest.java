package org.duffy.ticketing.domain.auth.dto;

import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@SuperBuilder
public class RegisterBuyerAccountRequest extends RegisterAccountRequest {
    public RegisterBuyerAccountRequest(String mail, String password, String name) {
        super(mail, password, name);
    }
}
