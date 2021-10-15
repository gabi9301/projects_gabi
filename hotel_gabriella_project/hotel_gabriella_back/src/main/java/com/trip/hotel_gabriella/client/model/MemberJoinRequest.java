package com.trip.hotel_gabriella.client.model;

import com.trip.hotel_gabriella.common.domain.Address;

import javax.validation.constraints.NotBlank;

public class MemberJoinRequest { //이 DTO에는 단순히 Member의 필드뿐 아니라 동의 항목 필드도 들어가야 한다.

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

}
