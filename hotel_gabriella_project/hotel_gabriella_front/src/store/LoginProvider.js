import { useState } from "react";
import { useEffect } from "react/cjs/react.production.min";
import LoginContext from "./login-context";

const LoginProvider = (props) => {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [id, setId] = useState("");
  const [account, setAccount] = useState("");
  const [accessToken, setAccessToken] = useState("");
  const [refresToken, setRefreshToken] = useState("");



  const changeLoginHandler = (authState) => {
    setIsLoggedIn(authState.isLoggedIn);
    setId(authState.id);
    setAccount(authState.account);
    setAccessToken(authState.accessToken);
    setRefreshToken(authState.refresToken);
  };

  const loginContext = {
    isLoggedIn: isLoggedIn,
    id :id,
    account: account,
    accessToken: accessToken,
    refresToken: refresToken,
    changeLogin: changeLoginHandler,
  };

  return (
    <LoginContext.Provider value={loginContext}>
      {props.children}
    </LoginContext.Provider>
  );
};

export default LoginProvider;
