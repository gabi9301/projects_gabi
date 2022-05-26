package com.trip.hotel_gabriella.common.domain;

import com.trip.hotel_gabriella.common.validation.annotation.*;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member extends BaseEntity{
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @NotBlank(message = "계정은 필수항목 입니다.")
    @Account
    @Column(unique = true)
    private String account;

    @NotBlank(message = "비밀번호는 필수항목 입니다.")
    private String password;

    @NotBlank(message = "이메일은 필수항목 입니다.")
    @Email
    //@Column(unique = true)
    private String email;

    @NotBlank(message = "이름은 필수항목 입니다.")
    private String name;

    @NotBlank(message = "생년월일은 필수항목 입니다.")
    @Birth
    private String birth;

    @NotBlank(message = "핸드폰 번호는 필수항목 입니다.")
    @Phone
    private String phone;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private MemberGrade memberGrade = MemberGrade.BASIC;

    @Builder.Default
    @OneToMany(mappedBy = "member")
    private List<Reservation> reservations = new ArrayList<>();





}
