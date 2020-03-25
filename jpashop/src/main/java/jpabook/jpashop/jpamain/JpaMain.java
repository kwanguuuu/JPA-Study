package jpabook.jpashop.jpamain;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;

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
            Order order = entityManager.find(Order.class, 1L);
            Long memberId = order.getMemberId();
            Member member = entityManager.find(Member.class,memberId);

        }finally {
            transaction.commit();
            entityManager.close();
        }
        entityManagerFactory.close();
    }
}
