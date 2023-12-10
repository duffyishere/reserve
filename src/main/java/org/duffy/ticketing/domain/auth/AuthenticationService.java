package org.duffy.ticketing.domain.auth;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.duffy.ticketing.domain.account.Account;
import org.duffy.ticketing.domain.account.BuyerAccount;
import org.duffy.ticketing.domain.account.SellerAccount;
import org.duffy.ticketing.domain.auth.dto.RegisterBuyerAccountRequest;
import org.duffy.ticketing.domain.auth.dto.RegisterSellerAccountRequest;
import org.duffy.ticketing.domain.account.service.AccountService;
import org.duffy.ticketing.domain.auth.dto.AuthenticationRequest;
import org.duffy.ticketing.domain.auth.dto.AuthenticationResponse;
import org.duffy.ticketing.global.config.JwtAuthenticationFilter;
import org.duffy.ticketing.global.config.JwtService;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AccountService accountService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse registerSellerAccount(RegisterSellerAccountRequest request) {
        String encryptedPassword = passwordEncoder.encode(request.getPassword());
        SellerAccount seller = new SellerAccount(request, encryptedPassword);
        accountService.save(seller);

        String jwtToken = jwtService.generateToken(seller);
        String refreshToken = jwtService.generateRefreshToken(seller);
        return new AuthenticationResponse(jwtToken, refreshToken);
    }

    public AuthenticationResponse registerBuyerAccount(RegisterBuyerAccountRequest request) {
        String encryptedPassword = passwordEncoder.encode(request.getPassword());
        BuyerAccount buyer = new BuyerAccount(request, encryptedPassword);
        accountService.save(buyer);

        String jwtToken = jwtService.generateToken(buyer);
        String refreshToken = jwtService.generateRefreshToken(buyer);
        return new AuthenticationResponse(jwtToken, refreshToken);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );
        Account user = accountService.getAccountBy(request.email());
        String jwtToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        return new AuthenticationResponse(jwtToken, refreshToken);
    }

    public AuthenticationResponse refreshToken(HttpServletRequest request) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader == null ||!authHeader.startsWith(JwtAuthenticationFilter.AUTH_HEADER_PREFIX)) {
            return null;
        }

        String refreshToken = authHeader.substring(JwtAuthenticationFilter.AUTH_HEADER_PREFIX.length());
        String userEmail = jwtService.extractUsername(refreshToken);;
        if (userEmail == null) {
            throw new IllegalArgumentException("Unable to extract user email from refresh token");
        }
        Account user = accountService.getAccountBy(userEmail);
        if (!jwtService.isTokenValid(refreshToken, user)) {
            throw new IllegalArgumentException("Refresh token is not valid for user: " + userEmail);
        }

        String accessToken = jwtService.generateToken(user);
        return new AuthenticationResponse(accessToken, refreshToken);
    }
}
