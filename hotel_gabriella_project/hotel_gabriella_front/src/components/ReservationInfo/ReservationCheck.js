import { useState } from "react";
import { useHistory } from "react-router-dom";
import { Fragment } from "react/cjs/react.production.min";
import classes from "./ReservationCheck.module.css";
const ReservationCheck = () => {
    const [reserver, setReserver] = useState("");
    const [phone, setPhone] = useState("");

    const [nameIsValid, setNameIsValid] = useState(false);
    const [phoneIsValid, setPhoneIsValid] = useState(false);
    const phoneRegex = /^01([016789])\d{8}$/;


    const history = useHistory();

    const reserverChangeHandler = (e)=>{
        setReserver(e.target.value);
        if(e.target.value !== ''){
            setNameIsValid(true);
        }
    }

    const phoneChangeHandler = (e) =>{
        setPhone(e.target.value);

        if(phoneRegex.test(e.target.value)){
            setPhoneIsValid(true);
        }
    }


    const ReservationReadRequest = {
        name: reserver,
        phone: phone
    }
    const reservationSearchHandler = () =>{
        if(nameIsValid && phoneIsValid){
        findReservationList(ReservationReadRequest)
        }else{
            alert("입력된 값을 다시 확인해주세요.")
        };
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
            fetch("http://139.150.65.169:8080/searchReservationHistory", requestOptions)
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
            <div className={classes.read_reservation_container}>
                <div className={classes.read_reservation_inner_container}>
            <div className={classes.reserver_container}>
                <label htmlFor="name">예약자 이름 : </label>
                <input type="text" id="reserver" name="reserver" className={classes.reserver_input} value={reserver} onChange={reserverChangeHandler}/>
            </div>
            <div className={classes.phone_container}>
                <label htmlFor="phone">휴대폰 번호 : </label>
                <input type="text" id="phone" name="phone" validity={phoneIsValid}
                className={classes.phone_input} value={phone} onChange={phoneChangeHandler} placeholder="-제외한 전화번호"/>
            </div>
            </div>
            
            <button className={classes.read_reservation_button} onClick={reservationSearchHandler}>검색</button>
            </div>


        </Fragment>)
};


export default ReservationCheck;