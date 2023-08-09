package jpabasic;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {
    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;

    private String name;
    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();

    // 연관관계 편의 메서드는 한쪽에만!
    public void addMember(Member member) {
        member.setTeam(this);
        members.add(member);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

}


// mappedBy가 무엇일까?
// 객체와 테이블이 관계를 맺는 차이
// 1. 객체 연관관계 = 2개
//    - 회원 -> 팀 연관 관계 1개(단방향)
//    - 팀 -> 회원 연관 관계 1개(단방향)
// 2. 테이블 연관관계 = 1개
//    - 회원 <-> 팀의 연관관계 1개(양방향)
/**
 * 양방향 매핑 규칙
 * 객체의 두 관계중 하나를 연관관계의 주인으로 지정
 * 연관관계의 주인만이 외래 키를 관리(등록, 수정)
 * 주인ㅇ ㅣ아닌쪽은 읽기만 가능
 * 주인은 mappedBy 속성 사용 X
 * 주인이 아니면 mappedBy 속성으로 지정
 * 즉, 외래키가 있는 곳을 주인으로 정하라
 */
//@OneToMany(mappedBy = "team")
