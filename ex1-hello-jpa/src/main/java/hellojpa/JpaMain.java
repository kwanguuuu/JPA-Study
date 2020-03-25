package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            Member member = new Member();
//            member.setId(50L);
            member.setUsername("B");
            System.out.println("======persist========");
            entityManager.persist(member);
            System.out.println("member.getId() : " + member.getId());
            System.out.println("=======commit========");
            transaction.commit();
        } catch (Exception e){
            transaction.rollback();
        } finally {
            entityManager.close();
        }
        entityManagerFactory.close();
    }
}
