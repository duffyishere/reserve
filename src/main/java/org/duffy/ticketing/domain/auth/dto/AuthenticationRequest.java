package org.duffy.ticketing.domain.auth.dto;

public record AuthenticationRequest(String email, String password) {
}
