package com.trip.hotel_gabriella.common.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "terms_history")
public class TermsHistory extends BaseEntity {
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
