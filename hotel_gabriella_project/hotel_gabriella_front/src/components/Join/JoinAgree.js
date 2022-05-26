import classes from "./JoinAgree.module.css";

const JoinAgree = (props) => {
  return (
    <div className={classes.join_agree_wrap}>
      <div className={classes.agree_category}>{props.agree_category}</div>
      <div className={classes.agree_category_inner_wrap}>
        <input type="hidden" name={props.code_name} value={props.code_value} />
        <div className={classes.radio_wrap}>
          <input
            type="radio"
            id={props.yes_id}
            name={props.term_name}
            value="y"
            defaultChecked
          />
          <label htmlFor={props.yes_id}>{props.label_y_value}</label>
        </div>
        <div className={classes.radio_wrap}>
          <input
            type="radio"
            id={props.no_id}
            name={props.term_name}
            value="n"
          />
          <label htmlFor={props.no_id}>{props.label_n_value}</label>
        </div>
      </div>
    </div>
  );
};

export default JoinAgree;
