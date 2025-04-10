package cat.itacademy.s05.t02.n01.S05T02N01Mascota.controller;

import cat.itacademy.s05.t02.n01.S05T02N01Mascota.Service.AuthService;
import cat.itacademy.s05.t02.n01.S05T02N01Mascota.dto.AuthDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AuthDTO user){
        authService.register(user);
        return ResponseEntity.ok("User successfully registered");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthDTO user){
        String token = authService.login(user);
        return ResponseEntity.ok(token);
    }
}
