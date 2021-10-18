package com.bcp.postulacion.tipoCambioService.service.impl;

import java.util.ArrayList;
import java.util.Optional;

import com.bcp.postulacion.tipoCambioService.dao.UsuarioDao;
import com.bcp.postulacion.tipoCambioService.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private UsuarioDao usuarioDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> u = usuarioDao.findById(username);
        if (u.isPresent()) {
            return new User(u.get().getUsername(), u.get().getClave(), new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("Usuario o password invalidos para : " + username);
        }
    }
}