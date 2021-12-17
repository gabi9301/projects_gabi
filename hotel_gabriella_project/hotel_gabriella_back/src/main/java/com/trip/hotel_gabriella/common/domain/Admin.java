package com.trip.hotel_gabriella.common.domain;

import com.trip.hotel_gabriella.common.validation.annotation.Account;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Getter
@Entity
public class Admin extends BaseEntity{
    @Id
    @Column(name = "admin_id")
    private Long id;

    @NotBlank(message = "계정은 필수항목 입니다.")
    @Account
    @Column(unique = true)
    private String account;

    @NotBlank(message = "비밀번호는 필수항목 입니다.")
    private String password;

}
