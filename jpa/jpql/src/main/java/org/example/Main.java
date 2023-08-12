package org.example;

import javax.persistence.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            em.persist(member);

//            TypedQuery<Member> member = em.createQuery("select m from Member m", Member.class);
//            Query query = em.createQuery("select m.username, m.age from Member m");

//            TypedQuery<Member> query = em.createQuery("select m from Member m", Member.class);
//            List<Member> resultList = query.getResultList(); // 빈 리스트 반환

//            TypedQuery<Member> query = em.createQuery("select m from Member m where m.id = '100", Member.class);
//            Member result = query.getSingleResult(); // 결과가 정확하게 하나여야 함, 없을 때 - NoResult, 둘 이상 - NonUniqueResult

            TypedQuery<Member> query = em.createQuery("select m from Member m where m.username = :username", Member.class);
            query.setParameter("username", "member1");
            Member sigleResult = query.getSingleResult();


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}