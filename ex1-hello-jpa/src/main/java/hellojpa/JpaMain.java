package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.transaction.TransactionManager;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em =emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        System.out.println("=========BEGIN=========");
        //비영속
        Member member = new Member();
        member.setId(100L);
        member.setName("Peter");

        //영속상태
        System.out.println("Persiste BEFORE");
        em.persist(member);     //영속성 컨텍스트에 들어가면서 영속 상태가 됨. -> 1차 캐시에 저장됨
        System.out.println("Persiste AFTER");

        em.find(Member.class,100L); //-> 1차 캐시에서 조회함
        //준영속 - 엔티티에서 분리
        em.detach(member);
        tx.commit();    //이 때 커밋으로 넘어감
        System.out.println("=========END=========");


    }
}
