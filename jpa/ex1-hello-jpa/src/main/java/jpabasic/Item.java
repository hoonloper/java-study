package jpabasic;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) // Single 하나의 통합 테이블, Joined 조인형태, Table 각각의 테이블
@DiscriminatorColumn // DType
public class Item {
    @Id @GeneratedValue
    private Long id;

    private String name;
    private int price;
}
