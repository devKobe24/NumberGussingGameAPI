# 🎯 Number Guessing Game API

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.6-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Gradle](https://img.shields.io/badge/Gradle-8.14.3-blue.svg)](https://gradle.org/)

Spring Boot로 구현된 간단하면서도 재미있는 숫자 추측 게임 REST API입니다. 서버가 생성한 1부터 100 사이의 숫자를 사용자가 맞춰나가는 게임으로, 각 사용자마다 독립적인 게임 세션을 제공합니다.

## ✨ 주요 기능

- **🎮 게임 세션 관리**: 각 사용자별로 독립된 게임 세션을 생성하고 관리
- **🎲 스마트 난수 생성**: 1부터 100 사이의 임의의 정답 숫자를 안전하게 생성
- **💡 실시간 힌트 제공**: 사용자의 추측에 따라 `UP`, `DOWN`, `CORRECT` 힌트를 즉시 제공
- **📊 시도 횟수 추적**: 사용자가 정답을 맞출 때까지의 모든 시도 횟수를 실시간 추적
- **⚡ 동시성 지원**: 여러 사용자가 동시에 게임을 플레이할 수 있는 멀티 세션 지원

## 🛠️ 기술 스택

| 기술 | 버전 | 용도 |
|------|------|------|
| **Java** | 17 | 백엔드 언어 |
| **Spring Boot** | 3.5.6 | 애플리케이션 프레임워크 |
| **Spring Web** | - | REST API 구현 |
| **Lombok** | - | 보일러플레이트 코드 제거 |
| **Gradle** | 8.14.3 | 빌드 및 의존성 관리 |

## 🚀 빠른 시작

### 📋 사전 요구사항

- **Java 17** 이상
- **Gradle 8.14** 이상 (Gradle Wrapper 사용 가능)

### 🔧 설치 및 실행

1. **프로젝트 복제**
   ```bash
   git clone <repository-url>
   cd NumberGuessingGame
   ```

2. **프로젝트 빌드**
   ```bash
   # Unix/Linux/macOS
   ./gradlew clean build
   
   # Windows
   gradlew.bat clean build
   ```

3. **애플리케이션 실행**
   ```bash
   # Unix/Linux/macOS
   ./gradlew bootRun
   
   # Windows
   gradlew.bat bootRun
   ```

4. **서버 실행 확인**
   
   서버가 성공적으로 시작되면 다음 주소에서 API를 사용할 수 있습니다:
   ```
   http://localhost:8080
   ```

## 📖 API 문서

**Base URL**: `http://localhost:8080/api/game`

### 🎯 1. 게임 시작

새로운 게임 세션을 시작하고 고유한 `gameId`를 발급받습니다.

**엔드포인트**: `POST /start`

**요청 예시**:
```bash
curl -X POST http://localhost:8080/api/game/start
```

**응답 예시**:
```json
{
    "message": "새로운 게임을 시작합니다! 1부터 100 사이의 숫자를 맞춰보세요. 🤔",
    "gameId": "550e8400-e29b-41d4-a716-446655440000"
}
```

### 🤔 2. 숫자 추측

발급받은 `gameId`와 추측한 숫자를 제출하여 힌트를 받습니다.

**엔드포인트**: `POST /guess`

**요청 본문**:
```json
{
    "gameId": "550e8400-e29b-41d4-a716-446655440000",
    "guess": 50
}
```

**응답 예시**:

- **정답보다 낮은 경우** (200 OK):
  ```json
  {
      "hint": "UP 👍",
      "attempts": 1
  }
  ```

- **정답보다 높은 경우** (200 OK):
  ```json
  {
      "hint": "DOWN 👎",
      "attempts": 2
  }
  ```

- **정답인 경우** (200 OK):
  ```json
  {
      "hint": "CORRECT 🎉",
      "attempts": 7
  }
  ```

### 🎮 게임 플레이 예시

```bash
# 1. 게임 시작
curl -X POST http://localhost:8080/api/game/start

# 2. 첫 번째 추측 (예: 50)
curl -X POST http://localhost:8080/api/game/guess \
  -H "Content-Type: application/json" \
  -d '{"gameId":"your-game-id","guess":50}'

# 3. 힌트에 따른 다음 추측 계속...
curl -X POST http://localhost:8080/api/game/guess \
  -H "Content-Type: application/json" \
  -d '{"gameId":"your-game-id","guess":75}'
```

### ⚠️ 에러 응답

| HTTP 상태 | 상황 | 응답 메시지 |
|-----------|------|-------------|
| **400 Bad Request** | 존재하지 않는 gameId | "존재하지 않는 게임 ID입니다." |
| **400 Bad Request** | 잘못된 요청 형식 | 요청 형식을 확인해주세요 |

## 📁 프로젝트 구조

```
src/
└── main/
    ├── java/com/kobe/numberguessinggame/
    │   ├── NumberGuessingGameApplication.java  # 🚀 메인 애플리케이션
    │   ├── controller/
    │   │   └── GameController.java             # 🎮 API 엔드포인트
    │   ├── service/
    │   │   └── GameService.java                # ⚙️ 핵심 게임 로직
    │   ├── model/
    │   │   └── Game.java                       # 🎯 게임 상태 모델
    │   └── dto/
    │       ├── request/
    │       │   └── GuessRequest.java           # 📥 요청 DTO
    │       └── response/
    │           └── GuessResponse.java          # 📤 응답 DTO
    └── resources/
        └── application.yml                     # ⚙️ 애플리케이션 설정
```

## 🎯 게임 규칙

1. **시작**: `/start` 엔드포인트로 새 게임을 시작합니다
2. **추측**: 1부터 100 사이의 숫자를 추측합니다
3. **힌트**: 
   - `UP 👍`: 더 큰 숫자를 입력하세요
   - `DOWN 👎`: 더 작은 숫자를 입력하세요
   - `CORRECT 🎉`: 정답입니다!
4. **승리**: 정답을 맞추면 게임이 종료됩니다

## 🔧 개발 및 빌드

### 개발 모드로 실행
```bash
./gradlew bootRun
```

### 테스트 실행
```bash
./gradlew test
```

### JAR 파일 생성
```bash
./gradlew bootJar
```

생성된 JAR 파일은 `build/libs/` 디렉토리에서 찾을 수 있습니다.

### JAR 파일 직접 실행
```bash
java -jar build/libs/NumberGuessingGame-0.0.1-SNAPSHOT.jar
```

## 🎯 최적화 전략

이진 탐색 알고리즘을 사용하면 최대 7번의 시도로 정답을 맞출 수 있습니다:

1. 첫 추측: 50 (중간값)
2. 힌트에 따라 범위를 반으로 줄여가며 추측
3. 최악의 경우도 ⌈log₂(100)⌉ = 7번 이내에 정답 도출

## 🤝 기여하기

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 라이선스

이 프로젝트는 [MIT License](LICENSE) 하에 배포됩니다.

## 📞 문의

프로젝트 관련 문의사항이 있으시면 이슈를 등록해주세요.

---

<div align="center">
  Made with ❤️ by <strong>Kobe</strong>
</div>