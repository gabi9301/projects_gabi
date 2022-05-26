import React from "react";

const SearchContext = React.createContext({

  countState: 1,
  addCount: () => {},
  reduceCount: () => {},
  viewType: "NONE",
  changeSelect: () => {}
});

export default SearchContext;
