package com.trip.hotel_gabriella.user.repository;

import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.trip.hotel_gabriella.common.domain.*;
import com.trip.hotel_gabriella.common.model.QRoomInfo;
import com.trip.hotel_gabriella.common.model.RoomInfo;
import com.trip.hotel_gabriella.user.model.search.RoomSearchRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

import static com.trip.hotel_gabriella.common.domain.QReservationRoom.*;
import static com.trip.hotel_gabriella.common.domain.QRoom.*;

@Repository
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
public class CustomQueryRoomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private final JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);

    private List<RoomInfo> findSuitableRooms(RoomSearchRequest roomSearchRequest){

        return queryFactory
                .select(new QRoomInfo(room.id, room.roomType, room.viewType, room.price, room.capacity))
                .from(room)
                .join(reservationRoom)
                .where(
                        room.capacity.goe(roomSearchRequest.getCapacity()),
                        room.viewType.eq(ViewType.valueOf(roomSearchRequest.getViewType())),
                        room.id.notIn(
                                JPAExpressions
                                        .select(reservationRoom.room.id)
                                        .from(reservationRoom)
                                        .where(
                                                reservationRoom.reservation.checkIn.after(roomSearchRequest.getCheckIn()),
                                                reservationRoom.reservation.checkOut.before(roomSearchRequest.getCheckOut())
                                        )
                        )
                ).groupBy(room.roomType)
                .fetch();

    }


}
