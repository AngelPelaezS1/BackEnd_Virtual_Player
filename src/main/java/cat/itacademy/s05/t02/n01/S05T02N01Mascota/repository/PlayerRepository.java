package cat.itacademy.s05.t02.n01.S05T02N01Mascota.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cat.itacademy.s05.t02.n01.S05T02N01Mascota.model.Player;
import cat.itacademy.s05.t02.n01.S05T02N01Mascota.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findByUser(User user);
}
