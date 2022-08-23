# # counterIt
LoLalytics (https://lolalytics.com) 로부터 크롤링을 활용하여 가져온 리그 오브 레전드 챔피언의 표준화된 카운터 지표를 확인할 수 있는 서비스입니다.

최대 30일까지만 데이터를 노출하는 원본 사이트의 특성 상 표본 수가 부족한 일부 챔피언 통계값의 신뢰도가 떨어질 수 있는 점을 보완하기 위해 패치 버전별 누적된 데이터들을 합산하여 통계값의 신뢰도를 높이기 위해 고안되었습니다.

또한 불필요한 데이터의 로드를 줄여 원본 사이트에 비한 지연 시간의 감소를 꾀했고, 회원이 체크한 챔피언만 결과 창에 노출되도록 하는 기능을 추가했습니다.

http://43.200.164.86:8090/

<br>

## 개발 환경
- Windows / Linux
- IntelliJ IDEA
- HeidiSQL
- GitHub

<br>

## 사용 기술
### 백엔드
#### 주요 프레임워크 / 라이브러리
- Java 11
- Spring Boot
- Spring Security
- Hibernate
- Spring Data JPA

#### Build Tool
- Maven

#### AWS 서비스
- AWS EC2
- AWS RDS

### 프론트엔드
- Javascript
- HTML/CSS
- Thymeleaf
- Fomantic-UI

<br>

## 구현 기능
### JPA를 활용한 CRUD

외부에서 크롤링 한 데이터를 DB에 저장하고 지정한 조건에 따라 화면에 노출하는 코드를 JPA를 활용하여 구현하였습니다.

관리자가 새로운 챔피언의 데이터를 등록하는 과정이나 회원이 검색 결과에 노출할 챔피언을 설정 페이지에서 저장, 수정하는 과정 역시 JPA를 활용하여 개발 생산성의 증대를 꾀했습니다.

### AJAX / Rest API

사용자와의 상호작용으로 인한 페이지 변환이 잦은 경우 자원의 효율적인 사용과 UX 개선을 위해 Rest API를 적극적으로 활용하였습니다.

### AWS 가상 서버를 활용한 배포

AWS EC2를 활용하여 Linux 환경에서 배포를 진행하였습니다.

DB 역시 EC2의 로컬 환경에서 작업하는 것이 아닌 AWS RDS를 활용하여 EC2와 RDS를 연동하는 작업을 거쳤습니다.
