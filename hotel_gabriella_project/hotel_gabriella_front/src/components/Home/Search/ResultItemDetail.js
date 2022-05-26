import react from "react";
import PopupDom from "../../UI/PopupDom";
import ItemDetail from "./ItemDetail";
import classes from "./ResultItemDetail.module.css";

const ResultItemDetail = (props) => {
    return (
        <PopupDom onClose={props.onClose}>
            <ItemDetail slides={props.slides}/>
        </PopupDom>
    )
};

export default ResultItemDetail;
