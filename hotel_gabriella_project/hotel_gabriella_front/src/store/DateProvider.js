import { useState } from "react";
import DateContext from "./date-context";
import moment from "moment";

const Day_ARR = ["일", "월", "화", "수", "목", "금", "토"];


const defaultDateState = {
  checkInDate: {
    year: moment().format("YYYY"),
    month: moment().format("MM"),
    date: moment().format("DD"),
    fullDate: moment().format("YYYYMMDD"),
    day: Day_ARR[moment().format("e")],
  },
  checkOutDate: {
    year: moment().clone().add(1, "day").format("YYYY"),
    month: moment().clone().add(1, "day").format("MM"),
    date: moment().clone().add(1, "day").format("DD"),
    fullDate: moment().clone().add(1, "day").format("YYYYMMDD"),
    day: Day_ARR[moment().clone().add(1, "day").format("e")],
  },
};

const DateProvider = (props) => {
    const [dateState, setDateState] = useState(defaultDateState);

    const changeDateHanlder = (date) => {
        setDateState({
          checkInDate: {
            year: date.checkInDate.year,
            month: date.checkInDate.month,
            date: date.checkInDate.date,
            fullDate: date.checkInDate.fullDate,
            day: Day_ARR[
              moment(
                date.checkInDate.year
                  .concat(date.checkInDate.month)
                  .concat(date.checkInDate.date)
              ).format("e")
            ],
          },
          checkOutDate: {
            year: date.checkOutDate.year,
            month: date.checkOutDate.month,
            date: date.checkOutDate.date,
            fullDate: date.checkOutDate.fullDate,
            day: Day_ARR[
              moment(
                date.checkOutDate.year
                  .concat(date.checkOutDate.month)
                  .concat(date.checkOutDate.date)
              ).format("e")
            ],
          },
        });
    }

 
    const dateContext = {
      checkInDate: dateState.checkInDate,
      checkOutDate: dateState.checkOutDate,
      changeDate: changeDateHanlder
    };

    return (
        <DateContext.Provider value={dateContext}>
            {props.children}
        </DateContext.Provider>
    );


};

export default DateProvider;