import classes from "./BookInput.module.css";

const BookInput = (props) =>{

return(
<div className={classes.book_input_wrapper}>
    <label htmlFor={props.id}>{props.input_name}</label>
    <input id={props.id} name={props.name} type={props.type} className={classes.book_input} onChange={props.changeHandler} value={props.value}>
    </input>
</div>)





}


export default BookInput;