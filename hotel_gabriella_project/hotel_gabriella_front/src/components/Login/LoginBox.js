import classes from "./LoginBox.module.css";
import LoginInput from "./LoginInput";
import id_icon from "../../assets/image/member_id.png";
import pwd_icon from "../../assets/image/member_password.png";
import LoginButton from "./LoginButton";
import RememberMe from "./RememberMe";
import React, { useRef } from "react";

function LoginBox(props) {
  return (
    <div className={classes.container}>
      <div className={classes.outer_container}>
        <div className={classes.middle_container}>
          <div className={classes.inner_container}>
            <LoginInput
              type="text"
              placeholder="MEMBER ID"
              inputChangeHandler={props.idChangeHandler}
              icon={id_icon}
              value={props.memberId}
            />

            <LoginInput
              type="password"
              placeholder="MEMBER PASSWORD"
              inputChangeHandler={props.pwdChangeHandler}
              icon={pwd_icon}
              value={props.memberPwd}
            />
          </div>
          <RememberMe />
        </div>
        <LoginButton
          memberId={props.memberId}
          memberPwd={props.memberPwd}
          loginHandler={props.onLogin}
        />
      </div>
    </div>
  );
}

export default LoginBox;
