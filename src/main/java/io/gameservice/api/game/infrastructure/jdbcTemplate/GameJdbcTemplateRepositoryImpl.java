package io.gameservice.api.game.infrastructure.jdbcTemplate;

import io.gameservice.api.game.infrastructure.entity.Game;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 3.
 */
@Repository
@RequiredArgsConstructor
public class GameJdbcTemplateRepositoryImpl implements GameJdbcTemplateRepository {
  private final JdbcTemplate jdbcTemplate;

  @Override
  public List<Game> findByPlayable(boolean playable) {
    String sql = "SELECT id, name, description FROM game WHERE playable = ?";
    return this.jdbcTemplate.query(
            sql,
            (resultSet, rowNum) -> Game.createToDisplay(resultSet),
            new Boolean[]{playable}
    );


  }
}
