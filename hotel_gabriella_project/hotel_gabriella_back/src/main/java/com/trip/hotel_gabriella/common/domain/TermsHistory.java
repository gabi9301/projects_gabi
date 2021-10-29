package com.trip.hotel_gabriella.common.domain;

import com.trip.hotel_gabriella.common.validation.annotation.Agreement;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "terms_history")
public class TermsHistory extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "terms_history_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @NotBlank(message = "동의항목 코드는 필수항목 입니다.")
    private String termsCode;


    @PastOrPresent
    private LocalDateTime beginAt;


    @PastOrPresent
    private LocalDateTime endAt;

    @NotBlank(message = "동의여부는 필수항목 입니다.")
    @Agreement
    private String agreeYn;

}
