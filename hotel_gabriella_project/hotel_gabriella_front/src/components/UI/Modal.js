import { Fragment } from 'react';
import reactDom from 'react-dom';
import classes from './Modal.module.css';
import close_btn from '../../assets/image/close_btn.png';

const Backdrop = props => {
    return <div className={classes.backdrop} onClick={props.onClose} />
};

const ModalOverlay = props => {
    return (
        <div className={classes.modal}>
            <div className={classes.content}>{props.children}</div>
            <button className={classes['close-btn']} onClick={props.onClose}>
                <img src={close_btn} alt="close-button" />
            </button>
        </div>
    );
};

const portalElement = document.getElementById('overlays');

const Modal = (props) => {
    return (
        <Fragment>
            {reactDom.createPortal(<Backdrop onClose={props.onClose}/>, portalElement)}
            {reactDom.createPortal(<ModalOverlay onClose={props.onClose}>{props.children}</ModalOverlay>, portalElement)}
        </Fragment>
    );
};

export default Modal;