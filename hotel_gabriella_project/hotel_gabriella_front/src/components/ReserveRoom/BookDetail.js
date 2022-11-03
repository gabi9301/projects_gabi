import { useContext, useState } from "react";
import { useHistory } from "react-router-dom";
import DateContext from "../../store/date-context";
import SearchContext from "../../store/search-context";
import PopupDom from "../UI/PopupDom";
import classes from "./BookDetail.module.css";
import BookInput from "./BookInput";
import BookSelected from "./BookSelected";

const BookDetail = (props) => {
  const [reserver, setReserver] = useState("");
  const [phone, setPhone] = useState("");
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
      fetch("/book.do", requestOptions)
        .then((response) => (response.status === 200 ? response.json() : null))
        .then((result) => {
          alert("예약이 완료되었습니다.")
          history.push("/");
        });
    } catch (error) {
      console.log(error);
    }
  };

  const bookingData = {
    reserveRequest: {
      name: reserver,
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
    setReserver(e.target.value);
  }

  const phoneChangeHandler = (e) => {
    setPhone(e.target.value);
  }

  const bookHandler = () => {
    reserveRoom(bookingData);
  }

  //여기에서 헤더에 토큰 정보 넣고 예약 요청 정보를 보내서(회원일 경우)
  //예약이 끝나면 일단 index로 보내기
  //예약 조회 서비스는 이름 + 전화번호로 리스트 뽑아서 보여주게 할 것

  return (
    <div className={classes.detail_wrapper}>
      <div className={classes.detail_content_wrapper}>
        <BookInput input_name="예약자명 (필수): " name="name" value={reserver} changeHandler={reserverChangeHandler} />
        <BookInput input_name="휴대폰번호 (필수): " name="phone" value={phone} changeHandler={phoneChangeHandler} />
        <BookSelected selected_name="체크인 날짜: " selected_content={props.searchData.checkIn} />
        <BookSelected selected_name="체크아웃 날짜: " selected_content={props.searchData.checkOut} />
        <BookSelected selected_name="정원: " selected_content={props.searchData.capacity} />
      </div>
      <div className="">
        <p>룸타입 : {props.roomType}</p>
        <p>전망 : {props.viewType}</p>

      </div>
      <button onClick={bookHandler}>예약하기</button>

    </div>)


}
export default BookDetail;