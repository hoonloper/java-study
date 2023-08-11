package jpabasic;

import javax.persistence.*;

@Entity
public class Member extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(nullable = false)
    private String name;

//    @Column(name = "TEAM_ID")
//    private Long teamId;
    @ManyToOne // Lazy -> 쿼리가 분리돼서 나감, 즉 호출할 때만 연관 테이블 조회가 된다는 의미
    @JoinColumn(name = "TEAM_ID")
    private Team team;

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

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}


// id 값을 알 수 있는 시점은 DB에 데이터가 등록된 시점임
// 영속성 컨텍스트는 PK가 있어야 됨
// IDENTITY 전략에서만 .persist() 호출한 시점애 insert 쿼리를 날림

// SEQUENCE 전략에서는 allocationSize를 통해 데이터를 미리 땡겨놓아 성능, 동시성 이슈를 해결했다
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)


// Id 직접 할당할 때 사용
// @GeneratedValue
// strategy
// IDENTITY: DB에 위임
// SEQUENCE: DB 시퀀스 오브젝트 사용, ORACLE @SequenceGenerator 필요
// TABLE: 키 생성용 테이블 사용, 모든 DB에서 사용, TableGenerator 필요
// AUTO: 방언에 따라 자동 지정

// 기본 키 제약 조건: null 아님, 유일, 변하면 안된다.
// 미래까지 이 조건을 만족하는 자연키는 찾기 어렵다. 대리키(대체키)를 사용하자
// 예를 들어 주민등록번호도 기본 키로 적절하지 않다.
// 권장: Long형 + 대체키 + 키 생성 전략 사용, Auto_increment 혹은 시퀀스 혹은 UUID



// @@@@ 필드와 컬럼 매핑
//@Entity
////@Table(name = "name")
//public class Member {
//    // PK 매핑
//    @Id
//    private Long id;
//
//    // name으로 컬럼 매핑
//    // insertable, updatable - 등록 변경이 발생할 때 넣을 것인지
//    // nullable - Not NULL 제약 조건 해제
//    // unique - unique 제약조건, 이름이 랜덤처럼 나와서 잘 사용하지 않음 보통 Table 어노테이션에서 작성
//    // length - 길이, String만 가능
//    // columnDefinition - 컬럼 설정을 직접할 수 있음
//    @Column(length = 10, insertable = true, updatable = true, nullable = true, unique = false, columnDefinition = "")
//    private String name;
//
//    // 소수점 사용할 때 활용
//    @Column(precision = 0, scale = 0)
//    private BigDecimal age;
//
//    // Enum 타입 활용
//    // Ordinal(기본값), String을 선택할 수 있는데 ordinal: 순서, string: 이름
//    // Ordinal을 사용하면 안되는 이유, 요구사항에 유연한 대응이 어렵기 때문
//    @Enumerated(EnumType.STRING)
//    private RoleType roleType;
//
//    // 날짜 타입 - 날짜, 시간, 날짜시간(timestamp)
//    // 옛날은 필요한데 지금은 필요없다, LocalDate, LocalDateTime을 지원해서
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date createdDate;
//
//    private LocalDate testLocalDate; // 년월
//    private LocalDateTime testLocalDateTime; // 년월일
//
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date lastModifiedDate;
//
//    // 큰 데이터. String은 clob으로 생성, 나머지는 blob
//    @Lob
//    private String description;
//
//    // 매핑하지 않음
//    // DB 저장, 조회 X
//    @Transient
//    private int temp;
//
//    public Member(Long id, String name) {
//    }
//}