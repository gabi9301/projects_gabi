import classes from "./BookSelected.module.css";

const BookSelected = (props) => {
return (
<div className={classes.book_selected_wrapper}>
    <span cl>{props.selected_name}</span>
    <spam>{props.selected_content}</spam>
</div>)



}

export default BookSelected;