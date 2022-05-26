import { useContext } from "react";
import { useHistory } from "react-router-dom";
import classes from "./SearchRoom.module.css";
import DateContext from "../../../store/date-context";
import SearchContext from "../../../store/search-context";
import Label from "../../UI/Label";
import SearchButton from "./SearchButton";

const SearchRoom = (props) => {
  const dateCtx = useContext(DateContext);
  const searchCtx = useContext(SearchContext);

  const history = useHistory();

  // const selectValue = {
  //   NONE: "비전망",
  //   OCEAN: "바다",
  //   MOUNTAIN: "산",
  // };


  const searchRoomHandler = () => {
    // console.log(dateCtx);
    // console.log(searchCtx);

    const searchData = {
      checkIn : dateCtx.checkInDate.fullDate,
      checkOut : dateCtx.checkOutDate.fullDate,
      capacity : searchCtx.countState,
      viewType : searchCtx.viewType
    };

    history.push("/searchResult", { searchData: searchData });
  };

 


  return (
    <div className={classes.container}>
      <div className={classes.inner_container}>
        <Label title="호텔" content="가브리엘라 서울" />
      </div>
      <div className={classes.inner_container} onClick={props.onShowResvOption}>
        <Label
          title="체크인"
          content={
            dateCtx.checkInDate.month +
            "월" +
            dateCtx.checkInDate.date +
            "일 (" +
            dateCtx.checkInDate.day +
            ")"
          }
        />
        <Label
          title="체크아웃"
          content={
            dateCtx.checkOutDate.month +
            "월" +
            dateCtx.checkOutDate.date +
            "일 (" +
            dateCtx.checkOutDate.day +
            ")"
          }
        />
      </div>
      <div className={classes.inner_container} onClick={props.onShowResvOption}>
        <Label title="인원" content={searchCtx.countState} />
        <Label title="전망" content={searchCtx.viewValue[searchCtx.viewType]} />

        {/* <Label title="" content="0" /> */}
      </div>
      <div className={classes.inner_container}>
        <span className={classes.promotion}>프로모션 코드</span>
      </div>
      <div className={classes.inner_container}>
        <SearchButton content="검색" onClick={searchRoomHandler}/>
      </div>
    </div>
  );
};

export default SearchRoom;
