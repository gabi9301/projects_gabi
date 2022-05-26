import { useState, useEffect, useContext } from 'react';
import DateContext from "../../../store/date-context";
import moment from "moment";
import Calendar from '../../UI/Calendar';
import CapacityCounter from './CapacityCounter';
import classes from './ResvDetail.module.css';
import left_arrow from "../../../assets/image/left_arrow.png";
import right_arrow from "../../../assets/image/right_arrow.png";
import ViewSelect from './ViewSelect';

const ResvDetail = (props) => {
 
  const dateCtx = useContext(DateContext);

  const [momentState, setMoment] = useState(moment(dateCtx.checkInDate.fullDate)); //moment() 는 오늘을 반환
  const [arrowIsValid, setArrowIsValid] = useState(false);

  const selectedInDate = dateCtx.checkInDate.fullDate;
  const selectedOutDate = dateCtx.checkOutDate.fullDate;

  const selectedInStateDefault = {
    isSelected: true,
    selectedInDate: selectedInDate,
  };
  const selectedOutStateDefault = {
    isSelected: true,
    selectedOutDate: selectedOutDate,
  };

  const [selectedInState, setSelectedInState] = useState(selectedInStateDefault);
  const [selectedOutState, setSelectedOutState] = useState(selectedOutStateDefault);
  const [bothSelected, setBothSelected] = useState(true);


  
  useEffect(()=>{
    +momentState.format("YYYYMM") <= +moment().format("YYYYMM")
    ? setArrowIsValid(false)
    : setArrowIsValid(true)
  }, [momentState]);

  useEffect(()=>{
    if(bothSelected) {
    const checkInDate = moment(selectedInState.selectedInDate);
    const checkOutDate = moment(selectedOutState.selectedOutDate);
    const dateInfo = {
      checkInDate: {
        year: checkInDate.format("YYYY"),
        month: checkInDate.format("MM"),
        date: checkInDate.format("DD"),
        fullDate: checkInDate.format("YYYYMMDD")
      },
      checkOutDate: {
        year: checkOutDate.format("YYYY"),
        month: checkOutDate.format("MM"),
        date: checkOutDate.format("DD"),
        fullDate: checkOutDate.format("YYYYMMDD")
      },
    };
      dateCtx.changeDate(dateInfo);
    }

  // eslint-disable-next-line react-hooks/exhaustive-deps
  },[bothSelected]);


  const getPrevMonth = () => {
    setMoment(momentState.clone().subtract(1, "month"));

  };

  const getNextMonth = () => {
    setMoment(momentState.clone().add(1, "month"));
  };

  const dateSelectHandler = (event) => {
    const targetId = event.target.id;
    const targetText = event.target.innerText;
    const targetClassName = event.target.className.toString();
    const Today = moment().format("YYYYMMDD").toString();

    console.log(targetId);

    if (targetText !== " " && +targetId >= +Today && !targetClassName.includes("invalid")) {
      if (selectedInState.isSelected === false) {
        setSelectedInState({ isSelected: true, selectedInDate: targetId });
      } else {
        if (+targetId < +selectedInState.selectedInDate) {
          setSelectedInState({ isSelected: true, selectedInDate: targetId });
          setSelectedOutState({ isSelected: false, selectedOutDate: "" });
          setBothSelected(false);
        } else {
          if (selectedOutState.isSelected === false) {
            if(+selectedInState.selectedInDate === +targetId){
              return;
            } 
            setSelectedOutState({
              isSelected: true,
              selectedOutDate: targetId,
            });
            setBothSelected(true);
          } else {
            if(bothSelected){
              setBothSelected(false);
              setSelectedInState({ isSelected: true, selectedInDate: targetId });
              setSelectedOutState({isSelected:false, selectedOutDate: ""});
              
            }else{
            setSelectedInState({ isSelected: true, selectedInDate: targetId });
            setSelectedOutState({ isSelected: false, selectedOutDate: "" });
            setBothSelected(false);
            }
          }
        }
      }
    }
  return;
  }

  // const [availableRooms, setAvailableRooms] = useState("0");

  //       useEffect(()=>{

  //         const requestOptions = {
  //           method: 'POST',
  //           headers: {'Content-Type' : 'application/json'},
  //           body: JSON.stringify({title: 'React post request'})
  //         };


  //         fetch("/availableRooms", requestOptions)
  //           .then((response) => response.json())
  //           .then((availableRooms) =>
  //             setAvailableRooms(availableRooms)
  //           );
  //       },[]);



    return (
      <div className={classes.container}>
        <div className={classes["first-cal"]}>
          {arrowIsValid && (
            <button className={classes["prev-btn"]} onClick={getPrevMonth}>
              <img src={left_arrow} alt="left arrow" />
            </button>
          )}

          <Calendar
            currentMoment={momentState.clone()}
            buttonType="prev"
            selectedInState={selectedInState}
            selectedOutState={selectedOutState}
            bothSelected={bothSelected}
            dateSelectHandler={dateSelectHandler}
          />
        </div>
        <div className={classes["last-cal"]}>
          <Calendar
            currentMoment={momentState.clone().add(1, "month")}
            buttonType="next"
            selectedInState={selectedInState}
            selectedOutState={selectedOutState}
            bothSelected={bothSelected}
            dateSelectHandler={dateSelectHandler}
          />
          <button className={`${classes["next-btn"]}`} onClick={getNextMonth}>
            <img src={right_arrow} alt="right_arrow" />
          </button>
        </div>
        <div className={classes.condition_select}>
         <CapacityCounter />
         <ViewSelect />
         <button className={classes.select_complete_btn} onClick={props.onClose}>
            선택완료
         </button>
        </div>
      </div>
    );
}

export default ResvDetail;