package com.trip.hotel_gabriella.common.domain;

import com.trip.hotel_gabriella.common.validation.annotation.Account;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    public Admin(Long id, String account, String password) {
        this.id = id;
        this.account = account;
        this.password = password;
    }
}
