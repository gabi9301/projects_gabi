package com.trip.hotel_gabriella.user.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.trip.hotel_gabriella.common.domain.*;
import com.trip.hotel_gabriella.common.model.QReservationInfo;
import com.trip.hotel_gabriella.common.model.QRoomInfo;
import com.trip.hotel_gabriella.common.model.ReservationInfo;
import com.trip.hotel_gabriella.common.model.RoomInfo;
import com.trip.hotel_gabriella.user.model.search.RoomSearchRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.trip.hotel_gabriella.common.domain.QMember.member;
import static com.trip.hotel_gabriella.common.domain.QReservation.reservation;
import static com.trip.hotel_gabriella.common.domain.QReservationRoom.*;
import static com.trip.hotel_gabriella.common.domain.QRoom.*;

@Repository
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
public class CustomQueryDslRepository {

    @PersistenceContext
    private EntityManager entityManager;
    private JPAQueryFactory queryFactory;

    public List<RoomInfo> findSuitableRooms(RoomSearchRequest roomSearchRequest) {
        queryFactory = new JPAQueryFactory(entityManager);

        return queryFactory
                .select(
                        new QRoomInfo(room.id, room.roomType, room.viewType, room.price, room.capacity)
                )
                .from(room)
                .where(
                        room.capacity.goe(roomSearchRequest.getCapacity()),
                        room.viewType.eq(ViewType.valueOf(roomSearchRequest.getViewType())),
                        room.id.notIn(
                                JPAExpressions
                                        .select(reservationRoom.room.id)
                                        .from(reservationRoom)
                                        .join(reservationRoom.reservation, reservation)
                                        .where(
                                                reservation.checkIn.loe(roomSearchRequest.getCheckIn()),
                                                reservation.checkOut.goe(roomSearchRequest.getCheckOut())
                                        )
                        )
                )
                .orderBy(room.id.asc())
                .fetch()
                .stream()
                .filter(distinctByKey(RoomInfo::getRoomType))
                .collect(Collectors.toList());

    }

    public List<ReservationInfo> findReservationsByMemberId(Long member_id) {

        queryFactory = new JPAQueryFactory(entityManager);

        return queryFactory
                .select(new QReservationInfo(reservation.id
                        , reservation.checkIn.stringValue()
                        , reservation.checkOut.stringValue()
                        , reservation.name
                        , reservation.phone
                        , reservation.capacity))
                .from(reservation)
                .join(reservation.member, member)
                .where(member.id.eq(member_id), reservation.isCanceled.isFalse())
                .fetch();

    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }


}
