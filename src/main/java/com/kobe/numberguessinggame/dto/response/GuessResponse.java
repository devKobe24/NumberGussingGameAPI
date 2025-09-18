package com.kobe.numberguessinggame.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * packageName    : com.kobe.numberguessinggame.dto.response
 * fileName       : GuessResponse
 * author         : kobe
 * date           : 2025. 9. 19.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025. 9. 19.        kobe       최초 생성
 */
@Getter
@AllArgsConstructor
public class GuessResponse {
	private String hint;
	private int attempts;
}
