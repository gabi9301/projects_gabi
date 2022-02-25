package com.trip.hotel_gabriella.user.service.member;

import com.trip.hotel_gabriella.common.domain.Member;
import com.trip.hotel_gabriella.common.model.MemberInfo;
import com.trip.hotel_gabriella.common.model.ReservationInfo;
import com.trip.hotel_gabriella.user.model.member.MyPageModifyCommand;
import com.trip.hotel_gabriella.user.model.member.MyPageReadResponse;
import com.trip.hotel_gabriella.user.repository.CustomQueryDslRepository;
import com.trip.hotel_gabriella.user.repository.MemberRepository;
import com.trip.hotel_gabriella.user.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MyPageServiceImpl implements MyPageService{

    private final MemberRepository memberRepository;

    private final ReservationRepository reservationRepository;

    private final CustomQueryDslRepository customQueryDslRepository;


    @Override
    public MyPageReadResponse readMyPage(Long id) {
        return null;
    }

    @Override
    public MemberInfo modifyMyPage(MyPageModifyCommand myPageModifyCommand) {
        return null;
    }

    @Override
    public MemberInfo readMyPagePersonalInfo(Long id) {
        Member findMember = memberRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);

        return new MemberInfo().fromEntity(findMember);
    }

    @Override
    public List<ReservationInfo> readMyPageReservationInfo(Long id) {
        return customQueryDslRepository.findReservationsByMemberId(id);
    }
}
