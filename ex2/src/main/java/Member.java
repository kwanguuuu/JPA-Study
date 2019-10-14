import javax.persistence.*;
import java.util.Date;

@Entity(name = "MEMBER")
@SequenceGenerator(name = "member_seq_generator", sequenceName = "member_seq", allocationSize = 50)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq_generator"  ) //db방언에 맞춰서 자동 생성됨
    private int id;

    private String username;

    public Member() {
    }

    public Member(int id, String username) {
        this.id = id;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
