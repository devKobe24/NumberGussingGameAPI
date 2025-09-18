package com.kobe.numberguessinggame.service;

import com.kobe.numberguessinggame.model.Game;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * packageName    : com.kobe.numberguessinggame.service
 * fileName       : GameService
 * author         : kobe
 * date           : 2025. 9. 19.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025. 9. 19.        kobe       최초 생성
 */
@Service
public class GameService {
	// 메모리에 게임 세션을 저장(Key: gameId, Value: Game 객체)
	private final Map<String, Game> activeGames = new ConcurrentHashMap<>();

	public Game startGame() {
		Game newGame = new Game();
		activeGames.put(newGame.getId(), newGame);
		return newGame;
	}

	public Game getGame(String id) {
		return activeGames.get(id);
	}

	public String makeGuess(String id, int guess) {
		Game game = getGame(id);
		if (game == null) {
			throw new IllegalArgumentException("존재하지 않는 게임 ID입니다.");
		}
		return game.guess(guess);
	}
}
