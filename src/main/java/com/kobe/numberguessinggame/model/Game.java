package com.kobe.numberguessinggame.model;

import lombok.Getter;

import java.util.UUID;

/**
 * packageName    : com.kobe.numberguessinggame.model
 * fileName       : Game
 * author         : kobe
 * date           : 2025. 9. 19.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025. 9. 19.        kobe       최초 생성
 */
@Getter
public class Game {
	private final String id;
	private final int targetNumber;
	private int attempts;
	private boolean isWon;

	public Game() {
		this.id = UUID.randomUUID().toString();
		this.targetNumber = (int) (Math.random() * 100) + 1; // 1 ~ 100
		this.attempts = 0;
		this.isWon = false;
	}

	public String guess(int number) {
		if (isWon) {
			return "이미 정답을 맞췄습니다!";
		}

		this.attempts++;

		if (number == this.targetNumber) {
			this.isWon = true;
			return "CORRECT 🎉";
		} else if (number < this.targetNumber) {
			return "UP 👍";
		} else {
			return "DOWN 👎";
		}
	}
}
