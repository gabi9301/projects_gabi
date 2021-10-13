import React from "react";
import classes from './NavBar.module.css';
import { Link } from "react-router-dom";
import LoginIcon from '../../assets/image/login_icon.png';
import LogoutIcon from '../../assets/image/logout_icon.png';
import JoinIcon from '../../assets/image/join_icon.png';

const NavBar = (props) => {
    return (
      <div className={classes.container}>
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
      </div>
    );
};

export default NavBar;