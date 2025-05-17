# Spring CQRS PostgreSQL 예제 프로젝트

## 프로젝트 개요
이 프로젝트는 CQRS(Command Query Responsibility Segregation) 패턴을 Spring Boot와 함께 구현한 예제입니다. 쓰기 모델은 PostgreSQL을 사용하고 읽기 모델은 MongoDB를 사용합니다.

## 기술 스택
- Kotlin 1.9.25
- Java 21
- Spring Boot 3.4.5
- Spring Data JPA
- Spring Data MongoDB
- PostgreSQL (Command/쓰기 모델)
- MongoDB (Query/읽기 모델)
- Gradle (빌드 도구)

## 아키텍처
CQRS 패턴을 사용하여 명령(Command)과 조회(Query) 책임을 분리합니다:
- **Command 모델**: 데이터 생성/수정/삭제 작업을 담당 (PostgreSQL)
- **Query 모델**: 데이터 조회 작업을 담당 (MongoDB)

## 설치 및 실행 방법

### 필수 조건
- JDK 21
- Docker 및 Docker Compose

### 데이터베이스 설정
도커 컴포즈로 필요한 서비스 실행:

## API 엔드포인트

### 상품 관리 (Command)
- **상품 생성**: `POST /api/products`
- **상품 수정**: `PUT /api/products/{id}`
- **상품 삭제**: `DELETE /api/products/{id}`

### 상품 조회 (Query)
- **전체 상품 목록 조회**: `GET /api/products`
- **특정 상품 조회**: `GET /api/products/{id}`
- **상품 검색**: `GET /api/products/search?keyword={keyword}`


### 데이터 동기화 구현
CQRS 아키텍처에서 중요한 부분인 Command 모델(PostgreSQL)과 Query 모델(MongoDB) 간의 데이터 동기화에 대한 설명입니다.

#### 동기화 전략
- **동기화 상태 추적**: `ProductEntity`에 `syncedToReadModel` 필드를 사용하여 각 레코드의 동기화 상태를 추적합니다.

#### 동기화 흐름
1. 상품 생성/수정 시 Command 모델(PostgreSQL)에 데이터 저장  
2. 스케줄링을 통해 MongoDB의 Query 모델 업데이트  
3. 동기화 완료 후 원본 엔티티의 `syncedToReadModel` 플래그 업데이트


## 주의사항

### Git 설정
- data 디렉토리와 같은 로컬 데이터는 `.gitignore`에 추가되어 있습니다.