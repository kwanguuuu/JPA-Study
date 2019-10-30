import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em =emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try {

            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setName("memberA");
            member.changeTeam(team);
            em.persist(member);

//            team.getMembers().add(member);      //이 라인의 의미가 중요함 *** 양쪽에 값을 설정
                                                // -> 연관관계 편의 메소드 생성해보자
            em.flush();
            em.clear();
            //member의 team이 어노테이션 JoinColumn 으로 조회될 수 있음.
            //Member findMember = em.find(Member.class, member.getId());

            //반대 방향으로 객체 탐식..

            Team findTeam = em.find(Team.class,team.getId());

            //왜 안가져올까.. -> team.getMembers().add(member) 가 없을 때 안가져옴..
            List<Member> members = findTeam.getMembers();
            System.out.println("===============================");
            for (Member m: members) {
                System.out.println("m = " + m.getName());
            }
            System.out.println("===============================");


            tx.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }

        emf.close();    //스키 자동생성이 create-drop일 떄, 드롭이 이거 수행 후 이뤄짐.
        System.out.println("=========END=========");


    }
}
