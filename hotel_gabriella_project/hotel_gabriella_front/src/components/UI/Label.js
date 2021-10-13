import { Fragment } from 'react';
import classes from './Label.module.css';

const Label = props => {
    return (
      <div className={classes.container} >
        <span className={classes.title}>{props.title}</span>
        <span className={classes.content}>{props.content}</span>
      </div>
    );
};

export default Label;