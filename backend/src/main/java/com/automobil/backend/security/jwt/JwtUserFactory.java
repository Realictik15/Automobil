package com.automobil.backend.security.jwt;

import com.automobil.backend.models.Clients;
import com.automobil.backend.models.Roles;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUser create(Clients user) {
        return new JwtUser(
            user.getIdUser(),
            mapToGrantedAuthorities(user.getRoles()),
            user.getFirstName(),
            user.getLastName(),
            user.getLogin(),
            user.getPass(),
            user.getEmale(),
            user.getTelephone()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(Roles roles) {
        List<Roles> userRoles= new ArrayList<Roles>(){{add(roles);}};

        return userRoles.stream()
            .map(role ->
                new SimpleGrantedAuthority(role.name())
            ).collect(Collectors.toList());
    }
}
