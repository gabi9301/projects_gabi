import react from "react";
import Slide from "../../UI/Slide";
import bathtub from "../../../assets/image/detail_slide/double_twin/bathtub.jpg";
import premium_bed from "../../../assets/image/detail_slide/double_twin/premium_bed.jpg";
import classes from "./ItemDetail.module.css";

const ItemDetail = (props) => {

//const slides = [bathtub, premium_bed];

return (<div className={classes.item_detail_container}>
    <Slide slideArr = {props.slides}/>
</div>)
};

export default ItemDetail;