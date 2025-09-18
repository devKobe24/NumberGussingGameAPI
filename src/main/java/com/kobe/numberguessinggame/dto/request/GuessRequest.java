package com.kobe.numberguessinggame.dto.request;

import lombok.Getter;
import lombok.Setter;

/**
 * packageName    : com.kobe.numberguessinggame.dto.request
 * fileName       : GuessRequest
 * author         : kobe
 * date           : 2025. 9. 19.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025. 9. 19.        kobe       최초 생성
 */
@Getter
@Setter
public class GuessRequest {
	private int guess;
	private String gameId;
}
