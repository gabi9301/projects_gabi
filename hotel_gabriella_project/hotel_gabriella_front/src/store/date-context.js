import React from "react";

const DateContext = React.createContext({
  checkInDate: {},
  checkOutDate: {},
  changeDate: (date) => {}
});

export default DateContext;