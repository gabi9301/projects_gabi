import { useRef } from "react";
import classes from "./LoginButton.module.css";


const LoginButton = (props) => {

  const loginInfo = {
    account: props.memberId,
    password: props.memberPwd,
  };

  return (
    <div className={classes.button_container}>
      <input
        type="button"
        value="LOGIN"
        className={classes.login_button}
        onClick={()=> props.loginHandler(loginInfo)}
      
      />
    </div>
  );
};

export default LoginButton;
