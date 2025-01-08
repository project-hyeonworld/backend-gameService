package io.gameservice.api.game.presentation.controller;

import static io.gameservice.api.game.presentation.controller.dto.res.DisplayGamesResponse.*;

import io.gameservice.api.game.presentation.controller.dto.res.DisplayGamesResponse;
import io.gameservice.api.game.domain.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/games")
public class GameController {
    private final GameService gameService;

    @GetMapping("/playable")
    public ResponseEntity<DisplayGamesResponse> displayGame (){
        return ResponseEntity.ok (from(gameService.displayGame()));
    }
}
