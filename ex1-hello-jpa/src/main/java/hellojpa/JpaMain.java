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
        //영속상태
        System.out.println("Persiste BEFORE");
        //em.persist(member);     //영속성 컨텍스트에 들어가면서 영속 상태가 됨. -> 1차 캐시에 저장됨
        System.out.println("Persiste AFTER");

        member = em.find(Member.class,100L); //-> 1차 캐시에서 조회함

        System.out.println("findMember.id = " + member.getId());
        System.out.println("findMember.name = " + member.getName());
        //준영속 - 엔티티에서 분리
        //em.detach(member);

        //3. 영속 엔티티의 동일 성 보장
        /*
        Member member1 = em.find(Member.class,100L);
        Member member2 = em.find(Member.class,100L);
        System.out.println("result ==" + (member1==member2));   //결과가 true로 나옴. 1차캐시에서 비교하기 때문
        */
        //4. 쓰기 지연 -> persist() 할 때 insert query를 모아뒀다가,
        // EntityTransaction.commit() 시에 모아둔 query를 한번에 전송함.
        /*
        em.persist(new Member(110L,"member1"));
        em.persist(new Member(112L,"member2"));
        */

        //5변경 감지 -> dirty checking 이라고도 함.
        //snapshot 으로 변경을 감지함. -> 1차캐시로 들어온 entity를 snapshot에 등록해놓고
        //flush()가 호출화 되는 시점에 엔티티와 스냅샷을 비교함.
        //이때 변경이 감지되면, 업데이트 쿼리를 생성하여 업데이트함 -> 업데이트 안 써줘도 됨.
        //member = em.find(Member.class,100L);
        //member.setName("ZZZZZZ");

        System.out.println("=====================");

        tx.commit();    //이 때 커밋으로 넘어감

        System.out.println("=========END=========");


    }
}
