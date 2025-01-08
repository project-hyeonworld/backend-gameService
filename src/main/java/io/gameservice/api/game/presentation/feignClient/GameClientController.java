package io.gameservice.api.game.presentation.feignClient;

import io.gameservice.api.game.domain.GameService;
import io.gameservice.api.game.domain.dto.in.CheckStageCommand;
import io.gameservice.api.game.domain.dto.in.ResultStageCommand;
import io.gameservice.api.game.presentation.feignClient.dto.res.CheckStageResponse;
import io.gameservice.api.game.domain.dto.in.ShowStageCommand;
import io.gameservice.api.game.presentation.feignClient.dto.res.ResultStageResponse;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 25. 1. 8.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/games")
public class GameClientController {

    private final GameService gameService;

    @GetMapping("/{gameId}/checks")
    public ResponseEntity<List<CheckStageResponse>> checkStage(@PathVariable long gameId,
            @RequestParam long partyId,
            @RequestParam long roundCount) {
        return ResponseEntity.ok(CheckStageResponse.from(gameService.checkStage(
                CheckStageCommand.from(gameId, partyId, roundCount))));
    }

    @GetMapping("/{gameId}/shows")
    public ResponseEntity<String> showStage(@PathVariable long gameId,
            @RequestParam long partyId,
            @RequestParam long roundCount,
            @RequestParam long targetId) {
        return ResponseEntity.ok(gameService.showStage(
                ShowStageCommand.from(gameId, partyId, roundCount, targetId)));
    }

    @GetMapping("/{gameId}/results")
    public ResponseEntity<ResultStageResponse> resultStage(@PathVariable long gameId,
            @RequestParam long partyId,
            @RequestParam long roundCount,
            @RequestParam long targetId) {
        return ResponseEntity.ok(ResultStageResponse.from(gameService.resultStage(ResultStageCommand.from(gameId, partyId, roundCount, targetId))));
    }
}
