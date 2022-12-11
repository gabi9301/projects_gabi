import { useEffect, useMemo, useState } from "react";
import { useTable } from "react-table";
import Table from "../UI/Table";

const ReservationResult = ({location}) =>{

const reservationResult = location.state.ReservationResult;

//console.log("?/?" + reservationResult);
const data = reservationResult.map((item)=>{
    return {
        "reservationInfo" : "",
        "reservation" : {
            "reservationId" : item.reservationInfo.id,
            "name":item.reservationInfo.name,
            "checkIn": item.reservationInfo.checkIn,
            "checkOut": item.reservationInfo.checkOut,
            "capacity": item.reservationInfo.capacity
        },
        "roomInfo" : "",
        "room" : {
            "roomType": item.roomInfo.roomType,
            "viewType": item.roomInfo.viewType,
            "capacity": item.roomInfo.capacity
        }
    
}
})

const columns = useMemo(
    ()=>[

    {Header: "예약정보",

    columns: [
        {
        Header: "예약번호",
        accessor: "reservation.reservationId"
        },
        {
        Header: "예약자명",
        accessor: "reservation.name"
        },
        {
        Header: "체크인",
        accessor: "reservation.checkIn"
        },
        {
        Header: "체크아웃",
        accessor: "reservation.checkOut"
        },
        {
        Header: "인원",
        accessor: "reservation.capacity"
        },

    ]

},
{Header: "객실정보",

    columns: [
        {
        Header: "객실타입",
        accessor: "room.roomType"
        },
        {
        Header: "전망",
        accessor: "room.viewType"
        },
        {
        Header: "정원",
        accessor: "room.capacity"
        }
        
    ]

}

]);


const resultList = 
    (<div>
        <Table columns={columns} data={data}></Table>
    {/* <span>{item.reservationInfo.id}</span> */}
    </div>);


return (
    <div>
    {reservationResult.length === 0 ? "NO RESULTS FOUND" : resultList}

    </div>
)

}

export default ReservationResult;