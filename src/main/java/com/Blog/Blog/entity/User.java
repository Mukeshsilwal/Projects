package com.Blog.Blog.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email;
    private String password;

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private Set<Role> roles;
        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            List<SimpleGrantedAuthority> authorities=roles.stream().map(role->new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
            return authorities;
        }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
        public String getUsername() {
            return email;
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }

}
