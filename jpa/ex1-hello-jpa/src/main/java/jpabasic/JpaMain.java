package jpabasic;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        /**
         * JPA에서 가장 중요한 2가지!!
         * 1. 객체와 관계형 데이터베이스 매핑하기(Object Relational Mapping, ORM)
         * 2. 영속성 컨텍스트
         */
        // emf는 로딩시점에 딱 하나만 만들어져야 함
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        // 최소 단위마다 em을 생성해야 함, 엔티티 매니저는 쓰레드간 공유 절대 금지(사용하고 버려라)
        EntityManager em = emf.createEntityManager();

        // JPA에서는 트랜잭션 단위가 매우 중요, 모든 데이터 변경은 트랜잭션 안에서 실행
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // 비영속 상태
            Member member = new Member();
            member.setId(100L);
            member.setName("HelloJPA");

            // 영속 상태
            em.persist(member);

            // 영속성 컨텍스트에서 분리, 준영속 상태
            em.detach(member);

            // 객체를 삭제한 상태
            em.remove(member);

            // 커밋화는 시점에 DB 쿼리가 날아감
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
//            // 영속성 컨텍스트(엔티티 매니저 생명주기)
//            // 비영속 상태
//            Member member = new Member();
//            member.setId(100L);
//            member.setName("HelloJPA");
//
//            // 영속 상태
//            em.persist(member);
//
//            // 영속성 컨텍스트에서 분리, 준영속 상태
//            em.detach(member);
//
//            // 객체를 삭제한 상태
//            em.remove(member);
//
//            // 커밋화는 시점에 DB 쿼리가


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