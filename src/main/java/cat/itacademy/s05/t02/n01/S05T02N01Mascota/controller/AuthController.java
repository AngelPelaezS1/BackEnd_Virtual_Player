package cat.itacademy.s05.t02.n01.S05T02N01Mascota.controller;

import cat.itacademy.s05.t02.n01.S05T02N01Mascota.service.AuthService;
import cat.itacademy.s05.t02.n01.S05T02N01Mascota.dto.AuthDTO;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

@Operation(summary = "Register new user.")
@ApiResponses( value = {
        @ApiResponse(responseCode = "201", description = "User successfully registered."),
        @ApiResponse(responseCode = "409", description = "User already exists")
})
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AuthDTO user){
        authService.register(user);
        return ResponseEntity.ok("User successfully registered.");
    }

@Operation(summary = "Login user.")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User successfully logged in."),
        @ApiResponse(responseCode = "400", description = "Invalid input data.")
})
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthDTO user){
        String token = authService.login(user);
        return ResponseEntity.ok(token);
    }
}
