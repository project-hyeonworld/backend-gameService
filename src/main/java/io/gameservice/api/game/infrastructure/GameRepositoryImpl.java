package io.gameservice.api.game.infrastructure;

import io.gameservice.api.game.infrastructure.entity.Game;
import io.gameservice.api.game.infrastructure.jdbcTemplate.GameJdbcTemplateRepository;
import io.gameservice.api.game.infrastructure.jpa.GameJpaRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 3.
 */
@Repository
@Primary
@RequiredArgsConstructor
public class GameRepositoryImpl implements GameRepository {
  private final GameJdbcTemplateRepository gameJdbcTemplateRepository;
  private final GameJpaRepository gameJpaRepository;

  @Override
  public List<Game> findByPlayable(boolean playable) {
    return gameJdbcTemplateRepository.findByPlayable(playable);
  }
}
