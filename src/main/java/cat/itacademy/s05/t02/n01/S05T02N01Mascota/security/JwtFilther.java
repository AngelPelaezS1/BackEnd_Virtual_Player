package cat.itacademy.s05.t02.n01.S05T02N01Mascota.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtFilther extends OncePerRequestFilter {

    private final TokenJwt tokenJwt;

    public JwtFilther(TokenJwt tokenJwt) {
        this.tokenJwt = tokenJwt;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

    }
}
