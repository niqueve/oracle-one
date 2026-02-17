package br.com.alura.forumHub.controller;

import br.com.alura.forumHub.domain.user.UserDTO;
import br.com.alura.forumHub.domain.user.UserDetailDTO;
import br.com.alura.forumHub.domain.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuario")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity createUser (@RequestBody @Valid UserDTO userDTO, UriComponentsBuilder uriBuilder) {
        var user = userService.createUser(userDTO);
        var uri = uriBuilder.path("/usuario/{id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(uri).body(new UserDetailDTO(user));
    }

}
