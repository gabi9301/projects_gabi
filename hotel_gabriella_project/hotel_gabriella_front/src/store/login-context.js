import React from "react";

const LoginContext = React.createContext({
  isLoggedIn: false,
  id: "",
  account: "",
  accessToken: "",
  refreshToken: "",
  changeLogin: (authState) => {}
});

export default LoginContext;
