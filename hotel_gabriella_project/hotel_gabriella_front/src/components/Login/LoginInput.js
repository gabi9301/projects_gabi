import classes from "./LoginInput.module.css";

const LoginInput = (props) => {
  return (
    <div className={classes.input_container}>
      <label htmlFor="inputId" className={classes.input_label}>
        <span>
          <img src={props.icon} alt="icon" />
        </span>
      </label>
      <input
        type={props.type}
        placeholder={props.placeholder}
        onChange={props.inputChangeHandler}
        className={classes.textInput}
        value={props.value}
      />
    </div>
  );
};

export default LoginInput;
