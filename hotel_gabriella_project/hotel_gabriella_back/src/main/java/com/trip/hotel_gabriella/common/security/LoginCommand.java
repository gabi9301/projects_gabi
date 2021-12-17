package com.trip.hotel_gabriella.common.security;

import com.trip.hotel_gabriella.common.interfaces.model.GenericHttpRequestTransferor;
import com.trip.hotel_gabriella.common.validation.annotation.Account;
import com.trip.hotel_gabriella.common.validation.annotation.Password;
import com.trip.hotel_gabriella.user.model.BaseDTO;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class LoginCommand extends BaseDTO implements GenericHttpRequestTransferor {
    @NotBlank(message = "계정 아이디를 입력해주세요.")
    @Account
    private String account;

    @NotBlank(message = "패스워드를 입력해주세요.")
    @Password
    private String password;
}
