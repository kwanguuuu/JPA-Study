import hellojpa.domain.item.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try {
            Book book = new Book();
            book.setName("오브젝트");
            book.setAuthor("조영호");
            em.persist(book);
            tx.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }

        emf.close();    //스키 자동생성이 create-drop일 떄, 드롭이 이거 수행 후 이뤄짐.
        System.out.println("=========END=========");


    }
}
