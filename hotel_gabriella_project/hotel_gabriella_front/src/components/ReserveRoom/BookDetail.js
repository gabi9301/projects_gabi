import { useContext, useState } from "react";
import { useHistory } from "react-router-dom";
import DateContext from "../../store/date-context";
import SearchContext from "../../store/search-context";
import PopupDom from "../UI/PopupDom";
import classes from "./BookDetail.module.css";
import BookInput from "./BookInput";
import BookSelected from "./BookSelected";

const BookDetail = (props) => {
  const [name, setName] = useState("");
  const [phone, setPhone] = useState("");
  const [nameIsValid, setNameIsValid] = useState(false);
  const [phoneIsValid, setPhoneIsValid] = useState(false);
  const phoneRegex = /^01([016789])\d{8}$/;

  const history = useHistory();

  const reserveRoom = (bookingInfo) => {
    const requestOptions = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(bookingInfo),
    };

    try {
      fetch("http://139.150.65.169:8080/book.do", requestOptions)
        .then(function(response){
              if(response.status ===201){
                alert("예약이 완료되었습니다.")
                history.push("/");
              }else{
                alert("예약을 다시 확인해 주세요.")
              }

        })

    } catch (error) {
      console.log(error);
    }
  };

  const bookingData = {
    reserveRequest: {
      name: name,
      phone: phone,
      checkIn: props.searchData.checkIn,
      checkOut: props.searchData.checkOut,
      capacity: props.searchData.capacity,
      isMember: false,
      isCanceled: false,
      reservationType: "BookReservation"
    },
    roomId: props.roomId,
    viewType: props.viewType

  }

  const reserverChangeHandler = (e) => {
    setName(e.target.value);
    if(e.target.value !== ""){
      setNameIsValid(true);
    }
  }

  const phoneChangeHandler = (e) => {
    setPhone(e.target.value);
    if(phoneRegex.test(e.target.value)){
      setPhoneIsValid(true);
    }
  }

  const bookHandler = () => {
    if(nameIsValid && phoneIsValid){
      reserveRoom(bookingData);
    }else{
      alert("입력값을 다시 확인해주세요.")
    }
   
  }

  //여기에서 헤더에 토큰 정보 넣고 예약 요청 정보를 보내서(회원일 경우)
  //예약이 끝나면 일단 index로 보내기
  //예약 조회 서비스는 이름 + 전화번호로 리스트 뽑아서 보여주게 할 것

  return (
    <div className={classes.detail_wrapper}>
      <div className={classes.detail_content_wrapper}>
        <div className="">
        <BookInput input_name="예약자명 (필수): " name="name" value={name} changeHandler={reserverChangeHandler} />
        <BookInput input_name="휴대폰번호 (필수): " name="phone" value={phone} changeHandler={phoneChangeHandler} placeholder=" - 제외한 전화번호"/>
        <BookSelected selected_name="체크인 날짜: " selected_content={props.searchData.checkIn} />
        <BookSelected selected_name="체크아웃 날짜: " selected_content={props.searchData.checkOut} />
        <BookSelected selected_name="인원: " selected_content={props.searchData.capacity} />
        </div>
      <div className="">
        <p>룸타입 : {props.roomType}</p>
        <p>전망 : {props.viewType}</p>

      </div>
      
      <div>
      <button onClick={bookHandler}>예약하기</button>
      </div>
      </div>
    </div>)


}
export default BookDetail;