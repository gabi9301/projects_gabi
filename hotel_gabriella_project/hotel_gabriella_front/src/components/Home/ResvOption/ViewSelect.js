import { useContext } from "react";
import SearchContext from "../../../store/search-context";
import classes from "./ViewSelect.module.css";

// const selectValue = {
//   NONE: "비전망",
//   OCEAN: "바다",
//   MOUNTAIN: "산",
// };

const ViewSelect = (props) => {
  const searchCtx = useContext(SearchContext);

  const selectChangeHandler = (event) => {
    //console.log(event.target.value);
    searchCtx.changeSelect(event.target.value);
  };

  return (
    <div className={classes.select_container}>
      <span className={classes.select_span}>전망</span>
      <div className={classes.select_inner_container}>
        <select
          className={classes.select_viewBox}
          onChange={selectChangeHandler}
        >
          <option value="NONE">{searchCtx.viewValue.NONE}</option>
          <option value="OCEAN">{searchCtx.viewValue.OCEAN}</option>
          <option value="MOUNTAIN">{searchCtx.viewValue.MOUNTAIN}</option>
        </select>
      </div>
    </div>
  );
};

export default ViewSelect;
