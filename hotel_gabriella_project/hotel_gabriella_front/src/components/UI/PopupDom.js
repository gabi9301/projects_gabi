import reactDom from "react-dom";

import classes from "./PopupDom.module.css";
import close_btn from "../../assets/image/close_btn.png"
import { Fragment } from "react";


const Backdrop = (props) => {
  return <div className={classes.backdrop} onClick={props.onClose} />;
};

const PopupOverlay = (props) => {
  return (
    <div className={classes.popup_overlay_container}>
      <div className={classes.popup_content}>{props.children}</div>
      <button className={classes["close-btn"]} onClick={props.onClose}>
        <img src={close_btn} alt="close-button" />
      </button>
    </div>
  );
};

const portalElement = document.getElementById("overlays");

const PopupDom = (props) => {
  return (
    <Fragment>
      {reactDom.createPortal(
        <Backdrop onClose={props.onClose} />,
        portalElement
      )}
      {reactDom.createPortal(
        <PopupOverlay onClose={props.onClose}>{props.children}</PopupOverlay>,
        portalElement
      )}
    </Fragment>
  );
};

export default PopupDom;
