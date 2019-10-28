### JPA 프로젝트 세팅 시 이슈

1. maven lib 설정시 문제
intellij와 maven target이 설정한 기본 jdk 버전이 달라서 빌드 에러발생


2. h2데이터베이스 라이브러리
h2 db를 maven repo로 등록해서 패키지 받았는데,
h2.jar만 없어서 커넥션 연결이 안됨.

설치했던 /h2/bin/h2.jar를 프로젝트 외부 라이브러리로 추가해줘서 해결함

### H2 db 연결 
수강했던 강의에서는 jdbc url을 
jdbc:h2:tcp://localhost/~/test    에서
jdbc:h2:tcp://localhost/~/jpashop 로

바로 변경을 해도 자동으로 연결 되었는데, 나는 연결이 되지 않았다
에러메세지는 /~/jpashop.mv.db라는 파일이 없다고 해서, test.mv.db를 복사해 이름을 변경해주니 연결이 되었다.
테스트 db에 있던 데이터를그대로 들고 복사된걸 보니, 해당 파일이 h2디비 연결이 바라본 db인것 같음.

강의에선 자동으로 됬는데 왜 나는 안됬을까, 어떤 환경이나 버전이 달라서 이런 이슈가 발생했는지 알아보고 싶다.
