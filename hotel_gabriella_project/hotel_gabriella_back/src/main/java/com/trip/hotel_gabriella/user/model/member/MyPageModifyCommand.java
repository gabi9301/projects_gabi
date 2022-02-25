package com.trip.hotel_gabriella.user.model.member;

import com.trip.hotel_gabriella.common.domain.Address;
import com.trip.hotel_gabriella.common.domain.Member;
import com.trip.hotel_gabriella.common.interfaces.model.GenericRequestEntityAdapter;
import com.trip.hotel_gabriella.common.validation.annotation.Birth;
import com.trip.hotel_gabriella.common.validation.annotation.Phone;
import com.trip.hotel_gabriella.user.model.BaseDTO;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class MyPageModifyCommand extends BaseDTO implements GenericRequestEntityAdapter<Member> {

    @NotNull(message = "회원 고유번호는 반드시 입력해야 합니다.")
    private Long id;

    @Email
    @NotBlank(message = "이메일은 필수항목 입니다.")
    private String email;

    @NotBlank(message = "이름은 필수항목 입니다.")
    private String name;

    @NotBlank(message = "생년월일은 필수항목 입니다.")
    @Birth
    private String birth;

    @NotBlank(message = "핸드폰 번호는 필수항목 입니다.")
    @Phone
    private String phone;

    @Valid
    private Address address;

    @Override
    public Member toEntity() {
        return Member.builder()
                .id(id)
                .email(email)
                .name(name)
                .birth(birth)
                .phone(phone)
                .address(address)
                .build();
    }
}
