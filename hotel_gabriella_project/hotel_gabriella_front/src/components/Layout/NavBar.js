import { React, useContext, useEffect, Fragment } from "react";
import classes from "./NavBar.module.css";
import { Link, useHistory } from "react-router-dom";
import LoginIcon from "../../assets/image/login_icon.png";
import LogoutIcon from "../../assets/image/logout_icon.png";
import JoinIcon from "../../assets/image/join_icon.png";
import LoginContext from "../../store/login-context";
import MypageIcon from "../../assets/image/mypage_icon.png";

const NavBar = (props) => {
  const loginCtx = useContext(LoginContext);
  const history = useHistory();

  async function logoutHandler() {
    const authState = JSON.parse(sessionStorage.getItem("authState"));

    console.log(authState.authorization);

    if (authState !== null) {
      const requestOptions = {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          authorization: authState.authorization,
          refreshtoken: authState.refreshtoken,
        },
        body: JSON.stringify(authState),
        //origin: "http://localhost:3000"
        //"Access-Control-Allow-origin" : "http://localhost:8080"
      };

      try {
        fetch("/logout", requestOptions); //여기서 이미 리다이렉트 일어남, 이걸 막아야함       

        //.then((result) =>  console.log(result.account));
      } catch (error) {
        console.log(error);
      }
    }

    const clearAuthState = {
      isLoggedIn: false,
      id: "",
      account: "",
      accessToken: "",
      refreshToken: "",
    };

    loginCtx.changeLogin(clearAuthState);
    sessionStorage.clear();
    history.push("/login");
  }

  return (
    <div className={classes.container}>
      <Link to="/reservationCheck"><button>예약 조회</button></Link>
      {loginCtx.isLoggedIn ? (
        <Fragment>
          <div className={classes.icon}>
            <Link to="/mypage">
              <img src={MypageIcon} alt="mypage" />
            </Link>
          </div>
          <div className={classes.icon}>
            <Link to="/logout" onClick={logoutHandler}>
              <img src={LogoutIcon} alt="logout" />
            </Link>
          </div>
        </Fragment>
      ) : (
        <Fragment>
          <div className={classes.icon}>
            <Link to="/join">
              <img src={JoinIcon} alt="join" />
            </Link>
          </div>
          <div className={classes.icon}>
            <Link to="/login">
              <img src={LoginIcon} alt="login" />
            </Link>
          </div>
        </Fragment>
      )}
    </div>
  );
};

export default NavBar;
