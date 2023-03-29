package com.erich.exam.controllers;

import com.erich.exam.entity.User;
import com.erich.exam.entity.auth.JwtRequestDto;
import com.erich.exam.entity.auth.JwtResponseDto;
import com.erich.exam.security.CustomUserDetailsService;
import com.erich.exam.security.jwt.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin("*")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final CustomUserDetailsService detailsService;

    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, CustomUserDetailsService securityService, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.detailsService = securityService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/token")
    public ResponseEntity<?> token(@RequestBody JwtRequestDto requestDto) throws Exception {
        try {
            this.auth(requestDto.getUsername(), requestDto.getPassword());

        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
            throw new Exception("User not found");
        }
        UserDetails userDetails = this.detailsService.loadUserByUsername(requestDto.getUsername());
        String token = this.jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponseDto(token));
    }

    private void auth(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("User DISABLED" + e.getMessage());
        } catch (BadCredentialsException e) {
            throw new Exception("Invalid credentials" + e.getMessage());
        }
    }


    @GetMapping("/current-user")
    public User getCurrentUser(Principal principal){
        UserDetails userDetails = detailsService.loadUserByUsername(principal.getName());
        return (User)userDetails;
    }
}
