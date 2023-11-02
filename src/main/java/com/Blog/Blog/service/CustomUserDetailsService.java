package com.Blog.Blog.service;

import com.Blog.Blog.entity.User;
import com.Blog.Blog.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private  UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=this.userRepo.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("UserName not found"));
        return org.springframework.security.core.userdetails.User.withUsername(user.getUsername()).password(user.getPassword())
                .authorities(user.getAuthorities()).disabled(user.isEnabled()).build();
    }
}
