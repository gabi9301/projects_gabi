package com.trip.hotel_gabriella.common.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Member extends BaseEntity{
    @Id @GeneratedValue
    @Column(name = "member_id")
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

    @Builder.Default
    @OneToMany(mappedBy = "member")
    private List<Reservation> reservations = new ArrayList<>();





}
