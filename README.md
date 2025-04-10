## 프로젝트 설명

## 1. API SPEC
### 1-1 회원 가입 API
+ URL : localhost:8080/api/v1/member/sign-up (POST)
+ Content-Type: application/json
+ Request Body
```java
  {
    "userId" : 1,
    "password": "12345678912A!",
    "name" : "1"
  }
```

+ Response : 200 ok

### 1-2 로그인 API
+ URL : localhost:8080/api/v1/member/sign-in (POST)
+ Content-Type: application/json
+ Request Body
```java
{
    "userId" : 1,
    "password": "12345678912A!"
}
```

+ 200 ok
+ body : 토큰 정보 응답
  

### 1-3 배달 조회 API
+ URL : localhost:8080/api/v1/delivery (GET)
+ Content-Type: application/json
+ Header : x-barogo-access-token
+ Request param : ?searchStartDate=2025-04-11&searchEndDate=2025-04-12

+ Response
```java
[
  {
     "deliveryId" : 1,
     "deliveryName" : "test",
     "userId" : 1,
     "status" : "PENDING",
     "city" : "seoul",
     "street" : "street",
     "createdAt" : "",
     "updatedAt" : ""
  }
]
```


### 1-4 배달 주문 수정 API   
+ URL : localhost:8080/api/v1/delivery/{id} (PATCH)
+ Content-Type: application/json
+ Request Body

```java
{
  "street" : "street",
  "city" : "city'
}
```

+ Response : 200 Ok

## 프로젝트 구조
+ common : ArgumentResolver, 예외처리, ConstraintValidator가 존재하는 영역입니다.
+ config : 환경 설정이 등록되는 부분들이 모이는 영역 입니다.
+ domain : 순수 자바 도메인 엔티티들이 존재하는 영역 입니다.
+ feature : application영역이며 클라이언트와 통신하는 영역입니다.
+ infrastruce : 저수준의 모듈이 존재하는 영역 입니다. 도메인 엔티티와 연결고리가 되는 영역입니다.
+ test
  - common : 재활용 가능한 컨텍스트가 존재하는 영역입니다.
  - controller : 컨트롤러 영역을 테스트 합니다. mock 처리하여 테스트를 진행하는 영역입니다.
  - domain : 순수 자바 도메인 엔티티에 대한 도메인 로직을 테스트 하는 영역입니다.

## 그외 / 개선 되었으면 좋을것 같은 부분
+ 도메인 엔티티와 jpa엔티티를 분리하여 설계 하였습니다.
  - 순수 도메인에 도메인 로직이 존재하도록 하여 소형 테스트를 쉽게 할 수 있도록 설계 하였습니다.

+ 배달 상태(Status)의 경우 enum형태로 관리 하였습니다.
  - convert를 활용할 필요는 없다고 판단하였습니다.

## 개선 되었으면 좋을것 같은 부분
+ record 클래스를 좀더 잘 활용해 보았으면 좋았겠지만 그렇지 못해서 아쉬웠습니다.
  - (개념 부족)

+ 멀티 모듈로 분리해서 환경을 격리 시켰으면 더 좋았을것 같습니다.

+ redis를 활용하여 조회 성능을 개선 시켰으면 좋을것 같습니다.

+ data.sql (import.sql)을 추가하지 못함
