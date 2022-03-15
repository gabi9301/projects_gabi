import classes from "./RememberMe.module.css";

const RememberMe = () => {
  return (
    <div className={classes.remember_container}>
      <input type="checkbox" id="remember" />
      <label htmlFor="remember">Remember</label>
    </div>
  );
};

export default RememberMe;
