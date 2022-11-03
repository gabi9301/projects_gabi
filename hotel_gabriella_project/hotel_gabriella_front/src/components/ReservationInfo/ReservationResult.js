import { useEffect, useState } from "react";

const ReservationResult = ({location}) =>{


const reservationResult = location.state.ReservationResult;
console.log("?/?" + reservationResult);

const resultList = reservationResult.map((item)=>(
    <span>{item.reservationInfo.id}</span>
)
)

return (
    <div>

    {resultList.length === 0 ? "NO RESULTS FOUND" : resultList}

    </div>
)

}

export default ReservationResult;