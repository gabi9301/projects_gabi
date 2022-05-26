import { useState, useEffect, useContext } from "react";
import SearchContext from "../../../store/search-context";
import classes from "./CapacityCounter.module.css";
import plus_icon from "../../../assets/image/plus_icon.png";
import minus_icon from "../../../assets/image/minus_icon.png";

const CapacityCounter = (props) => {

  const [plusButtonDisabled, setPlusButtonDisabled] = useState(false);
  const [minusButtonDisabled, setMinusButtonDisabled] = useState(true);

  const searchCtx = useContext(SearchContext);
    console.log(searchCtx);

  useEffect(() => {
    if (searchCtx.countState === 1) {

      setMinusButtonDisabled(true);
    } else if (searchCtx.countState === 5) {
      setPlusButtonDisabled(true);
    } else {
      if (minusButtonDisabled || plusButtonDisabled) {
        setMinusButtonDisabled(false);
        setPlusButtonDisabled(false);
      }
    }
  }, [searchCtx.countState, plusButtonDisabled, minusButtonDisabled]);

  return (
    <div className={classes.counter_container}>
      <span className={classes.counter_span}>인원</span>
      <div className={classes.counter_inner_container}>
        <button
          className={classes.counter_button}
          onClick={searchCtx.reduceCount}
          disabled={minusButtonDisabled}
        >
          <img src={minus_icon} alt="minus_icon" />
        </button>
        <span className={classes.counter_state}>{searchCtx.countState}</span>
        <button
          className={classes.counter_button}
          onClick={searchCtx.addCount}
          disabled={plusButtonDisabled}
        >
          <img src={plus_icon} alt="plus_icon" />
        </button>
      </div>
    </div>
  );
};

export default CapacityCounter;
