package com.bcp.postulacion.tipoCambioService.config;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bcp.postulacion.tipoCambioService.utils.MyPair;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.bcp.postulacion.tipoCambioService.service.impl.JwtUserDetailsService;


@Component
public class JwtTokenValidateFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        try {
            Optional.ofNullable(request.getHeader("Authorization"))
                    .filter(x -> x.startsWith("Bearer "))
                    .map(x -> x.substring(7))
                    .map(token -> new MyPair<String, String>(jwtTokenUtil.getUsernameFromToken(token), token))
                    .filter(x -> SecurityContextHolder.getContext().getAuthentication() == null)
                    .map(x -> new MyPair<String, UserDetails>(x.getValue(), this.jwtUserDetailsService.loadUserByUsername(x.getKey())))
                    .filter(x -> jwtTokenUtil.validateToken(x.getKey(), x.getValue()))
                    .map(x -> {
                        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                                x.getValue(), null, x.getValue().getAuthorities());
                        usernamePasswordAuthenticationToken
                                .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        return usernamePasswordAuthenticationToken;
                    }).ifPresent(usernamePasswordAuthenticationToken -> {
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            });
        } catch (MalformedJwtException e) {
            System.out.println("JWT strings must contain exactly 2 period characters");
        } catch (IllegalArgumentException e) {
            System.out.println("Unable to get JWT Token");
        } catch (ExpiredJwtException e) {
            System.out.println("JWT Token has expired");
        } finally {
            chain.doFilter(request, response);
        }
    }
}
