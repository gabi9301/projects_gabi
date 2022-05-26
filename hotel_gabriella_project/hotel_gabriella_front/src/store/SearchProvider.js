import { useState } from "react";
import SearchContext from "./search-context";

const SearchProvider = (props) => {
  const [countState, setCountState] = useState(1);
  const [viewType, setViewType] = useState("NONE")

  const viewValue = {
    NONE: "비전망",
    OCEAN: "바다",
    MOUNTAIN: "산",
  };

  const addCountHandler = () => {
    setCountState(countState + 1);
  };

  const reduceCountHandler = () => {
    setCountState(countState - 1);
  };

  const changeSelectHandler = (viewType) => {
    setViewType(viewType);
  }

  const searchContext = {
    viewValue: viewValue,
    countState: countState,
    addCount: addCountHandler,
    reduceCount: reduceCountHandler,
    viewType: viewType,
    changeSelect : changeSelectHandler
  };

  return (
    <SearchContext.Provider value={searchContext}>
      {props.children}
    </SearchContext.Provider>
  );
};

export default SearchProvider;
