package Busan.Matjib.domain.member;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "Members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId ;

    @Column(name = "userId", nullable = false)
    private Long id ;
    private String pwd;
    private String name;

    @Embedded
    private Address address;
    private String tel;

    @Enumerated(EnumType.STRING)
    private roleStatus role;
    private LocalDateTime date;

    protected Member() {
    }

}


enum roleStatus {
    ADMIN, ASSISTANT_ADMIN, USER
}


