# Ticketing API Server

--- 

티켓팅 서비스의 특징인 대규모 트래픽을 경험해 보기 위해 진행한 프로젝트입니다. 티켓팅 서비스의 특성상 공연의 오픈 시간 트래픽이 몰리기 때문에 입장 대기 줄 서버를 추가로 구현하였습니다. 입장 대기 서버는 [여기](https://github.com/duffyishere/waiting-server)에서 확인하실 수 있습니다.

실제 서비스를 위해 만든게 아니기 때문에 공연 등록과 예매 기능만 구현이 되어 있습니다. 공연 예매를 하기까지의 흐름은 다음과 같습니다.

1. 공연을 서버에 등록한다.
2. 예매 시작 시간이 되면 사람들은 빈 좌석 목록을 보고,
3. 원하는 자리를 ‘찜'한다. 다른 사람이 예매했거나 찜한 자리는 찜할 수 없다.

## 시작하기

이 프로젝트를 시작하려면 저장소를 컴퓨터에 복제하면 됩니다.

```bash
git clone https://github.com/duffyishere/ticketing.git
```

다음으로 환경 변수를 설정해야 합니다. 프로젝트 루트에 'application-db.yml'파일을 생성하고 다음 변수들을 채우세요:

``` .env
spring:
	datasource:  
	  driver-class-name: org.postgresql.Driver  
	  url: ${POSTGRES_URL}/${DB}  
	  username: ${POSTGRES_ID}  
	  password: ${POSTGRES_PW}
```


환경 변수를 설정하고 나면 개발 서버를 실행할 수 있습니다:

``` command line
./gradlew bootRun
```

개발 서버는 http://localhost:8080 에서 실행 중이어야 합니다.

## 엔드포인트 및 HTTP 메서드

- **'GET /concert/{id}'**: 특정 공연의 정보를 가져옵니다.
- **'GET /concert/seat/{id}'**: 특정 공연의 빈 좌석 목록을 가져옵니다.
- **'POST /concert'**: 공연을 예매 정보를 생성합니다

## 에러 처리
- **`404 Not Found`**: 요청한 리소스를 찾을 수 없음.
- **`400 Bad Request`**: 잘못된 요청.
- **`401 Unauthorized`**: 권한 없음.

## 라이선스

이 API는 MIT 라이선스에 따라 배포됩니다.