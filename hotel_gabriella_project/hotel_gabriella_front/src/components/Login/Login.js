import { useState, useRef, useContext } from "react";
import { useHistory } from "react-router-dom";
import classes from "./Login.module.css";
import LoginBox from "./LoginBox";
import LoginContext from "../../store/login-context";

const Login = (props) => {
  const loginCtx = useContext(LoginContext);
  const history = useHistory();

  const [memberId, setMemberId] = useState("");
  const [memberPwd, setMemberPwd] = useState("");

  const idChangeHandler = (event) => {
    setMemberId(event.target.value);
  };

  const pwdChangeHandler = (event) => {
    setMemberPwd(event.target.value);
  };

  async function loginHandler(loginInfo) {
    const requestOptions = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(loginInfo),
    };

    try {
      fetch("/login", requestOptions)
        .then((response) => (response.status === 201 ? response.json() : null))
        .then((result) => {
          const authState = {
            isLoggedIn: true,
            id: result.id,
            account: result.account,
            authorization: result.accessToken,
            refreshtoken: result.refreshToken,
          };

          loginCtx.changeLogin(authState);
          sessionStorage.setItem("authState", JSON.stringify(authState));
          history.push("/");
        });
    } catch (error) {
      console.log(error);
    } 
  }

  return (
    <LoginBox
      memberId={memberId}
      memberPwd={memberPwd}
      idChangeHandler={idChangeHandler}
      pwdChangeHandler={pwdChangeHandler}
      onLogin={loginHandler}
    />
  );
};

export default Login;
