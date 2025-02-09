## 🎳 볼링 점수 기록 사이트 Perfect 
- Spring Boot + JPA 를 이용한 볼링 점수 기록 사이트
## 💻 PROJECT INTRODUCTION
- 목적 : Spring Boot를 이용한 볼링 점수를 기록하는 사이트로 JPA 공부 목적으로 진행
- 기능
  - 자유롭게 회원을 추가, 정보 수정, 삭제 할 수 있음
  - 게임에 참여자 선택이 가능하며, 참여자의 점수를 기록하고 계산 가능
  - 점수 평균 계산으로 순위를 회원의 전체 순위를 조회할 수 있음

## 🗓️ DEVELOPMENT PERIOD
2024.06 - 2024.07

## ⚙️ DEVELOPMENT ENVIRONMENT
- Programming Language : JAVA 17
- Framework : Spring Boot
- Database : MySQL / MySQL Workbench
- Front : HTML/CSS, JavaScript, Tymeleaf
- Tooling/ DevOps : Intellij IDEA, GitHub
- Environment : MacOS

## 💡 FEATURE

- 회원 관리 시스템 (사용자 정보 추가, 수정, 삭제)
- 볼링 게임의 기본정보 등록, 게임에 참여한 회원을 등록하고 참여자의 점수 기록
- 점수 평균 계산 및 순위 계산으로 전체 회원 순위 표시



## 📑 DEMO
|                               회원 정보 페이지                               |                               게임 점수 기록 페이지                               | 
| :---------------------------------------------------------------------: | :---------------------------------------------------------------------: |
| <img width="2054" alt="a" src="https://github.com/user-attachments/assets/8ff5e678-d5f1-469d-8a9d-0ef7643c7ece" />| <img width="2053" alt="스크린샷 2025-01-12 오후 8 59 06" src="https://github.com/user-attachments/assets/c5cf2d38-b447-4fd3-b0b5-d628f95e1143" /> |

|                               게임 점수 추가                               |                               순위 페이지                               | 
| :---------------------------------------------------------------------: | :---------------------------------------------------------------------: |
| <img width="2052" alt="스크린샷 2025-01-12 오후 8 59 28" src="https://github.com/user-attachments/assets/7f379b7c-3b3f-4fa6-a173-5a99443ca4ce" /> | <img width="2054" alt="스크린샷 2025-01-12 오후 9 00 06" src="https://github.com/user-attachments/assets/342f4462-914e-4318-b0eb-9672fd758370" />|




## 📂 PROJECT STRUCTURE
```
src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── perfect
    │   │           └── bowling
    │   │               ├── BowlingApplication.java
    │   │               ├── controller
    │   │               │   ├── ExcelController.java
    │   │               │   ├── HomeController.java
    │   │               │   ├── ScoreController.java
    │   │               │   └── bowlingController.java
    │   │               ├── dto
    │   │               │   ├── GameDto.java
    │   │               │   ├── ScoreDto.java
    │   │               │   └── UserDto.java
    │   │               ├── entity
    │   │               │   ├── GameEntity.java
    │   │               │   ├── ScoreEntity.java
    │   │               │   └── UserEntity.java
    │   │               ├── repository
    │   │               │   ├── GameRepository.java
    │   │               │   ├── ScoreRepository.java
    │   │               │   └── UserRepository.java
    │   │               ├── service
    │   │               │   ├── GameService.java
    │   │               │   ├── ScoreService.java
    │   │               │   └── UserService.java
    │   │               └── serviceImpl
    │   │                   ├── GameServiceImpl.java
    │   │                   ├── ScoreServiceImpl.java
    │   │                   └── UserServiceImpl.java
    │   └── resources
    │       ├── application.yml
    │       ├── static
    │       │   ├── css
    │       │   └── images
    │       └── templates
    └── test

```
