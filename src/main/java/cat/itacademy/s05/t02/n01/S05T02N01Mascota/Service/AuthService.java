package cat.itacademy.s05.t02.n01.S05T02N01Mascota.Service;

import cat.itacademy.s05.t02.n01.S05T02N01Mascota.enums.RoleType;
import cat.itacademy.s05.t02.n01.S05T02N01Mascota.exception.LoginException;
import cat.itacademy.s05.t02.n01.S05T02N01Mascota.model.User;
import cat.itacademy.s05.t02.n01.S05T02N01Mascota.dto.AuthDTO;
import cat.itacademy.s05.t02.n01.S05T02N01Mascota.exception.EmptyException;
import cat.itacademy.s05.t02.n01.S05T02N01Mascota.exception.NameExistsException;
import cat.itacademy.s05.t02.n01.S05T02N01Mascota.repository.UserRepository;
import cat.itacademy.s05.t02.n01.S05T02N01Mascota.security.TokenJwt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TokenJwt tokenJwt;

    public AuthService(PasswordEncoder passwordEncoder, UserRepository userRepository, TokenJwt tokenJwt) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.tokenJwt = tokenJwt;
    }

    public String register(AuthDTO userDTO) {
        if (userDTO.getName() == null || userDTO.getName().trim().isEmpty()) {
            throw new EmptyException("the name is empty.");
        }
        if (userDTO.getPassword() == null || userDTO.getPassword().trim().isEmpty()) {
            throw new EmptyException("the password is empty");
        }
        if (userRepository.existsByName(userDTO.getName())) {
            throw new NameExistsException("name does exist.");
        }
        String hash = passwordEncoder.encode(userDTO.getPassword());
        User user = new User();
        user.setName(userDTO.getName());
        user.setPassword(hash);
        user.setRole(RoleType.USER);
        userRepository.save(user);
        return "Successful registration";
    }

    public String login(AuthDTO userDTO){
        Optional<User> optionalUser = userRepository.findByName(userDTO.getName());
        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (passwordEncoder.matches(userDTO.getPassword(), user.getPassword())) {
                String role = user.getName().equals("admin") ? RoleType.ADMIN.name() : user.getRole().name();
                String token = tokenJwt.generateToken(user.getId(), user.getName(), role);
                return token;
            }
        }
        throw new LoginException("incorrect username or password");
    }
}