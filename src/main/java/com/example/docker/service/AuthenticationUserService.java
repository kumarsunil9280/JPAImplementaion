package com.example.docker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.docker.dao.AuthUserRepository;
import com.example.docker.entity.UserAuth;


@Service
public class AuthenticationUserService implements UserDetailsService {
	
	

	@Autowired
    private AuthUserRepository authUserRepository ;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        UserAuth user = authUserRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found: " + username));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())   // ✅ already encoded
                .roles("USER")          // USER / ADMIN
                .build();
    }

}
