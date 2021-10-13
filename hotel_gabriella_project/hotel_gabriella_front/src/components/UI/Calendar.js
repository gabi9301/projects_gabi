
import { useState, useRef, useEffect, useContext } from "react";
import DateContext from "../../store/date-context";
import moment from "moment";
import classes from './Calendar.module.css';



const CHEAD_ARR = ['일', '월', '화', '수', '목', '금', '토'];

const Calendar = (props) => {

  const dateCtx = useContext(DateContext);
  //const [momentState, setMoment] = useState(props.initMoment); //moment() 는 오늘을 반환

  //const Today = moment().format("YYYYMM").toString() + moment().date();
  const Today = moment().format("YYYYMMDD").toString();

  const TheDay = props.currentMoment;
  const TheYear =TheDay.get("year");
  const TheMonth =TheDay.get("month") + 1;
  const FirstWeekDay = TheDay.startOf("month").day(); // 0: 일요일 ~ 6:토요일까지 일련 숫자

  const DaysInMonth = TheDay.daysInMonth();  //해당 달에 총 며칠이 있는지

  const DaysArray = () => {   //필요한 공백을 포함해
    let result = [];

    for (let i = 0; i < FirstWeekDay; i++) {
      result.push(" ");
    }

    for (let i = 1; i < DaysInMonth + 1; i++) {
      result.push(i.toString());
    }

    for(let i = 0; i < 42 - (DaysInMonth + FirstWeekDay); i++){
      result.push(" ");
    }

    return result;
  };

  
  // -----------------------------------------------------------
  //Calendar Head 만들기
  const CheadList = CHEAD_ARR.map((value, index) => <th key={index}>{value}</th>);

  //Calendar Body만들기

  const GenerateTdIndex = () => {   //Td에 붙여줄 유니크한 key값 생성 
    const TdIndexArray = DaysArray().map((value, index) =>
      value === " " ? index.toString() : TheDay.format("YYYYMM").concat(value<10 ? '0'.concat(value.toString()):value.toString())
    ).filter((value)=>{return value});
    return TdIndexArray;
  }




  const refs = useRef([]);

  const TdList = DaysArray().map(
    (
      value,
      index //<Td>value</Td> 형태의 배열 생성
    ) => (
      <td
        className={`
        ${`${classes.td}`}
        ${value === " " ? `${classes["blank_td"]}` : `${classes["filled_td"]}`}
        ${
          Today ===
          TheDay.format("YYYYMM").concat(
            value < 10 ? "0".concat(value.toString()) : value.toString()
          )
            ? `${classes.today}`
            : ""
        } 
        ${index % 7 === 0 ? `${classes.holiday}` : ""}
        ${
          +GenerateTdIndex()[index] < +Today && value !== " "
            ? `${classes.expired}`
            : ""
        }
        ${props.selectedInState.isSelected &&
          value !== " " &&
          +moment(GenerateTdIndex()[index].toString()).diff(
            moment(props.selectedInState.selectedInDate), 'days') > 28 &&
            props.bothSelected === false
            ? `${classes.invalid}`
            : ""
        }
        ${value !== " " &&
          +GenerateTdIndex()[index] > +props.selectedInState.selectedInDate &&
          +GenerateTdIndex()[index] < +props.selectedOutState.selectedOutDate
          ? `${classes.selectedDates}`
          : ""
        }
        ${value !== " " &&
          (+GenerateTdIndex()[index] === +props.selectedInState.selectedInDate ||
          +GenerateTdIndex()[index] === +props.selectedOutState.selectedOutDate)
          ? `${classes.selectedDate}`
          : ""
        }
        `}
        key={GenerateTdIndex()[index]}
        id={GenerateTdIndex()[index]}
        ref={(el) => {
          refs.current[index] = el;
        }}
        onClick={props.dateSelectHandler}
      >
        {value}
      </td>
    )
  );
  //   [<td> </td>, <td> </td>, <td> </td>, <td>1</td>, <td>2</td>, <td>3</td>, ....]

  const TdGroups = TdList.map((value, index) => { //위의 배열을 주 단위로 자른 다른 배열 생성
    return index % 7 === 0 ? TdList.slice(index, index + 7) : null;
  }).filter((value) => {
    return value;
  });

  const CbodyList = TdGroups.map((value, index) => (
    <tr key={index}>{value}</tr>
  ));

  useEffect(()=>{
    
  },[props.selectedInState, props.selectedOutState]);



  return (
    <div className={classes['table-wrap']}>
      
      <table className={classes.table}>
      <caption>{TheYear}년 {TheMonth}월</caption>
        <thead>
          <tr>{CheadList}</tr>
        </thead>
        <tbody>{CbodyList}</tbody>
      </table>
      {/* {props.buttonType==="prev" && <Button content="이전달" onClick={getPrevMonth} />}
      {props.buttonType==="next" && <Button content="다음달" onClick={getNextMonth} />} */}
    </div>
  );
}

export default Calendar;