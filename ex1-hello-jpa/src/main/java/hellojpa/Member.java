package hellojpa;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity (name ="Member")//필수
public class Member {

    @Id
    private long id;
    private String name;
    private int age2;

    //JPA에서 생성자 선언으로 할때, 기본 생성자가 반드시 있어야 함.
    public Member() {
    }

    public Member(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
