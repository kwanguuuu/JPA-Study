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
            Member member = new Member();
            member.setUsername("peter");
            Member member2 = new Member();
            member2.setUsername("peter2");
            Member member3 = new Member();
            member3.setUsername("peter3");

            System.out.println("==============");
            em.persist(member);
            em.persist(member2);
            em.persist(member3);

//            System.out.println(member.getId());
            System.out.println(member.getUsername());
            System.out.println(member2.getUsername());
            System.out.println(member3.getUsername());
            System.out.println("==============");
            tx.commit();    //이 때 커밋으로 넘어감
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();

        }

        emf.close();    //스키 자동생성이 create-drop일 떄, 드롭이 이거 수행 후 이뤄짐.
        System.out.println("=========END=========");


    }
}
