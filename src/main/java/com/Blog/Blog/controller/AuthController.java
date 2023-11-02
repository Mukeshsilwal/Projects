package com.Blog.Blog.controller;

import com.Blog.Blog.Security.JwtService;
import com.Blog.Blog.model.JwtRequest;
import com.Blog.Blog.model.JwtResponse;
import com.Blog.Blog.payloads.UserDto;
import com.Blog.Blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest user) {
        JwtResponse jwtResponse;
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
        String token = this.jwtService.generateToken(userDetails);
        jwtResponse = JwtResponse.builder()
                .token(token)
                .email(userDetails.getUsername())
                .build();
        return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
    }
    @PostMapping("/create_user")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user)  {
        UserDto userDto = this.userService.createUser(user);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);

    }
}
