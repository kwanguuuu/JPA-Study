import javax.persistence.*;
import java.util.Date;

@Entity(name = "MEMBER")
public class Member {

    @Id @GeneratedValue
    private long id;
    @Column(name = "user_name")
    private String name;

//    @Column(name ="TEAM_ID")
//    private long teamId;          객체지향적 모델링이 아님

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

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

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
