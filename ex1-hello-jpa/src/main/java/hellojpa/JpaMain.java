package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        // EntityManagerFactory 는 어플리케이션에서 1개만
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        // 트랜젝션 마다  EntityManager는 만들어 줘야함. -> 쉽게 생각하면 db 커넥션 하나 받았다고 생각하면 돰.
        EntityManager entityManager = emf.createEntityManager();
        //code를 이 부분에 구현하게 됨.

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        try {
            //1.등록
/*
            Member member = new Member();
            member.setId(2L);
            member.setName("helloB");

            entityManager.persist(member);
*/
            //2. 조회
            Member findMember = entityManager.find(Member.class, 1L);

            System.out.println("findMember.id = " + findMember.getId() );
            System.out.println("findMember.name = " + findMember.getName() );

            //3. 삭제
            //entityManager.remove(1L);

            //4. 수정 - em.persist()로 저장 안해도 저장됨.
            // 자바 객체에서 값만 바꿔도 수정이 됨.
            findMember.setName("hello JPA");

          entityTransaction.commit();
        } catch (Exception e) {
            entityTransaction.rollback();
        } finally {
            entityManager.close();
        }

        emf.close();
    }
}
