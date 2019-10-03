package hellojpa;

import javax.persistence.*;

public class JpaMain3_FLUSH {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em =emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try {
            System.out.println("BEGIN=========================");
            //1. 즉시호출
            Member member = new Member(200L,"member200");
            em.persist(member);
            em.flush();

            //플러시가 실행 되어도, 1차 캐시는 그대로 유지됨.
            //오직 쓰기지연SQL들, 변경감지등 발생한 것들이 데이터베이스에 반영된다고 생각하면 된다.


            //플러시 모드 옵션
            em.setFlushMode(FlushModeType.COMMIT);

            //FlushModeType.AUTO
            //커밋이나 쿼리를 실행할 때 플러시(기본값)            //FlushModeType.AUTO
            //FlushModeType.COMMIT
            //커밋할 때만 플러시 (가끔 써본적 있음 큰 도움 안됨.. ) 그냥 오토로 써라~

            //플러시는
            //1. 영속성 컨텍스트를 비우진 않음
            //2. 영속성 컨텍스트의 변경내용을 데이터베이스에 동기화
            //3. 트랜잭션이라는 작업 단위가 중요 -> 커밋 직전에만 동기화 하면 됨.

            System.out.println("============================");
            tx.commit();    //이 때 커밋으로 넘어감
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        System.out.println("=========END=========");


    }
}
