package com.trip.hotel_gabriella.client.model.member;

import com.trip.hotel_gabriella.client.model.BaseDTO;
import com.trip.hotel_gabriella.common.domain.Address;
import com.trip.hotel_gabriella.common.domain.Member;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class MemberRegisterRequest extends BaseDTO implements GenericRequestEntityAdapter<Member> { //이 DTO에는 단순히 Member의 필드뿐 아니라 동의 항목 필드도 들어가야 한다.

    @NotBlank(message = "아이디는 필수항목입니다.")
    private String account;

    @NotBlank(message = "비밀번호는 필수항목입니다.")
    private String password;

    @NotBlank(message = "메일 주소는 필수항목입니다.")
    private String email;

    @NotBlank(message = "이름은 필수항목입니다.")
    private String name;

    @NotBlank(message = "생년월일은 필수항목입니다.")
    private String birth;

    @NotBlank(message = "전화번호는 필수항목입니다.")
    private String phone;

    @NotBlank(message = "주소는 필수항목입니다.")
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
