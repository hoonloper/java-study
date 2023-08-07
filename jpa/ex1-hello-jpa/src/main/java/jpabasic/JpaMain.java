package jpabasic;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        // emf는 로딩시점에 딱 하나만 만들어져야 함
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        // 최소 단위마다 em을 생성해야 함, 엔티티 매니저는 쓰레드간 공유 절대 금지(사용하고 버려라)
        EntityManager em = emf.createEntityManager();

        // JPA에서는 트랜잭션 단위가 매우 중요, 모든 데이터 변경은 트랜잭션 안에서 실행
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
//            List<Member> result = em.createQuery("select m from Member as m", Member.class)
//                    .setFirstResult(1)
//                    .setMaxResults(10)
//                    .getResultList(); 페이징 처리 매우 간단


//            Member findMember = em.find(Member.class, 1L); // 조회
//            findMember.setName("HelloJPA"); // 수정

//            Member member = new Member();
//            member.setId(1L);
//            member.setName("hello");
//            em.persist(member); // 생성

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}