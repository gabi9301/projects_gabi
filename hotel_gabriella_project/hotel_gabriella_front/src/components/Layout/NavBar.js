import { React, useContext, useEffect, Fragment } from "react";
import classes from "./NavBar.module.css";
import { Link } from "react-router-dom";
import LoginIcon from "../../assets/image/login_icon.png";
import LogoutIcon from "../../assets/image/logout_icon.png";
import JoinIcon from "../../assets/image/join_icon.png";
import LoginContext from "../../store/login-context";
import MypageIcon from "../../assets/image/mypage_icon.png";

const NavBar = (props) => {
  const loginCtx = useContext(LoginContext);

  const logoutHandler = () => {
    const authState = {
      isLoggedIn: false,
      id: "",
      account: "",
      accessToken: "",
      refreshToken: ""
    };

    loginCtx.changeLogin(authState);
    sessionStorage.clear();
  };

  return (
    <div className={classes.container}>
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
