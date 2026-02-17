package br.com.alura.forumHub.domain.user;

import br.com.alura.forumHub.domain.ForumHubValidationException;
import br.com.alura.forumHub.domain.profile.Profile;
import br.com.alura.forumHub.domain.profile.ProfileRepository;
import br.com.alura.forumHub.domain.profile.RoleName;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Transactional
    public User createUser(@Valid UserDTO userDTO) {
        if(userRepository.existsByEmail(userDTO.email())){
            throw new ForumHubValidationException("Email já está cadastrado no sistema.");
        }

        var defaultProfile = profileRepository.findByName(RoleName.ROLE_ALUNO)
                .orElseThrow(() -> new ForumHubValidationException("Perfil padrão não encontrado"));

        var encodePassword = passwordEncoder.encode(userDTO.password());

        var user = new User(userDTO, encodePassword);
        user.getProfiles().add(defaultProfile);

        userRepository.save(user);
        return user;
    }
}
