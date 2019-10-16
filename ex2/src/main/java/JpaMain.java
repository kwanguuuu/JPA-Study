import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

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
            member.setUsername("member1");
//            member.setTeamId(team.getId()); //애매하다
            em.persist(member);

            Member findMember = em.find(Member.class, member.getId());
//            Long findTeamId = findMember.getTeamId();
            em.find(Team.class, findTeamId);

            //별로 객체지향적이지 못하다.

            tx.commit();

        } catch (Exception e) {

        }

        emf.close();    //스키 자동생성이 create-drop일 떄, 드롭이 이거 수행 후 이뤄짐.
        System.out.println("=========END=========");


    }
}
