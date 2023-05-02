# 원데이클랜 API 서버

## 서비스 소개
원데이클래스의 수강생들을 모집하고 해당 클래스에 참여하는 참여자들의 정보와 신청 현황을 관리하는 서비스입니다.

## 핵심 기능
- 관리자
  - 클래스를 등록 할 수 있습니다.
  - 클래스에 신청한 참여자들의 정보를 확인하며 출석을 관리 할 수 있습니다.
  - 클래스에 참여했던 참여자들의 리뷰를 확인 하고 관리 할 수 있습니다.
    
- 일반 사용자
  - 전체 클래스 목록을 확인 할 수 있습니다.
    - 클래스의 카테고리, 최신순, 거리순 정렬이 가능합니다.
    - 클래스의 제목, 내용, 태그로 검색이 가능합니다.
  - 참여 신청한 클래스들의 목록을 확인 할 수 있습니다.
  - 참여했던 클래스들에 리뷰를 작성 할 수 있습니다. 


## Tech Stacks
- Java 17
- Spring Boot 3.0.1
- Spring Data Jpa 3.0.0
- Spring Security 6.0.1
- Redis
- AWS ec2
- AWS rds


## 서비스 구조
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
~~~

## ERD
![onedayclan_erd.png](resource%2Fonedayclan_erd.png)
