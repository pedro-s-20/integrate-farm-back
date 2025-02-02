package br.com.integratefarma.security.service;

import br.com.integratefarma.usuario.service.UsuarioService;
import br.com.integratefarma.usuario.entity.UsuarioEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements UserDetailsService {

    private final UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<UsuarioEntity> usuarioEntityOptional = usuarioService.findByEmail(email);

        return usuarioEntityOptional
                .orElseThrow(()->new UsernameNotFoundException("Usuário não encontrado"));
    }



}