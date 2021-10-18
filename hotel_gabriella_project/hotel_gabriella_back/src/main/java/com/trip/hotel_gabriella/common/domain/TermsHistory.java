package com.trip.hotel_gabriella.common.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "terms_history")
public class TermsHistory {
    @Id @GeneratedValue
    @Column(name = "terms_history_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String termCode;
    private LocalDateTime beginAt;
    private LocalDateTime endAt;
    private String agreeYn;



}
