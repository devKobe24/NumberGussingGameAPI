package com.kobe.numberguessinggame.controller;

import com.kobe.numberguessinggame.dto.request.GuessRequest;
import com.kobe.numberguessinggame.dto.response.GuessResponse;
import com.kobe.numberguessinggame.model.Game;
import com.kobe.numberguessinggame.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * packageName    : com.kobe.numberguessinggame.controller
 * fileName       : GameController
 * author         : kobe
 * date           : 2025. 9. 19.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025. 9. 19.        kobe       최초 생성
 */
@RestController
@RequestMapping("/api/game")
@RequiredArgsConstructor
public class GameController {

	private final GameService gameService;

	@PostMapping("/start")
	public ResponseEntity<Map<String, String>> startGame() {
		Game game = gameService.startGame();
		return ResponseEntity.ok(Map.of(
			"message", "새로운 게임을 시작합니다! 1부터 100 사이의 숫자를 맞춰보세요. 🤔",
			"gameId", game.getId()
		));
	}

	@PostMapping("/guess")
	public ResponseEntity<GuessResponse> makeGuess(
		@RequestBody GuessRequest request) {
		String hint = gameService.makeGuess(request.getGameId(), request.getGuess());
		Game game = gameService.getGame(request.getGameId());
		return ResponseEntity.ok(new GuessResponse(hint, game.getAttempts()));
	}
}
