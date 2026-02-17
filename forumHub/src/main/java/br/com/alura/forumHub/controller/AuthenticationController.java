package br.com.alura.forumHub.controller;

import br.com.alura.forumHub.domain.user.User;
import br.com.alura.forumHub.domain.user.UserLoginDTO;
import br.com.alura.forumHub.infra.security.TokenDataJWT;
import br.com.alura.forumHub.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity login (@RequestBody @Valid  UserLoginDTO userDTO) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(userDTO.login(), userDTO.password());
        var authentication = authenticationManager.authenticate(authenticationToken);
        var tokenJWT = tokenService.generateToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new TokenDataJWT(tokenJWT));

    }
}
