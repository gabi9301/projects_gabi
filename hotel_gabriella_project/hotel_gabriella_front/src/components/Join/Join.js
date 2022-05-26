import React from "react";
import { useState } from "react";
import classes from './Join.module.css';
import JoinBox from "./JoinBox";
import PopUpDom from "../UI/PopupDom";

const Join = (props) => {
  const [popupShown, setPopupShown] = useState(false);




    return (
        // <div className={classes.title}>
        //     <h1>Join Page here!</h1>
        // </div>
        <JoinBox />

    );
};

export default Join;