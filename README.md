# 원데이클랜 API 서버

### 서비스 구조
서비스 아키텍처는 `헥사고날 아키텍처`를 적용한다.
비즈니스 로직이 데이터 레이어에 의존되는 것을 막기 위해 application 레이어에 port를 둬서 의존성 역전을 만든다.

~~~
├── adapter 
│   ├── in // 요청이 들어와 application port.in 을 호출하는 영역
│   │   └── web
│   └── out // application.service 에서 port.out 을 통해 호출되는 영역
│       └── persistence
├── application
│   ├── port
│   │   ├── in // application.service 로 들어오는 어댑터 요청을 받는 것을 정의
│   │   └── out // application.service 에서 나가는 요청을 정의
│   └── service // 비즈니스 로직 영역
└── domain // 도메인 모델 영역. 도메인 모델은 POJO로 구성
