package cat.itacademy.s05.t02.n01.S05T02N01Mascota.repository;

import cat.itacademy.s05.t02.n01.S05T02N01Mascota.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);
    boolean existsByName(String name);
}
