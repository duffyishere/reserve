package org.duffy.ticketing.domain.auth.dto;

public record AuthenticationResponse(String accessToken, String refreshToken) {
}
