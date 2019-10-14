import javax.persistence.*;
import java.util.Date;

@Entity
public class Member_2 {

    @Id
    private long id;

    @Column(name = "name")
    private String username;

    /*
    *  @Column
    *  name : 필드와 매핑할 테이블의 컬럼 이름    기본값 : 객체의 필드 이름
    *  insertable, updatable : 등록 ,변경 가능 여부
    *  nullable : null값의 허용 여부를 설정함. false로 하면 ddl에 not null 제약조건 추가
    *  unique : unique 제약조건을 걸 때 사용함. 이름을 반영하기 어려워서  Entity 클래스 줄떄 유니크 줄수 있는걸 선호함.
    *  length : 길이 제약조건을 줄수 있음
    *  columnDefinition : 데이터베이스 컬럼 정보를 직접 줄 수 있음.
    *  precision,scale: BigDecimal 컬럼에 사용함.
    * */
    private Integer age;

    @Enumerated(EnumType.STRING)        //enum타입 매
    private RoleType roleType;
    /*
    *  Enum 사용할 떄 주의사항
    *  기본 default 가 ORDINARY.
    *  EnumType.ORDINARY: Enum 순서를 데이터베이스에 저장   -> integer타입으로 저장됨 . 쓰지말아야해..
    *  EnumType.String: 이름을 데이터베이스에 저장
    * */



    @Temporal(TemporalType.TIMESTAMP)   //날짜 매핑
    private Date createDate;

    /*
    *  날짜 타입을 매핑할 떄 사용(Java.util.Date, java.util.Calendar)사용시..
    *  LocalDate, LocalDateTime 사용하면 사실 쓸 이유가 없는 필드매핑
    *
    * */

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob
    private String description; //@lob에 문자열이면 clob으로 생성됨. 아니면blob

    @Transient
    private int temp;       //특정 필드를 컬럼에 매핑하지 않음.

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }
}
