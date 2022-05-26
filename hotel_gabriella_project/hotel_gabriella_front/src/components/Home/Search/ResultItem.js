import { useState, useContext } from "react";
import SearchContext from "../../../store/search-context";
import classes from "./ResultItem.module.css";
import won_icon from "../../../assets/image/won_icon.png";
import capacity_icon from "../../../assets/image/capacity_icon.png";
import window_icon from "../../../assets/image/window_icon.png";
import detail_arrow from "../../../assets/image/detail_arrow.png";
import { Fragment } from "react/cjs/react.production.min";
import ResultItemDetail from "./ResultItemDetail";

const ResultItem = (props) => {
  const searchCtx = useContext(SearchContext);

  const [detailShown, setDetailShown] = useState(false);

  const showDetailHandler = () =>{
    setDetailShown(true);
  }

  const hideDetailHandler = () => {
    setDetailShown(false);
  }

  return (
    <Fragment>
      <div className={classes.item_container}>
        <div className={classes.img_inner_container}>
          <img
            src={props.imgSource}
            alt="room_img"
            className={classes.room_img}
          />
        </div>
        <div className={classes.content_inner_container}>
          <div className={classes.inner_title_wrap}>
            <span className={classes.roomType_span}>{props.roomType}</span>
            <img
              src={detail_arrow}
              alt="detail_image"
              className={classes.detail_arrow_img}
              onClick={showDetailHandler}
            />
          </div>
          <div className={classes.inner_content_wrap}>
            <div className={classes.inner_content_item}>
              <img src={capacity_icon} alt="capacity_image" />
              <span className={classes.capacity_span}>정원 </span>
              <span className={classes.capacity_span}>{props.capacity}명</span>
            </div>
            <div className={classes.inner_content_item}>
              <img src={window_icon} alt="window_image" />
              <span className={classes.viewType_span}>전망 </span>
              <span className={classes.viewType_span}>
                {searchCtx.viewValue[props.viewType]}
              </span>
            </div>

            <div className={classes.inner_content_item}>
              <div className={classes.price_span_wrap}>
                <span className={classes.price_span}>
                  1박 기준 / <img src={won_icon} alt="won_image" />
                </span>
                <span className={classes.price_span}>{props.price}</span>
              </div>
            </div>
          </div>
        </div>
        <div className={classes.button_inner_container}>
          <button className={classes.reserve_button}>예약하기</button>
        </div>
      </div>
      <div className={classes.item_detail_container}>
        {detailShown && (
          <ResultItemDetail
            onClose={hideDetailHandler}
            slides={props.slideSource}
          />
        )}
      </div>
    </Fragment>
  );
};

export default ResultItem;
