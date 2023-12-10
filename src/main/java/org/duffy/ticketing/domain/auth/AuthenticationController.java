package org.duffy.ticketing.domain.auth;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.duffy.ticketing.domain.auth.dto.RegisterBuyerAccountRequest;
import org.duffy.ticketing.domain.auth.dto.RegisterSellerAccountRequest;
import org.duffy.ticketing.domain.auth.dto.AuthenticationRequest;
import org.duffy.ticketing.domain.auth.dto.AuthenticationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authService;

    @PostMapping("/register/seller")
    public ResponseEntity<AuthenticationResponse> registerSeller(@RequestBody RegisterSellerAccountRequest request) {
        return ResponseEntity.ok(authService.registerSellerAccount(request));
    }

    @PostMapping("/register/buyer")
    public ResponseEntity<AuthenticationResponse> registerBuyer(@RequestBody RegisterBuyerAccountRequest request) {
        return ResponseEntity.ok(authService.registerBuyerAccount(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<AuthenticationResponse> refreshToken(HttpServletRequest request) {
        return ResponseEntity.ok(authService.refreshToken(request));
    }
}
