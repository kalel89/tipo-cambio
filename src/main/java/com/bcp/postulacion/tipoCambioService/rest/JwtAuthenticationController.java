package com.bcp.postulacion.tipoCambioService.rest;

import com.bcp.postulacion.tipoCambioService.service.impl.JwtUserDetailsService;
import io.reactivex.Maybe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;


import com.bcp.postulacion.tipoCambioService.config.JwtTokenUtil;
import com.bcp.postulacion.tipoCambioService.rest.dto.seguridad.JwtRequest;
import com.bcp.postulacion.tipoCambioService.rest.dto.seguridad.JwtResponse;

@RestController
@RequestMapping("/")
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @PostMapping(value = "login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Maybe<ResponseEntity<JwtResponse>> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        return Maybe.just(authenticationRequest)
                .map(x -> new UsernamePasswordAuthenticationToken(x.getUsername(), x.getPassword()))
                .map(authenticationManager::authenticate)
                .map(Authentication::getPrincipal)
                .map(s -> (User)s)
                .map(User::getUsername)
                .map(Object::toString)
                .map(userDetailsService::loadUserByUsername)
                .map(jwtTokenUtil::generateToken)
                .map(JwtResponse::new)
                .map(ResponseEntity::ok)
                .doOnError(throwable -> {
                    throw new Exception("USER_DISABLED", throwable);
                });
    }

    private void authenticate(String username, String password) throws Exception {

    }
}