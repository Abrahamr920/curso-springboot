package com.abraham.springboot_jpa.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abraham.springboot_jpa.repositories.UserRepository;

@Service
public class JpaUserDetailsServiceService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username)
                .map(user -> {
                    List<GrantedAuthority> authorities = user.getRoles().stream()
                            .map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
                    return new org.springframework.security.core.userdetails.User(user.getUsername(),
                            user.getPassword(), user.isEnabled(), true, true, true, authorities);
                })
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("El usuario %s no está registrado", username)));
    }
}