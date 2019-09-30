## JPA 시작

주요내용
- h2 데이터베이스 설치 및 실행
- 메이븐 라이브러리 설정
- /METE-INF/persistence.xml 
- JPA 클래스 사용해보기

---
### H2 데이터베이스 설치 및 실행.

h2데이터베이스 파일을 다운로드 받아 설치 후, 아래 경로의 파일 실행
/설치디렉토리/bin/h2.sh
다운로드 링크 : http://www.h2database.com/

⭐ h2의 버전과 JPA에 작성하는 버전을 일치시켜야 함
⭐⭐ 나는 프로젝트 설정 시,h2-xxx.jar 파일을 프로젝트에가 가져오지 않아 h2/bin에 있는 .jar 파일을 프로젝트에 임포트 시켜줌.

---

### 메이븐 설정

메이븐 설정 후, 기본적으로 hibernate, h2driver 부분의 레퍼지토리를 추가해 줌.

```xml
//pom.xml 문서
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>jpa-basic</groupId>
    <artifactId>ex1-hello-jpa</artifactId>
    <version>1.0.0</version>

    <dependencies>
        <!--        JPA Hibernate-->
        <dependency>
            <groupId>org.hibernate</groupId>
            <artifactId>hibernate-entitymanager</artifactId>
            <version>5.3.10.Final</version>  <!-- 현재 가장 안정되고 오래 사용한 버전 -->
        </dependency>

        <!-- h2 database  -->
        <!-- h2 database 엔진 -->
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <version>1.4.199</version> <!-- 설치한 h2db와 같은 버전으로 설치 -->
            <scope>test</scope>
        </dependency>

        <!-- maven build시, 빌드환경이 맞지 않아서 추가해 준 라이브러리. -->
        <dependency>
            <groupId>javax.xml.bind</groupId>
            <artifactId>jaxb-api</artifactId>
            <version>2.3.0</version>
        </dependency>
    </dependencies>
</project>

```

---
### JPA 설정하기.

JPA 설정파일로, 항상 위치는 resource/META-INF/persistence.xml 란 파일로 존재함.
설정 문서 안에 persistence-unit 에서 지정한 이름으로, 소스에서 참조해 읽는다.
추가정보
JPA표준속성 : javax.persistence의 패키지에 존재
하이버네이트 전용 속성 : hibernate로 시작함.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<persistence version="2.2"
             xmlns="http://xmlns.jcp.org/xml/ns/persistence" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence http://xmlns.jcp.org/xml/ns/persistence/persistence_2_2.xsd">
    <persistence-unit name="hello"> <!-- 지정한 이름인 hello로 참조. -->
        <properties>
            <!-- 필수 속성 -->
            <property name="javax.persistence.jdbc.driver" value="org.h2.Driver"/>
            <property name="javax.persistence.jdbc.user" value="sa"/>
            <property name="javax.persistence.jdbc.password" value=""/>
            <property name="javax.persistence.jdbc.url" value="jdbc:h2:tcp://localhost/~"/>    <!-- 경로설정 -->

            <property name="hibernate.dialect" value="org.hibernate.dialect.H2Dialect"/> <!-- H2Dialect!. 디비 종속적이지 않도록 JPA가 알아서 해석해준다. -->
            <!-- 옵션 -->
            <property name="hibernate.show_sql" value="true"/>
            <property name="hibernate.format_sql" value="true"/>
            <property name="hibernate.use_sql_comments" value="true"/>
            <!--<property name="hibernate.hbm2ddl.auto" value="create" />-->
        </properties>
    </persistence-unit>
</persistence>
```

위의 persistence.xml파일에 작성된 property를 살펴보면,
name 참조가 javax.persistence.~~ 로 되어있는건 JPA 표준을 참조하고 있는것.
    ex. jdbc.driver, user, password, url 등
name 참조가 hibernate.~~ 로 되어 있는것은 하이버네이트 전용 속성이다.
    - dialect.H2Dialect : db에 종속적이지 않도록 설정하는것. 쿼리 수행시 h2에서 종속적인 표현들을 알아서 해석해줘, 사용자가 신경쓰지 않도록 함
    - show_sql : 실행되는 sql을 콘솔에 출력할 지 보여주는 여부
    - format_sql : sql이 읽기 쉽도록 포맷해 줌.
    - use_sql_comments : sql이 어디에서 실행 되었는지를 알려줌.

**DB 방언**
각각 데이터베이스가 제공하는 sql 중 표준과 조금씩 다른 sql을 말함.
 - 가변문자 : VARCHAR(MySQL), VARCHAR2(Oracle)
 - 함수(문자열자르기) : SUBSTRING() (표준), SUBSTR() (Oracle)
 - 페이징 : LIMIT(MySQL), ROWNUM(Oracle) 등

해당 방언을 persistence.xml 프로퍼티 중 dialect부분에 어떤 db를 사용해주는지 명시해주면, 해당 db에 맞도록 JPA가 알아서 쿼리를 작성한다. 
-> 플랫폼 변경해도 사용하기가 쉽다.

---
### JPA 애플리케이션 개발

JPA 기본 구동방식
persistence 객체가 /META-INF/persistence.xml을 조회하고
조회에 맞춰 EntityManagerFactory를 생성함.
EntityManagerFactory로 부터 EntityManager를 생성해, 한 트랜잭션당 하나의 엔티티 매니저를 갖도록 코딩한다.


**객체와 테이블 매핑하기**
객체에 @Entity, @Id 등 persistence관련 어노테이션을 사용해 객체와 매핑해준다
- @Entity : 해당 객체가 JPA가 관리할 객체라는 것을 말해줌. 테이블과 이름이 같으면 그대로, 다르다면 name="테이블명"을 준다. 필수 항목
- @Id : 해당 객체에서 PK로 사용하는 것에 Id값을 준다. 컬럼명과 다르면 name="컬럼명" 을 줌

```java
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity //필수
public class Member {

    @Id
    private long id;

    private String name;

    //getter, setter...
}
```

이렇게 생성한 entity를 JPA가 사용할 수 있도록 테스트 코드 작성해봄.
JPA 사용시, 불러오는 클래스는 EntityManagerFactory, EntityManager, EntityTransaction등이 있음.
```java
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    // EntityManagerFactory는 어플리케이션에 1개만 있으면 되고,
    // 이후 JPA사용시, 트랜잭션마다 entityManager를 만들어 실행한다 
    // EntityManager는 db 트랜잭션 이라고 생각할 수 있음.
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello"); //persistence.xml 에 있는 persistence-unit 명을 입력

    EntityManager em = emf.createEntityManager(); //

    EntityTransaction tx = em.getTransaction(); //EntityManager로 부터 트랜잭션을 호출해서 사용함.

    tx.begin();  //트랜잭션 시작

    //  to do something...

    tx.commit(); //or tx.rollback()  트랜잭션 종료

    em.close();  // entityManager() 종료

    emf.close(); // EntityManagerFactory() 종료

}

```

**기본적인 crud 메소드**
1. 등록: entityManager.persist(// 엔티티)
2. 조회: entityManager.find(//엔티티, //조회조건)
3. 삭제: entityManager.remove(//조건);
4. 수정: ⭐entity.set~~(//변경어)

