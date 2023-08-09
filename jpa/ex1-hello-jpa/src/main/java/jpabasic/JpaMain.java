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
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setName("member1");
            member.setTeam(team);
            em.persist(member);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
/*
 * 준영속 상태
 * 영속 -> 준영속
 * 영속 상태의 엔티티가 영속성 컨텍스트에서 분리(detached)
 * 영속성 컨텍스트가 제공하는 기능을 사용 못함
 */
// 영속
//            Member member = em.find(Member.class, 150L);
//            // 더티체킹
//            member.setName("AAAAA");
//
//            // 특정 엔티티를 준영속 상태로 전환, JPA에서 관리하지 않음, 직접 사용할 일은 거의 없다.
//            em.detach(member);
//
//            // em 내부 영속성 컨텍스트 전체를 통째로 지움
//            em.clear();
//
//            // em 종료
//            em.close();

/**
 * 이점 1. 1차 캐시
 * 엔티티를 영속시키면 1차 캐시에 키(고유값), 밸류(엔티티 그자체)로 저장됨
 * 멤버1을 조회하게 될 경우 영속성 컨텍스트 1차 캐시를 탐색함
 * 멤버2를 조회하게 될 경우 1차 캐시에 없으면 DB를 조회하고 조회한 값을 1차 캐시에 저장한 후 반환
 *
 * 이점 2. 영속 엔티티의 동일성 보장
 * 1차 캐시로 반복 가능한 읽기(REPEATABLE READ) 등급의 트랜잭션 격리 수준을
 * 데이터베이스가 아닌 애플리케이션 차원에서 제공
 *
 * 이점 3. 엔티티 등록(트랜잭션을 지원하는 쓰기 지연)
 * 영속성 컨텍스트 내부에 쓰기 지연 SQL 저장소에 쿼리를 저장한다.
 * 이후 커밋하는 순간 데이터베이스에 flush를 통해 쿼리를 날린다.
 * 버퍼링이라는 기능을 쓸 수 있음, 데이터베이스에 한번에 보낼 수 있음(Batch)
 * hibernate.jdbc.batch.size -> 이 사이즈만큼 모았다가 한번에 전송
 *
 * 이점 4. 엔티티 수정(변경 감지, 더티체킹)
 * 커밋 시점에 엔티티의 변경을 감지해서 데이터베이스에 반영함
 * 커밋하게 되면 영속성 컨텍스트 내부에서 flush를 실행하는데 이때 1차 캐시 내부에 엔티티와 스냅샷을 비교한다.
 * 최초 상태를 스냅샷에 저장해놓는데 엔티티의 값이 바뀌었을 경우 엔티티와 스냅샷의 변경이 감지되면 UPDATE SQL을 데이터베이스에 반영
 *
 * 이점 5. 엔티티 삭재
 * 위와 동일
 *
 * 이점 6. 플러시
 * 영속성 컨텍스트를 비우지 않음
 * 영속성 컨텍스트의 변경 내용을 데이터베이스에 동기화
 * 트랜잭션이라는 작업 단위가 중요 -> 커밋 직전에만 동기화하면 됨
 */
//            Member member = new Member();
//            member.setId(200L);
//            member.setName("memeber200");
//            em.persist(member);
//
//            em.flush(); // 영속성 컨텍스트 내부 쓰기 지연 쿼리를 즉시 실행함, 1차 캐시는 그대로 유지된다

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