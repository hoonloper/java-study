package jpabasic;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
//@Table(name = "name")
public class Member {
    // PK 매핑
    @Id
    private Long id;

    // name으로 컬럼 매핑
    // insertable, updatable - 등록 변경이 발생할 때 넣을 것인지
    // nullable - Not NULL 제약 조건 해제
    // unique - unique 제약조건, 이름이 랜덤처럼 나와서 잘 사용하지 않음 보통 Table 어노테이션에서 작성
    // length - 길이, String만 가능
    // columnDefinition - 컬럼 설정을 직접할 수 있음
    @Column(length = 10, insertable = true, updatable = true, nullable = true, unique = false, columnDefinition = "")
    private String name;

    // 소수점 사용할 때 활용
    @Column(precision = 0, scale = 0)
    private BigDecimal age;

    // Enum 타입 활용
    // Ordinal(기본값), String을 선택할 수 있는데 ordinal: 순서, string: 이름
    // Ordinal을 사용하면 안되는 이유, 요구사항에 유연한 대응이 어렵기 때문
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    // 날짜 타입 - 날짜, 시간, 날짜시간(timestamp)
    // 옛날은 필요한데 지금은 필요없다, LocalDate, LocalDateTime을 지원해서
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    private LocalDate testLocalDate; // 년월
    private LocalDateTime testLocalDateTime; // 년월일

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    // 큰 데이터. String은 clob으로 생성, 나머지는 blob
    @Lob
    private String description;

    // 매핑하지 않음
    // DB 저장, 조회 X
    @Transient
    private int temp;

    public Member(Long id, String name) {
    }
}