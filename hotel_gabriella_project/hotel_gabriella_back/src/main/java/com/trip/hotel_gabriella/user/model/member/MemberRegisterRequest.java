package com.trip.hotel_gabriella.user.model.member;

import com.trip.hotel_gabriella.common.interfaces.model.GenericRequestEntityAdapter;
import com.trip.hotel_gabriella.common.validation.annotation.*;
import lombok.Getter;

import com.trip.hotel_gabriella.user.model.BaseDTO;
import com.trip.hotel_gabriella.common.domain.Address;
import com.trip.hotel_gabriella.common.domain.Member;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Getter
public class MemberRegisterRequest extends BaseDTO implements GenericRequestEntityAdapter<Member> {
    @NotBlank(message = "아이디는 필수항목입니다.")
    @Account
    private String account;

    @NotBlank(message = "비밀번호는 필수항목입니다.")
    @Password
    private String password;

    @NotBlank(message = "메일 주소는 필수항목입니다.")
    @Email
    private String email;

    @NotBlank(message = "이름은 필수항목입니다.")
    private String name;

    @NotBlank(message = "생년월일은 필수항목입니다.")
    @Birth
    private String birth;

    @NotBlank(message = "전화번호는 필수항목입니다.")
    @Phone
    private String phone;

    @Valid
    private Address address;

    public void setEncodedPassword(String encodedPassword) {
        this.password = encodedPassword;
    }

    @Override
    public Member toEntity() {
        return Member.builder()
                .account(account)
                .password(password)
                .email(email)
                .name(name)
                .birth(birth)
                .phone(phone)
                .address(address)
                .build();
    }

}
