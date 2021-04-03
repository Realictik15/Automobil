package com.automobil.backend.rest;

import com.automobil.backend.dto.AuthenticationRequestDto;
import com.automobil.backend.dto.ClientsDto;
import com.automobil.backend.exeption.CLientException;
import com.automobil.backend.exeption.EntityNotFoundException;
import com.automobil.backend.models.Clients;
import com.automobil.backend.security.jwt.JwtTokenProvider;
import com.automobil.backend.service.ClientService;
import com.automobil.backend.transfer.New;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
//@CrossOrigin//(origins = " http://localhost:4200")
public class AuthRestController {
    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final ClientService clientService;

    @Autowired
    public AuthRestController(AuthenticationManager authenticationManager,
                              JwtTokenProvider jwtTokenProvider, ClientService clientService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.clientService = clientService;
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto) {
        try {
            String login = requestDto.getLogin();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, requestDto.getPass()));
            Clients client = clientService.findByUserName(login);

            if (client == null) {
                throw new UsernameNotFoundException("User with username: " + login + " not found");
            }

            String token = jwtTokenProvider.createToken(login, client.getRoles());

            Map<Object, Object> response = new HashMap<>();
            response.put("id", client.getIdUser());
            response.put("role", client.getRoles());
            response.put("login", login);
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
        MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registration(@Validated(New.class) @RequestBody ClientsDto clientsDto) throws CLientException {
        if (clientsDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            clientService.register(clientsDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }
}

