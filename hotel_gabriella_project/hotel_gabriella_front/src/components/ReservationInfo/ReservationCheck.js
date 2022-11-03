import { useState } from "react";
import { useHistory } from "react-router-dom";
import { Fragment } from "react/cjs/react.production.min";
import classes from "./ReservationCheck.module.css";
const ReservationCheck = () => {
    const [reserver, setReserver] = useState("");
    const [phone, setPhone] = useState("");



    const history = useHistory();

    const reserverChangeHandler = (e)=>{
        setReserver(e.target.value);
    }

    const phoneChangeHandler = (e) =>{
        setPhone(e.target.value);
    }

    const ReservationReadRequest = {
        name: reserver,
        phone: phone
    }
    const reservationSearchHandler = () =>{
        findReservationList(ReservationReadRequest);
    }


    const findReservationList = (reserverInfo) =>{
        const requestOptions = {
            method: "POST",
            headers: {
              "Content-Type": "application/json",
            },
            body: JSON.stringify(reserverInfo),
          };
      
          try {
            fetch("/searchReservationHistory", requestOptions)
              .then((response) => (response.status === 200 ? response.json() : null))
              .then((result) => {

                console.log(result);

                history.push("/reservationResult", { ReservationResult: result });
              });
          } catch (error) {
            console.log(error);
          }
      

    }













    return (
        <Fragment>
            <div >
                <label htmlFor="name">예약자 이름 : </label>
                <input type="text" id="reserver" name="reserver" value={reserver} onChange={reserverChangeHandler}/>
            </div>
            <div >
                <label htmlFor="phone">휴대폰 번호 : </label>
                <input type="text" id="phone" name="phone" value={phone} onChange={phoneChangeHandler}/>
            </div>
            <button onClick={reservationSearchHandler}>검색</button>


        </Fragment>)
};


export default ReservationCheck;