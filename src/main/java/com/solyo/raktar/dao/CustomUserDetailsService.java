package com.solyo.raktar.dao;

import com.solyo.raktar.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var customer = userRepository.findByEmail(email);
        if (customer.isEmpty()){
            throw new UsernameNotFoundException("User with email: "+email+" not found!");
        }
        return new CustomUserDetails(customer.get());
    }
}