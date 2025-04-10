package cat.itacademy.s05.t02.n01.S05T02N01Mascota.security;

import cat.itacademy.s05.t02.n01.S05T02N01Mascota.enums.RoleType;
import cat.itacademy.s05.t02.n01.S05T02N01Mascota.model.User;
import cat.itacademy.s05.t02.n01.S05T02N01Mascota.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminInitializer {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void initAdmin(){
        if(!userRepository.existsByName("admin")) {
            User admin = new User();
            admin.setName("admin");
            admin.setPassword(passwordEncoder.encode("admin1234"));
            admin.setRole(RoleType.ADMIN);
            userRepository.save(admin);
        }
    }
}
