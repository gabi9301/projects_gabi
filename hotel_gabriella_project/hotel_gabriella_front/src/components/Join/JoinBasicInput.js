import { useState, useEffect } from "react";
import classes from "./JoinBasicInput.module.css";

const JoinBasicInput = (props) => {
  const [spanContent, setSpanContent] = useState(props.regexMessage);

  const [uniqueButtonDisabled, setUniqueButtonDisabled] = useState(true);

  

  const inputChangeHandler = (event) => {
    const regex = props.regex;
    let spanMessage = "";

    if (regex) {
      if (regex.test(event.target.value)) {
        spanMessage = "사용가능";
        props.onValidityCheck(true); 
        if (event.target.id === "account") {
          setUniqueButtonDisabled(false);        
        }
      } else {
        props.onValidityCheck(false);
        spanMessage = props.regexMessage;

        if (event.target.id === "account") {
          setUniqueButtonDisabled(true);
        }
      }
      setSpanContent(spanMessage);
    }
    
    props.setEnteredId(event.target.value);
  };

  const uniqueCheckHandler = (event) => {
    event.preventDefault();
    console.log(props.enteredId);
    //db 조회하는 http 요청 로직
    props.onUniqueAccountCheck({ account: props.enteredId });

    //이 결과를 폼 제출할 때 확인해서 중복되지 않은 아이디 값인 걸 확실히 하구 제출
    //만약 이 값이 없으면 패스된 아이디가 없는 것
    //이 값이 있더라도 폼 안에 입력된 값이랑 다르면 중복확인이 안 된 아이디라는 것
  };

  
  return (
    <div className={classes.join_input_container}>
      <div className={classes.join_input_wrap}>
        <div className={classes.join_input_inner_wrap}>
          <label htmlFor={props.id} className={classes.join_input_label}>
            {props.labelContent}
          </label>
          <input
            className={
              props.hasButton
                ? classes.join_input_with_button
                : classes.join_input
            }
            type={props.type}
            id={props.id}
            name={props.name}
            placeholder={props.placeholder}
            onChange={inputChangeHandler}
            value={props.enteredId}
          />
        </div>
        <span
          className={
            spanContent === "사용가능"
              ? classes.join_input_pass
              : classes.join_input_span
          }
        >
          {spanContent}
        </span>
      </div>

      {props.hasButton && (
        <button
          className={classes.check_unique}
          onClick={uniqueCheckHandler}
          disabled={uniqueButtonDisabled}
        >
          중복확인
        </button>
      )}
    </div>
  );
};

export default JoinBasicInput;
