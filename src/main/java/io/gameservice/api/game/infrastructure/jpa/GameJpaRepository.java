package io.gameservice.api.game.infrastructure.jpa;

import io.gameservice.api.game.infrastructure.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 3.
 */
public interface GameJpaRepository extends JpaRepository<Game, Long> {
}
