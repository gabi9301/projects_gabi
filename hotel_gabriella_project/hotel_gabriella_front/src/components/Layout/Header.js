import { Fragment } from 'react';
import { Link } from 'react-router-dom';
import classes from './Header.module.css';

import NavBar from './NavBar';

const Header = props => {
    return (
    <Fragment>
        <NavBar />
        <div className={classes.header}>
            <Link to='/' style={{textDecoration: 'none', color: 'inherit'} }><h1>Hotel Gabriella</h1></Link>
        </div>
        
    </Fragment>
    );

};

export default Header;