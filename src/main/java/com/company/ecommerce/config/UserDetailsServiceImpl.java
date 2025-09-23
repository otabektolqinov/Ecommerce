package com.company.ecommerce.config;

import com.company.ecommerce.domain.AuthUser;
import com.company.ecommerce.repository.AuthUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AuthUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        AuthUser users = userRepository.findByPhoneNumber(phoneNumber).orElseThrow(()->
                new UsernameNotFoundException(String.format("User with %s phone number not found", phoneNumber))
        );

        return new MyUserDetails(
                users.getId(),
                users.getPhoneNumber(),
                users.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + users.getRole().toString()))
        );
    }
}
