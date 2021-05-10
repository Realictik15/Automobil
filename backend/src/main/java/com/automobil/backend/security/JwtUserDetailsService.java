package com.automobil.backend.security;

import com.automobil.backend.models.Clients;
import com.automobil.backend.security.jwt.JwtUser;
import com.automobil.backend.security.jwt.JwtUserFactory;
import com.automobil.backend.service.ClientService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service

public class JwtUserDetailsService implements UserDetailsService {
    private final ClientService clientService;
    private static final Logger LOGGER = LogManager.getLogger(JwtUserDetailsService.class.getName());
    @Autowired
    public JwtUserDetailsService(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Clients clients = clientService.findByUserName(username);

        if (clients == null) {
            throw new UsernameNotFoundException("User with username: " + username + " not found");
        }
        JwtUser jwtUser = JwtUserFactory.create(clients);
        LOGGER.info("JwtUser created");
        return jwtUser;
    }
}
