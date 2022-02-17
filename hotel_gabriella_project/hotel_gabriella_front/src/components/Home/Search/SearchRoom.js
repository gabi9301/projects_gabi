import { useContext } from 'react';

import classes from './SearchRoom.module.css';
import DateContext from "../../../store/date-context";
import Label from '../../UI/Label';
import SearchButton from "./SearchButton";

const SearchRoom = props => {

  const dateCtx = useContext(DateContext);

    return (
      <div className={classes.container}>
        <div className={classes.inner_container}>
          <Label title="호텔" content="가브리엘라 서울" />
        </div>
        <div
          className={classes.inner_container}
          onClick={props.onShowResvOption}
        >
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
        <div
          className={classes.inner_container}
          onClick={props.onShowResvOption}
        >
          <Label title="인원" content="2" />
          <Label title="전망" content="바다" />
          {/* <Label title="" content="0" /> */}
        </div>
        <div className={classes.inner_container}>
          <span className={classes.promotion}>프로모션 코드</span>
        </div>
        <div className={classes.inner_container}>
          <SearchButton content="검색" />
        </div>
      </div>
    );
};

export default SearchRoom;