import { useState } from "react";
import { Fragment } from "react/cjs/react.production.min";
import left_arrow from "../../assets/image/left_arrow.png";
import right_arrow from "../../assets/image/right_arrow.png";
import classes from "./Slide.module.css";

const Slide = (props) => {
  const slide_images = props.slideArr;
  const count = slide_images.length;
  const [sourceCount, setSourceCount] = useState(0);
  const [currentSlide, setCurrentSlide] = useState(slide_images[sourceCount]);

  // const slideList = slide_images.map((item) => (
  //   <div className={classes.slide_fade}>
  //     <img src={item} alt="slide_image" />
  //   </div>
  // ));

  const dotClickHandler = (event) => {
    const dotIndex = parseInt(event.target.id);
    setCurrentSlide(slide_images[dotIndex]);
    setSourceCount(dotIndex);
  };

  const dotList = slide_images.map((item,index) => (
    <span className={classes.dot} key={index} id={index} onClick={dotClickHandler}></span>
  ));

  const slidePrevHandler = () => {
    if (sourceCount > 0) {
      setCurrentSlide(slide_images[sourceCount - 1]);
      setSourceCount(sourceCount - 1);
    }
  };

  const slideNextHandler = () => {
    if (sourceCount < slide_images.length - 1) {
      setCurrentSlide(slide_images[sourceCount + 1]);
      setSourceCount(sourceCount + 1);
    }
  };

  return (
    <Fragment>
      <div className={classes.slide_container}>
        {count !== 0 && (
          <div className={classes.slide_fade}>
            <img src={currentSlide} alt="slide_image" />
          </div>
        )}
        <div className={classes.button_container}>
          <button className={classes.slide_prev} onClick={slidePrevHandler}>
            <img src={left_arrow} alt="prev_button" />
          </button>
          <button className={classes.slide_next} onClick={slideNextHandler}>
            <img src={right_arrow} alt="next_button" />
          </button>
        </div>
      </div>
      <br />
      <div className={classes.dot_container}>{count !== 0 && dotList}</div>
    </Fragment>
  );
};

export default Slide;
