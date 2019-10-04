package hellojpa;

import javax.persistence.*;

public class JpaMain4_detached {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em =emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try {
            Member member = em.find(Member.class,100L);
            member.setName("100");

            //em.detach(member);  //detached로 JPA에서 떼어냄. tx실행시 아무것도 안일어남 -> update 쿼리 실행 안되는 걸 알 수 있음.
            em.clear();     //영속성 컨텍스트를 통으로 지움

            Member member2 = em.find(Member.class,100L);


            tx.commit();    //이 때 커밋으로 넘어감
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        System.out.println("=========END=========");


    }
}
