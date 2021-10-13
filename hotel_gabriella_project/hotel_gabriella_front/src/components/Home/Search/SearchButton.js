import classes from './SearchButton.module.css';

const SearchButton = (props) => {
    return(
    <button className={classes['big-button']} onClick={props.onClick}>{props.content}</button>
    );
};

export default SearchButton;