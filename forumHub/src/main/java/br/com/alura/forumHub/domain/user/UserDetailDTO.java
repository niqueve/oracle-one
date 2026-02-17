package br.com.alura.forumHub.domain.user;

import java.util.List;

public record UserDetailDTO(
        Long id,
        String name,
        String email,
        List profile
) {
    public UserDetailDTO(User user){
        this(user.getId(), user.getName(), user.getEmail(), user.getProfiles());
    }
}
