package com.trip.hotel_gabriella.common.model;

import com.trip.hotel_gabriella.common.domain.Address;
import com.trip.hotel_gabriella.common.domain.Member;
import com.trip.hotel_gabriella.common.domain.MemberGrade;
import com.trip.hotel_gabriella.common.interfaces.model.GenericResponseEntityAdapter;
import com.trip.hotel_gabriella.user.model.BaseDTO;
import lombok.Getter;

@Getter
public class MemberInfo extends BaseDTO implements GenericResponseEntityAdapter<Member> {

    private Long id;
    private String account;
    private String email;
    private String name;
    private String birth;
    private String phone;
    private Address address;
    private MemberGrade memberGrade;

    @Override
    public MemberInfo fromEntity(Member member) {
        this.id = member.getId();
        this.account = member.getAccount();
        this.email = member.getEmail();
        this.name = member.getName();
        this.birth = member.getBirth();
        this.phone = member.getPhone();
        this.address = member.getAddress();
        this.memberGrade = member.getMemberGrade();

        return this;
    }
}
