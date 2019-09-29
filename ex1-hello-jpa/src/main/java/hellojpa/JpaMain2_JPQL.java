package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.transaction.TransactionManager;
import java.util.List;

public class JpaMain2_JPQL {
    public static void main(String[] args) {
        //간단하게 JPQL 사용해보기.
        //JPQL : 엔티티 객체를 대상으로 쿼리
        //SQL : 데이터베이스 테이블을 대상으로 쿼리

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            List<Member> result =
                    em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(5)
                    .setMaxResults(8)
                    .getResultList();
                    //자동으로 페이지 해줌.. 오.. 그리고 RDB에 맞춰서 해줌.

            for (Member member: result) {
            System.out.println("member.name= " + member.getName());
        }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {

        }


    }
}
