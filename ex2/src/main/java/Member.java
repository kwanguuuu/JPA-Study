import javax.persistence.*;
import java.util.Date;

@Entity(name = "MEMBER")
public class Member {

    @Id @GeneratedValue
    @Column(name="MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

//    @Column(name = "TEAM_ID")
//    private Long teamId;

    //멤버와 팀의 관계에서 봤을때, 멤버가 다, 팀이 1
    @ManyToOne
    @JoinColumn(name="TEAM_ID")
    private Team team;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
