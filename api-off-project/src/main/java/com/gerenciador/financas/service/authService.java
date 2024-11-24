package com.gerenciador.financas.service;

import com.gerenciador.financas.dto.AcessDTO;
import com.gerenciador.financas.dto.AuthenticationDTO;
import com.gerenciador.financas.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class authService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;



    public AcessDTO login (AuthenticationDTO authDTO) {
        try {
            //cria mecanismo de credenial para o spring
            UsernamePasswordAuthenticationToken userAuth = new UsernamePasswordAuthenticationToken(authDTO.getUserName(), authDTO.getPassWord());
            //perpara macanismo para autenticaçãp
            Authentication authentication = authenticationManager.authenticate(userAuth);
            //busca usuario logado
            UserDetailsImpl userAuthenticate = (UserDetailsImpl)authentication.getPrincipal();

            String token  =  jwtUtils.generateTokenFromUserDetailsImpl(userAuthenticate);

            AcessDTO acessDTO = new AcessDTO(token);
            return acessDTO;
        }catch (BadCredentialsException e) {
            return null;

            //TODO login ou senha invalido
        }

    }
}
