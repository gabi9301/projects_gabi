package com.trip.hotel_gabriella.common.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter @Setter
@Entity
public class Member {
    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;
    private String account;
    private String password;
    private String email;
    private String name;
    private String birth;
    private String phone;
    @Embedded
    private Address address;
    @Enumerated(EnumType.STRING)
    private MemberGrade memberGrade;

    @OneToMany(mappedBy = "member")
    private List<Reservation> reservations;

}
