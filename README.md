# Number Guessing Game API

Spring Boot로 구현된 간단한 숫자 추측 게임 REST API입니다. 서버가 생성한 1부터 100 사이의 숫자를 사용자가 맞춰나가는 게임입니다.

[](https://www.oracle.com/java/)
[](https://spring.io/projects/spring-boot)
[](https://gradle.org/)

## ✨ 주요 기능

  - **게임 세션 관리**: 각 사용자별로 독립된 게임 세션을 생성하고 관리합니다.
  - **난수 생성**: 1부터 100 사이의 임의의 정답 숫자를 생성합니다.
  - **추측 및 힌트 제공**: 사용자의 추측에 따라 `UP`, `DOWN`, `CORRECT` 힌트를 제공합니다.
  - **시도 횟수 추적**: 사용자가 정답을 맞출 때까지 시도한 횟수를 기록합니다.

## 🛠️ 기술 스택

| 기술            | 버전/종류                          | 용도                   |
| :-------------- | :--------------------------------- | :--------------------- |
| **Java** | 17                                 | 백엔드 언어            |
| **Spring Boot** | 3.5.6                              | 애플리케이션 프레임워크 |
| **Spring Web** | -                                  | REST API 구현          |
| **Lombok** | -                                  | 보일러플레이트 코드 제거 |
| **Gradle** | 8.14.3                             | 빌드 및 의존성 관리    |

## 🚀 빠른 시작

### 사전 요구사항

  - Java 17 이상
  - Gradle 8.14.3 이상

### 설치 및 실행

1.  **프로젝트 복제**

    ```bash
    git clone [저장소 URL]
    cd NumberGuessingGame
    ```

2.  **프로젝트 빌드**

    ```bash
    ./gradlew clean build
    ```

3.  **애플리케이션 실행**

    ```bash
    ./gradlew bootRun
    ```

    서버는 `http://localhost:8080`에서 실행됩니다.

## 📖 API 문서

**Base URL**: `http://localhost:8080/api/game`

### 1\. 게임 시작

**`POST /start`**

새로운 게임 세션을 시작하고 고유한 `gameId`를 발급받습니다.

  - **요청 예시 (`curl`)**:

    ```bash
    curl -X POST http://localhost:8080/api/game/start
    ```

  - **성공 응답 예시 (200 OK)**:

    ```json
    {
        "message": "새로운 게임을 시작합니다! 1부터 100 사이의 숫자를 맞춰보세요. 🤔",
        "gameId": "xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx"
    }
    ```

### 2\. 숫자 추측

**`POST /guess`**

`gameId`와 추측한 숫자를 요청 본문에 담아 제출합니다. 서버는 추측에 대한 힌트와 현재까지의 시도 횟수를 반환합니다.

  - **요청 본문**:

    ```json
    {
        "gameId": "xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx",
        "guess": 50
    }
    ```

  - **성공 응답 예시 (정답보다 낮은 경우)**:

    ```json
    {
        "hint": "UP 👍",
        "attempts": 1
    }
    ```

  - **성공 응답 예시 (정답인 경우)**:

    ```json
    {
        "hint": "CORRECT 🎉",
        "attempts": 7
    }
    ```

### 예외 처리 응답

| HTTP 상태 코드          | 상황                  |
| :---------------------- | :-------------------- |
| **400 Bad Request** | 존재하지 않는 `gameId` |

## 📁 프로젝트 구조

```
src/
└── main/
    ├── java/com/kobe/numberguessinggame/
    │   ├── NumberGuessingGameApplication.java # 메인 애플리케이션
    │   ├── controller/
    │   │   └── GameController.java            # API 엔드포인트
    │   ├── service/
    │   │   └── GameService.java               # 핵심 게임 로직
    │   ├── model/
    │   │   └── Game.java                      # 게임 상태 모델
    │   └── dto/
    │       ├── request/GuessRequest.java    # 요청 DTO
    │       └── response/GuessResponse.java  # 응답 DTO
    └── resources/
        └── application.yml                    # 애플리케이션 설정
```