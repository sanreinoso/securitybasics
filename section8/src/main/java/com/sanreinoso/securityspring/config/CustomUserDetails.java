package com.sanreinoso.securityspring.config;

import com.sanreinoso.securityspring.model.Customer;
import com.sanreinoso.securityspring.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetails implements UserDetailsService {

    private final CustomerRepository customerRepository;

    /**
     * This method is used to load the user data from the database.
     * @param username the username identifying the user whose data is required.
     * @return user details.
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        Customer customer = this.customerRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(customer.getRole()));
        return new User(customer.getEmail(), customer.getPwd(), authorities);
    }
}
