### JPA 프로젝트 세팅 시 이슈

1. maven lib 설정시 문제
intellij와 maven target이 설정한 기본 jdk 버전이 달라서 빌드 에러발생


2. h2데이터베이스 라이브러리
h2 db를 maven repo로 등록해서 패키지 받았는데,
h2.jar만 없어서 커넥션 연결이 안됨.

설치했던 /h2/bin/h2.jar를 프로젝트 외부 라이브러리로 추가해줘서 해결함
